package org.bartoszwojcik.investmentportfolioapi.mapper;

import org.bartoszwojcik.investmentportfolioapi.config.MapperConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockSymbol;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Stock;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface StockMapper {

    Stock toStock(StockSymbol symbol);

    StockDto toStockDto(Stock stock);

}
