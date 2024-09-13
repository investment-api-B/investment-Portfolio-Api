package org.bartoszwojcik.investmentportfolioapi.dto.inflation;

import jakarta.validation.constraints.PositiveOrZero;

public class InflationDto {
    @PositiveOrZero
    private double yearToYear;
}
