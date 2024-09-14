package org.bartoszwojcik.investmentportfolioapi.service.stock;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockSymbol;

public interface StockService {
    List<StockDto> getStocks();

    StockDto addStock(StockSymbol stockSymbol);
}
