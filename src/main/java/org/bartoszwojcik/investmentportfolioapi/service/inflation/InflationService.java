package org.bartoszwojcik.investmentportfolioapi.service.inflation;

import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;

public interface InflationService {
    InflationDto getInflation(InflationCountry country);

    InflationDto createInflation(InflationCountry country, InflationDto inflation);

    InflationDto updateInflation(InflationCountry country, InflationDto inflation);
}
