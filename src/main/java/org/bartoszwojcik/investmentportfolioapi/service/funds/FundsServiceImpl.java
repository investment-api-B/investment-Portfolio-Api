package org.bartoszwojcik.investmentportfolioapi.service.funds;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.funds.FundsDto;
import org.bartoszwojcik.investmentportfolioapi.exception.PaymentException;
import org.bartoszwojcik.investmentportfolioapi.mapper.FundsMapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Funds;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.model.enums.PaymentType;
import org.bartoszwojcik.investmentportfolioapi.model.enums.Status;
import org.bartoszwojcik.investmentportfolioapi.repository.funds.FundsRepository;
import org.bartoszwojcik.investmentportfolioapi.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundsServiceImpl implements FundsService {
    private static final String CANCEL = "/cancel/";
    private static final String SUCCESS = "/success/";
    private static final String COMPLETE = "complete";
    private static final String OPEN = "open";
    private final FundsMapper fundsMapper;
    private final FundsRepository fundsRepository;
    private final UserRepository userRepository;

    @Value("${domain}")
    private String domain;
    @Value("${adding.founds.endpoint}")
    private String addingFoundsEndpoint;
    @Value("${currency}")
    private String currency;

    @Override
    public FundsDto createPayment(User user, BigDecimal amount) {
        try {
            Session session = createStripeSession(amount);
            Funds funds = getFunds(
                    Status.PENDING,
                    PaymentType.ADDING,
                    amount,
                    session,
                    user
            );

            return fundsMapper.toFundsDto(
                    fundsRepository.save(funds)
            );
        } catch (StripeException e) {
            throw new PaymentException("cannot add money to account", e);
        }
    }

    private Funds getFunds(Status status,
                           PaymentType paymentType,
                           BigDecimal amount,
                           Session session,
                           User user) {
        Funds funds = new Funds();
        funds.setUserId(user.getId());
        funds.setStatus(status);
        funds.setType(paymentType);
        funds.setSessionUrl(session.getUrl());
        funds.setSessionId(session.getId());
        funds.setAmountToPay(amount);
        return funds;
    }

    private Session createStripeSession(BigDecimal amount) throws StripeException {
        SessionCreateParams sessionCreateParams = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(domain + addingFoundsEndpoint + SUCCESS + "{CHECKOUT_SESSION_ID}")
                .setCancelUrl(domain + addingFoundsEndpoint + CANCEL + "{CHECKOUT_SESSION_ID}")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency(currency)
                                .setUnitAmount(amount.multiply(BigDecimal.valueOf(100)).longValue())
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName(
                                                        "Adding to account " + amount
                                                                + " " + currency
                                                ).build()
                                ).build()
                        ).setQuantity(1L)
                        .build()
                ).build();
        return Session.create(sessionCreateParams);
    }

    @Override
    public String successPayment(String sessionId) {
        try {
            Session retrieve = Session.retrieve(sessionId);
            if (COMPLETE.equals(retrieve.getStatus())) {
                Funds funds = fundsRepository.findBySessionId(sessionId).orElseThrow(
                        () -> new PaymentException(
                                "Cannot find funds with session id " + sessionId)
                );
                User user = userRepository.findUserById(funds.getUserId()).orElseThrow(
                        () -> new PaymentException(
                                "Cannot find user with this ID: " + funds.getUserId())
                );
                funds.setStatus(Status.PAID);
                fundsRepository.save(funds);
                user.setCash(user.getCash().add(funds.getAmountToPay()));
                userRepository.save(user);
                return "You successfully added funds to your account! Thank you for your payment.";
            } else if (OPEN.equals(retrieve.getStatus())) {
                return "adding funds paused. You can try add your founds later.";
            } else {
                return "Adding funds is not completed yet. Please try again.";
            }
        } catch (StripeException e) {
            throw new PaymentException(
                    "cannot retrieve payment success", e);
        }
    }

    @Override
    public String cancelPayment(String sessionId) {
        try {
            Session retrieve = Session.retrieve(sessionId);
            if (OPEN.equals(retrieve.getStatus())) {
                Funds funds = fundsRepository.findBySessionId(sessionId).orElseThrow(
                        () -> new PaymentException(
                                "Cannot find funds with session id " + sessionId)
                );
                funds.setStatus(Status.PAUSED);
                fundsRepository.save(funds);
                return "adding funds paused. You can try add your founds later.";
            } else if (COMPLETE.equals(retrieve.getStatus())) {
                return "You successfully added funds to your account! Thank you for your payment.";
            } else {
                return "Adding funds is not completed yet. Please try again.";
            }
        } catch (StripeException e) {
            throw new PaymentException(
                    "cannot retrieve payment cancel", e);
        }
    }
}
