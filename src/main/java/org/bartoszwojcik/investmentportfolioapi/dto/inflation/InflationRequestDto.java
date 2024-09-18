package org.bartoszwojcik.investmentportfolioapi.dto.inflation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record InflationRequestDto(
        @NotBlank
        String countryName,
        @PositiveOrZero
        Double yearToYear
) {
}
