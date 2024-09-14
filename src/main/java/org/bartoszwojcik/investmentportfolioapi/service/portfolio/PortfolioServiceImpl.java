package org.bartoszwojcik.investmentportfolioapi.service.portfolio;

import java.util.List;
import org.bartoszwojcik.investmentportfolioapi.dto.user.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Override
    public List<PortfolioDto> getMyPortfolio(User user) {
        return List.of();
    }

    @Override
    public PortfolioDto buyStock(User user, Long stockId) {
        return null;
    }

    @Override
    public PortfolioDto sellStock(User user, Long stockId) {
        return null;
    }
}
