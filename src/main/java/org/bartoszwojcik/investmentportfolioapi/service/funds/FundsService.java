package org.bartoszwojcik.investmentportfolioapi.service.funds;

import java.math.BigDecimal;
import org.bartoszwojcik.investmentportfolioapi.dto.funds.FundsDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;

public interface FundsService {
    FundsDto createPayment(User user, BigDecimal amount);

    String successPayment(String sessionId);

    String cancelPayment(String sessionId);

    String withdrawal(User user, BigDecimal amount);
}
