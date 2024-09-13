package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.service.inflation.InflationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inflation")
@RequiredArgsConstructor
public class InflationController {
    private final InflationService inflationService;

    @Operation
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InflationDto getInflation(@Valid @RequestBody InflationCountry inflationCountry) {
        return null;
    }

    @Operation
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InflationDto createInflation(@Valid @RequestBody InflationCountry inflationCountry,
                                        @Valid @RequestBody InflationDto inflationDto) {
        return null;
    }

    @Operation
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public InflationDto updateInflation(@Valid @RequestBody InflationCountry inflationCountry,
                                        @Valid @RequestBody InflationDto inflationDto) {
        return null;
    }
}
