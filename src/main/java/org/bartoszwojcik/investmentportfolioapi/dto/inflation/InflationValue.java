package org.bartoszwojcik.investmentportfolioapi.dto.inflation;

import jakarta.validation.constraints.PositiveOrZero;

public record InflationValue(
        @PositiveOrZero
        double yearToYear
) {
}
