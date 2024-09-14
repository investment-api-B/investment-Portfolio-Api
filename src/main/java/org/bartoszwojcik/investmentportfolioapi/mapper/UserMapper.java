package org.bartoszwojcik.investmentportfolioapi.mapper;

import org.bartoszwojcik.investmentportfolioapi.config.MapperConfig;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.login.LoginRequestDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.portfolio.PortfolioValueDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.register.RegistrationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UserDto userDto);

    User toUser(RegistrationRequestDto registrationRequestDto);

    User toUser(LoginRequestDto loginRequestDto);

    User toUser(PortfolioValueDto portfolioValueDto);

    PortfolioDto toPortfolioDto(User user);
}
