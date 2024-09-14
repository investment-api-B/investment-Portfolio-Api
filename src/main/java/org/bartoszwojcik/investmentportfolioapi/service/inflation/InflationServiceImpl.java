package org.bartoszwojcik.investmentportfolioapi.service.inflation;

import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationValue;
import org.springframework.stereotype.Service;

@Service
public class InflationServiceImpl implements InflationService {
    @Override
    public InflationDto getInflation(InflationCountry country) {
        return null;
    }

    @Override
    public InflationDto createInflation(InflationCountry country, InflationValue inflationValue) {
        return null;
    }

    @Override
    public InflationDto updateInflation(InflationCountry country, InflationValue inflationValue) {
        return null;
    }
}
