package org.bartoszwojcik.investmentportfolioapi.service.stock;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockDto;

public interface StockService {
    List<StockDto> getStocks();

    String addStock(String stockSymbol);

    String addStock(String stockSymbol, boolean force);
}
