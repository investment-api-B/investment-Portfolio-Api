package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UpdateUserRequestDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.portfolio.PortfolioValueDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @Operation(summary = "Get my profile info")
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getMyProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getMyProfile(user);
    }

    @Operation(summary = "Update my profile info")
    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateMyProfile(Authentication authentication,
                                   @RequestBody @Valid UpdateUserRequestDto request) {
        User user = (User) authentication.getPrincipal();
        return userService.updateMyProfile(user, request);
    }

    @Operation
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PortfolioValueDto getMyPortfolioValue() {
        return null;
    }
}
