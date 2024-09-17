package org.bartoszwojcik.investmentportfolioapi.service.stock;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockDto;
import org.bartoszwojcik.investmentportfolioapi.mapper.StockMapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Stock;
import org.bartoszwojcik.investmentportfolioapi.repository.stock.StockRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Override
    public List<StockDto> getStocks() {
        return stockRepository.findAll().stream()
                .map(stockMapper::toStockDto)
                .toList();
    }

    @Override
    public String addStock(String stockSymbol) {
        return null;
    }

    @Override
    public String addStock(String stockSymbol, boolean force) {
        Stock stock = new Stock();
        stock.setStockSymbol(stockSymbol);
        if (force) {
            stockRepository.save(
                    stock
            );
        }
        return "stock added with ticker:" + stockSymbol;
    }
}
