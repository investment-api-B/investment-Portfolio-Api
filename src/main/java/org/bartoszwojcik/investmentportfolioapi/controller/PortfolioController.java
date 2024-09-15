package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.portfolio.PortfolioDto;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.service.portfolio.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    @Operation(summary = "show stocks in my portfolio")
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioDto> getMyPortfolio(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return portfolioService.getMyPortfolio(principal);
    }

    @Operation(summary = "buy stocks")
    @PostMapping("/buy")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioDto buyStock(Authentication authentication,
                                 @PathVariable Long stockId) {
        User principal = (User) authentication.getPrincipal();
        return portfolioService.buyStock(principal, stockId);
    }

    @Operation(summary = "sell stocks")
    @PutMapping("/sell")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDto sellStock(Authentication authentication,
                                  @PathVariable Long stockId) {
        User principal = (User) authentication.getPrincipal();
        return portfolioService.sellStock(principal, stockId);
    }
}
