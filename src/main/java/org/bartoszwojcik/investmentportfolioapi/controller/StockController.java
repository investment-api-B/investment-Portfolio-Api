package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockDto;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.StockSymbol;
import org.bartoszwojcik.investmentportfolioapi.service.stock.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @Operation
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockDto> getStocks() {
        return null;
    }

    @Operation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockDto addStock(@RequestBody StockSymbol stockSymbol) {
        return null;
    }

    @Operation
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockDto> updateStocksPrice() {
        return null;
    }
}
