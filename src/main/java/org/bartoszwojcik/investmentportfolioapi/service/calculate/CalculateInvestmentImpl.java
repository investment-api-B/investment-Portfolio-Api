package org.bartoszwojcik.investmentportfolioapi.service.calculate;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.config.SerpApiConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.calculate.CalculatorOutputDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.internal.GooglePageForStocksWrapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.model.classes.UserStock;
import org.bartoszwojcik.investmentportfolioapi.repository.inflation.InflationRepository;
import org.bartoszwojcik.investmentportfolioapi.repository.stock.user.UserStockRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateInvestmentImpl implements CalculateInvestment {
    private final InflationRepository inflationRepository;
    private final UserStockRepository userStockRepository;
    private final SerpApiConfig serpApiConfig;

    @Override
    public CalculatorOutputDto calculateValueWithOnePayment(User user,
                               BigDecimal rateOfReturnInPercent,
                               int years) {
        BigDecimal presentValue = getStockValue(user);

        BigDecimal rateOfReturn = rateOfReturnInPercent
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1))
                .pow(years);

        BigDecimal finalEarnings = presentValue.multiply(rateOfReturn);
        return getCalculatorDto(finalEarnings);
    }

    @Override
    public CalculatorOutputDto calculateValueWithOnePaymentInflation(User user,
                               BigDecimal rateOfReturnInPercent,
                               int years,
                               String inflationCountry) {
        BigDecimal presentValue = getStockValue(user);

        BigDecimal rateOfReturn = rateOfReturnInPercent
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1));

        Inflation inflation = inflationRepository.findByCountryName(inflationCountry).orElseThrow(
                () -> new EntityNotFoundException(
                        "We could not find any inflation for " + inflationCountry
                )
        );
        BigDecimal yearToYear = BigDecimal.valueOf(inflation.getYearToYear());

        BigDecimal inflationRate = yearToYear
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1));

        BigDecimal adjustedRate = rateOfReturn.divide(inflationRate, 10, RoundingMode.HALF_UP);
        BigDecimal investingYears = adjustedRate.pow(years);

        BigDecimal finalEarnings = presentValue.multiply(investingYears);
        return getCalculatorDto(finalEarnings);
    }

    @Override
    public CalculatorOutputDto calculateValueWithManyTimes(User user,
                               BigDecimal perMonth,
                               BigDecimal rateOfReturnInPercent,
                               int years) {
        BigDecimal presentValue = getStockValue(user);
        BigDecimal rateOfReturn = rateOfReturnInPercent
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        BigDecimal added = rateOfReturn.add(BigDecimal.valueOf(1)).pow(years);

        BigDecimal rateOfReturnPerMonth = rateOfReturn
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal divided = rateOfReturnPerMonth.add(BigDecimal.valueOf(1))
                .pow(12 * years)
                .subtract(BigDecimal.valueOf(1))
                .divide(rateOfReturnPerMonth, 10, RoundingMode.HALF_UP);

        BigDecimal finalEarnings = presentValue.multiply(added)
                .add(perMonth.multiply(divided));
        return getCalculatorDto(finalEarnings);
    }

    @Override
    public CalculatorOutputDto calculateValueWithManyTimesInflation(User user, BigDecimal perMonth,
                               BigDecimal rateOfReturnInPercent,
                               int years,
                               String inflationCountry) {
        BigDecimal presentValue = getStockValue(user);

        BigDecimal rateOfReturn = rateOfReturnInPercent
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1));

        Inflation inflation = inflationRepository.findByCountryName(inflationCountry).orElseThrow(
                () -> new EntityNotFoundException(
                        "We could not find any inflation for " + inflationCountry
                )
        );
        BigDecimal yearToYear = BigDecimal.valueOf(inflation.getYearToYear());

        BigDecimal inflationRate = yearToYear
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1));

        BigDecimal adjustedRate = rateOfReturn
                .divide(inflationRate, 10, RoundingMode.HALF_UP);
        BigDecimal investingYears = adjustedRate.pow(years);

        BigDecimal inflationRatePerMonth = yearToYear
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal rateOfReturnPerMonth = rateOfReturnInPercent
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal divided = rateOfReturnPerMonth.add(BigDecimal.valueOf(1))
                .divide(inflationRatePerMonth.add(BigDecimal.valueOf(1)))
                .pow(12 * years)
                .subtract(BigDecimal.valueOf(1))
                .divide(rateOfReturnPerMonth
                        .divide(inflationRatePerMonth
                                .add(BigDecimal.valueOf(1)), 10, RoundingMode.HALF_UP),
                        10, RoundingMode.HALF_UP);

        BigDecimal finalEarnings = presentValue.multiply(investingYears)
                .add(perMonth.multiply(divided));
        return getCalculatorDto(finalEarnings);
    }

    private static CalculatorOutputDto getCalculatorDto(BigDecimal finalEarnings) {
        String info = "In the future your portfolio will be worth %s".formatted(finalEarnings);
        CalculatorOutputDto calculatorOutputDto = new CalculatorOutputDto();
        calculatorOutputDto.setInfo(info);
        calculatorOutputDto.setEarnings(finalEarnings);
        return calculatorOutputDto;
    }

    private BigDecimal getStockValue(User user) {
        List<UserStock> allStocks = userStockRepository.findByUserId(user.getId());
        return allStocks.stream()
                .map(this::getValueForThisStocks)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getValueForThisStocks(UserStock us) {
        GooglePageForStocksWrapper companyInformation = serpApiConfig
                .getCompanyInformation(us.getStock().getStockSymbol());

        BigDecimal pricePerStock = companyInformation.getAnswerBox().getPrice();
        // get currency
        Integer quantity = us.getQuantity();

        return pricePerStock.multiply(BigDecimal.valueOf(quantity));
    }
}
