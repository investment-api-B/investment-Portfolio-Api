package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.calculate.CalculatorOutputDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.service.calculate.CalculateInvestment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count-it-up")
@RequiredArgsConstructor
public class InvestmentCalculatorController {
    private final CalculateInvestment calculateInvestment;

    @Operation(summary = "Investment calculator without taking inflation into account",
            description = "rateOfReturnInPercent = 1 equals 1% \n "
                + "rateOfReturnInPercent = 10 equals 10%")
    @GetMapping("/one-payment/{rateOfReturnInPercent}/{years}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CalculatorOutputDto calculateValueWithOnePayment(Authentication authentication,
                               @PathVariable BigDecimal rateOfReturnInPercent,
                               @PathVariable int years) {
        User principal = (User) authentication.getPrincipal();
        return calculateInvestment.calculateValueWithOnePayment(
                principal, rateOfReturnInPercent, years
        );
    }

    @Operation(summary = "Inflation-adjusted investment calculator",
            description = "rateOfReturnInPercent = 1 equals 1% \n "
                    + "rateOfReturnInPercent = 10 equals 10%")
    @GetMapping("/one-payment/{inflationCountry}/{rateOfReturnInPercent}/{years}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CalculatorOutputDto calculateValueWithOnePaymentInflation(Authentication authentication,
                               @PathVariable BigDecimal rateOfReturnInPercent,
                               @PathVariable int years,
                               @PathVariable String inflationCountry) {
        User principal = (User) authentication.getPrincipal();
        return calculateInvestment.calculateValueWithOnePaymentInflation(
                principal, rateOfReturnInPercent, years, inflationCountry
        );
    }

    @Operation(summary = "Investment calculator without taking inflation into account "
            + "(with monthly contributions)",
            description = "rateOfReturnInPercent = 1 equals 1% \n "
                    + "rateOfReturnInPercent = 10 equals 10%")
    @GetMapping("/many-times/{perMonth}/{rateOfReturnInPercent}/{years}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CalculatorOutputDto calculateValueWithManyTimes(Authentication authentication,
                               @PathVariable BigDecimal perMonth,
                               @PathVariable BigDecimal rateOfReturnInPercent,
                               @PathVariable int years) {
        User principal = (User) authentication.getPrincipal();
        return calculateInvestment.calculateValueWithManyTimes(
                principal, perMonth, rateOfReturnInPercent, years
        );
    }

    @Operation(summary = "Inflation-adjusted investment calculator (with monthly contributions)",
            description = "rateOfReturnInPercent = 1 equals 1% \n "
                    + "rateOfReturnInPercent = 10 equals 10%")
    @GetMapping("/many-times/{inflationCountry}/{perMonth}/{rateOfReturnInPercent}/{years}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CalculatorOutputDto calculateValueWithManyTimesInflation(Authentication authentication,
                               @PathVariable BigDecimal perMonth,
                               @PathVariable BigDecimal rateOfReturnInPercent,
                               @PathVariable int years,
                               @PathVariable String inflationCountry) {
        User principal = (User) authentication.getPrincipal();
        return calculateInvestment.calculateValueWithManyTimesInflation(
                principal, perMonth, rateOfReturnInPercent, years, inflationCountry
        );
    }
}
