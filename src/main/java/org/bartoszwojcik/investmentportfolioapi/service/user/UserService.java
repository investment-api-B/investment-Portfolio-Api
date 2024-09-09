package org.bartoszwojcik.investmentportfolioapi.service.user;

import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.register.RegistrationRequestDto;

public interface UserService {
    UserDto registration(RegistrationRequestDto requestDto);
}
