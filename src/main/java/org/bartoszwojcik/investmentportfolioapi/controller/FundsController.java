package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.service.funds.FundsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "add funds to your account")
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto addFunds(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return fundsService.addFunds(principal);
    }

    @Operation(summary = "take funds from your account")
    @PostMapping("/take")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto takeFunds(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return fundsService.takeFunds(principal);
    }
}
