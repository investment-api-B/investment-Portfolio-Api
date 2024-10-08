package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.external.StockDto;
import org.bartoszwojcik.investmentportfolioapi.service.stock.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @Operation(summary = "get available stocks in this app")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<StockDto> getStocks() {
        return stockService.getStocks();
    }

    @Operation(summary = "add new stock to our app")
    @PostMapping("/add-new")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String addStock(@RequestParam String stockSymbol) {
        return stockService.addStock(stockSymbol);
    }

    @Operation(summary = "add new stock to our app without checking if it is correct ticker")
    @PostMapping("/add-new/force")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String addStockWithoutChecking(@RequestParam String stockSymbol) {
        return stockService.addStock(stockSymbol, true);
    }
}
