package org.bartoszwojcik.investmentportfolioapi.service.portfolio;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.dto.portfolio.PortfolioValueDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;

public interface PortfolioService {
    List<PortfolioDto> getMyPortfolio(User user);

    PortfolioDto buyStock(User user, Long stockId, Integer quantity);

    PortfolioDto sellStock(User user, Long stockId, Integer quantity);

    PortfolioValueDto getMyPortfolioValue(User user);
}
