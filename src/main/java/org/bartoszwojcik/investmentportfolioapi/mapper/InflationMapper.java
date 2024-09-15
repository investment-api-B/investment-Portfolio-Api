package org.bartoszwojcik.investmentportfolioapi.mapper;

import org.bartoszwojcik.investmentportfolioapi.config.MapperConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface InflationMapper {

    Inflation toInflation(InflationRequestDto requestDto);

    InflationDto toInflationDto(Inflation inflation);
}
