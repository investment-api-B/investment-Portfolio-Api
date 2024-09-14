package org.bartoszwojcik.investmentportfolioapi.service.inflation;

import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationValue;

public interface InflationService {
    InflationDto getInflation(InflationCountry country);

    InflationDto createInflation(InflationCountry country, InflationValue inflationValue);

    InflationDto updateInflation(InflationCountry country, InflationValue inflationValue);
}
