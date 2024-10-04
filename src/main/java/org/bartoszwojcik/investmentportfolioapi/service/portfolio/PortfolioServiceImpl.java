package org.bartoszwojcik.investmentportfolioapi.service.portfolio;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.config.SerpApiConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.dto.portfolio.PortfolioValueDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.internal.GooglePageForStocksWrapper;
import org.bartoszwojcik.investmentportfolioapi.exception.StockNotFountException;
import org.bartoszwojcik.investmentportfolioapi.mapper.PortfolioMapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Stock;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.model.classes.UserStock;
import org.bartoszwojcik.investmentportfolioapi.repository.stock.StockRepository;
import org.bartoszwojcik.investmentportfolioapi.repository.stock.user.UserStockRepository;
import org.bartoszwojcik.investmentportfolioapi.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final SerpApiConfig serpApiConfig;
    private final StockRepository stockRepository;
    private final UserStockRepository userStockRepository;
    private final UserRepository userRepository;
    private final PortfolioMapper portfolioMapper;

    @Override
    public List<PortfolioDto> getMyPortfolio(User user) {
        return userStockRepository.findByUserId(user.getId()).stream()
                .map(portfolioMapper::toPortfolioDto)
                .toList();
    }

    @Override
    public PortfolioDto buyStock(User user, Long stockId, Integer quantity) {
        Stock stock = getStock(stockId);
        BigDecimal total = getTotalForStocks(quantity, stock);

        if (user.getCash().compareTo(total) < 0) {
            throw new StockNotFountException(
                    "User cash is less than total " + total
            );
        }

        Optional<UserStock> buyUserStock = userStockRepository
                .findByUserId(user.getId())
                .stream()
                .filter(us -> us.getStock().getId().equals(stockId))
                .findFirst();

        user.setCash(user.getCash().subtract(total));
        userRepository.save(user);

        if (buyUserStock.isPresent()) {
            UserStock userStock = buyUserStock.get();
            userStock.setQuantity(userStock.getQuantity() + quantity);
            return portfolioMapper.toPortfolioDto(
                    userStockRepository.save(userStock)
            );
        }

        UserStock userStock = new UserStock();
        userStock.setUser(user);
        userStock.setStock(stock);
        userStock.setQuantity(quantity);

        return portfolioMapper.toPortfolioDto(
                userStockRepository.save(userStock)
        );
    }

    @Override
    public PortfolioDto sellStock(User user, Long stockId, Integer quantity) {
        Stock stock = getStock(stockId);
        BigDecimal total = getTotalForStocks(quantity, stock);

        Optional<UserStock> sellUserStock = userStockRepository
                .findByUserId(user.getId())
                .stream()
                .filter(us -> us.getStock().getId().equals(stockId))
                .findFirst();

        if (sellUserStock.isPresent()) {
            UserStock userStock = sellUserStock.get();

            if (userStock.getQuantity() < quantity) {
                throw new StockNotFountException(
                        "User don't have enough stocks: " + userStock.getQuantity()
                );
            }

            user.setCash(user.getCash().add(total));
            userRepository.save(user);

            userStock.setQuantity(userStock.getQuantity() - quantity);
            return portfolioMapper.toPortfolioDto(
                    userStockRepository.save(userStock)
            );
        }

        user.setCash(user.getCash().add(total));
        userRepository.save(user);

        UserStock userStock = new UserStock();
        userStock.setUser(user);
        userStock.setStock(stock);
        userStock.setQuantity(quantity);

        return portfolioMapper.toPortfolioDto(
                userStockRepository.save(userStock)
        );
    }

    private BigDecimal getTotalForStocks(Integer quantity, Stock stock) {
        GooglePageForStocksWrapper companyInformation = serpApiConfig
                .getCompanyInformation(stock.getStockSymbol());

        BigDecimal pricePerStock = companyInformation.getAnswerBox().getPrice();
        // get currency
        BigDecimal total = pricePerStock.multiply(BigDecimal.valueOf(quantity));
        return total;
    }

    private Stock getStock(Long stockId) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(
                () -> new EntityNotFoundException(
                        "Stock not found for id " + stockId
                )
        );
        return stock;
    }

    @Override
    public PortfolioValueDto getMyPortfolioValue(User user) {
        List<UserStock> allStocks = userStockRepository.findByUserId(user.getId());
        BigDecimal sumStocks = allStocks.stream()
                .map(this::getValueForThisStocks)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal cash = user.getCash();

        BigDecimal add = cash.add(sumStocks);

        PortfolioValueDto portfolioValueDto = new PortfolioValueDto();
        portfolioValueDto.setAll(add);
        portfolioValueDto.setInCash(cash);
        portfolioValueDto.setInStock(sumStocks);

        return portfolioValueDto;
    }

    private BigDecimal getValueForThisStocks(UserStock us) {
        GooglePageForStocksWrapper companyInformation = serpApiConfig
                .getCompanyInformation(us.getStock().getStockSymbol());

        BigDecimal pricePerStock = companyInformation.getAnswerBox().getPrice();
        // get currency
        Integer quantity = us.getQuantity();

        return pricePerStock.multiply(BigDecimal.valueOf(quantity));
    }
}
