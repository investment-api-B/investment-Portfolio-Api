package org.bartoszwojcik.investmentportfolioapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.login.LoginRequestDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.login.LoginResponseDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.register.RegistrationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(
            @RequestBody @Valid RegistrationRequestDto requestDto) {
        return userService.registration(requestDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(
            @RequestBody @Valid LoginRequestDto requestDto) {
        return null;
    }
}
