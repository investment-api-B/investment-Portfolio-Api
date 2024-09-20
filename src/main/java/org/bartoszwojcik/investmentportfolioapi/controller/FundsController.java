package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.funds.FundsDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.service.funds.FundsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funds")
@RequiredArgsConstructor
public class FundsController {
    private final FundsService fundsService;

    @Operation(summary = "add funds to your account")
    @PostMapping("/add/{amount}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FundsDto createPaymentSession(Authentication authentication,
                                         @PathVariable BigDecimal amount) {
        User user = (User) authentication.getPrincipal();
        return fundsService.createPayment(user, amount);
    }

    @Operation(summary = "Check successful Stripe adding funds")
    @GetMapping("/success/{sessionId}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public String paymentSuccess(@PathVariable String sessionId) {
        return fundsService.successPayment(sessionId);
    }

    @Operation(summary = "Return adding funds cancel message")
    @GetMapping("/cancel/{sessionId}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public String paymentCancel(@PathVariable String sessionId) {
        return fundsService.cancelPayment(sessionId);
    }

    @Operation(summary = "send information to telegram"
            + " information about withdrawal funds")
    @PostMapping("withdrawal/{amount}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public String withdrawalFunds(Authentication authentication,
                                  @PathVariable BigDecimal amount) {
        User principal = (User) authentication.getPrincipal();
        return fundsService.withdrawal(principal, amount);
    }
}
