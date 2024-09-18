package org.bartoszwojcik.investmentportfolioapi.service.stock;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.config.SerpApiConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.external.StockDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.internal.GooglePageForStocksWrapper;
import org.bartoszwojcik.investmentportfolioapi.mapper.StockMapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Stock;
import org.bartoszwojcik.investmentportfolioapi.repository.stock.StockRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final SerpApiConfig serpApiConfig;
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
        GooglePageForStocksWrapper companyInformation = serpApiConfig
                .getCompanyInformation(stockSymbol);

        Stock stock = new Stock();
        stock.setStockSymbol(companyInformation.getAnswerBox().getStock());
        stockRepository.save(stock);
        String companyName = companyInformation.getAnswerBox().getTitle();
        String companyTicker = companyInformation.getAnswerBox().getStock();
        BigDecimal companyPrice = companyInformation.getAnswerBox().getPrice();
        String currency = companyInformation.getAnswerBox().getCurrency();
        return companyName + " with ticker: " + companyTicker
                + " and price: " + companyPrice + " " + currency + " added to database";
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
