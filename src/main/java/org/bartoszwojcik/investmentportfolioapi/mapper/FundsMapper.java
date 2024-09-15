package org.bartoszwojcik.investmentportfolioapi.mapper;

import org.bartoszwojcik.investmentportfolioapi.config.MapperConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.funds.FundsDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Funds;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface FundsMapper {
    FundsDto toFundsDto(Funds funds);

    Funds toFunds(FundsDto fundsDto);
}
