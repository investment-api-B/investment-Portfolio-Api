package org.bartoszwojcik.investmentportfolioapi.mapper;

import org.bartoszwojcik.investmentportfolioapi.config.MapperConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationValue;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface InflationMapper {

    Inflation toInflation(InflationValue inflationValue);

    Inflation toInflation(InflationCountry inflationCountry);

    InflationDto toInflationDto(Inflation inflation);
}
