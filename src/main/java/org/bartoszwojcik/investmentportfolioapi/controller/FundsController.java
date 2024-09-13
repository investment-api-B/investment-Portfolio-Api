package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.service.funds.FundsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funds")
@RequiredArgsConstructor
public class FundsController {
    private final FundsService fundsService;

    @Operation
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto addFunds(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return null;
    }

    @Operation
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto takeFunds(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return null;
    }
}
