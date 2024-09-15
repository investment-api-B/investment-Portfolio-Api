package org.bartoszwojcik.investmentportfolioapi.dto.inflation;

import lombok.Data;

@Data
public class InflationDto {
    private String countryName;
    private Double yearToYear;
}
