package org.bartoszwojcik.investmentportfolioapi.dto.inflation;

import jakarta.validation.constraints.NotBlank;

public record InflationCountry(
        @NotBlank
        String countryName
) {
}
