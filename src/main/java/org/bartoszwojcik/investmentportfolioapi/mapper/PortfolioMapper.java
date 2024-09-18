package org.bartoszwojcik.investmentportfolioapi.mapper;

import org.bartoszwojcik.investmentportfolioapi.config.MapperConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.UserStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PortfolioMapper {
    @Mapping(source = "stock.stockSymbol", target = "ticker")
    PortfolioDto toPortfolioDto(UserStock userStock);
}
