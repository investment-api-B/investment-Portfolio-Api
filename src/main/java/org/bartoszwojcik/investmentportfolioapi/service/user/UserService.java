package org.bartoszwojcik.investmentportfolioapi.service.user;

import org.bartoszwojcik.investmentportfolioapi.dto.user.UpdateUserRequestDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.register.RegistrationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;

public interface UserService {
    UserDto registration(RegistrationRequestDto requestDto);

    UserDto getMyProfile(User user);

    UserDto updateMyProfile(User user, UpdateUserRequestDto request);

}
