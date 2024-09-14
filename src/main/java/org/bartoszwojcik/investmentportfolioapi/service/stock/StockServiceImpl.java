package org.bartoszwojcik.investmentportfolioapi.service.stock;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockSymbol;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Override
    public List<StockDto> getStocks() {
        return List.of();
    }

    @Override
    public StockDto addStock(StockSymbol stockSymbol) {
        return null;
    }
}
