package org.bartoszwojcik.investmentportfolioapi.service.inflation;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationRequestDto;

public interface InflationService {
    List<InflationDto> getInflationS();

    InflationDto getInflation(InflationCountry country);

    InflationDto createInflation(InflationRequestDto requestDto);

    InflationDto updateInflation(InflationRequestDto requestDto);
}
