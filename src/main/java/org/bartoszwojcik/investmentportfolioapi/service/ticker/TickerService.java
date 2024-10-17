package org.bartoszwojcik.investmentportfolioapi.service.ticker;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.external.StockDto;

public interface TickerService {
    List<StockDto> getTickers();

    String addTicker(String stockSymbol);

    String addTicker(String stockSymbol, boolean force);
}
