package org.bartoszwojcik.investmentportfolioapi.service.portfolio;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.user.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;

public interface PortfolioService {
    List<PortfolioDto> getMyPortfolio(User user);

    PortfolioDto buyStock(User user, Long stockId);

    PortfolioDto sellStock(User user, Long stockId);
}
