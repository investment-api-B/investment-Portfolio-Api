package org.bartoszwojcik.investmentportfolioapi.service.funds;

import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;

public interface FundsService {
    UserDto addFunds(User user);

    UserDto takeFunds(User user);
}
