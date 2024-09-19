package org.bartoszwojcik.investmentportfolioapi.service.calculate;

import java.math.BigDecimal;
import org.bartoszwojcik.investmentportfolioapi.dto.calculate.CalculatorOutputDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;

public interface CalculateInvestment {
    CalculatorOutputDto calculateValueWithOnePayment(User user,
                                                     BigDecimal rateOfReturnInPercent,
                                                     int years);

    CalculatorOutputDto calculateValueWithOnePaymentInflation(User user,
                                                              BigDecimal rateOfReturnInPercent,
                                                              int years,
                                                              String inflationCountry);

    CalculatorOutputDto calculateValueWithManyTimes(User user,
                                                    BigDecimal perMonth,
                                                    BigDecimal rateOfReturnInPercent,
                                                    int years);

    CalculatorOutputDto calculateValueWithManyTimesInflation(User user,
                                                             BigDecimal perMonth,
                                                             BigDecimal rateOfReturnInPercent,
                                                             int years,
                                                             String inflationCountry);
}
