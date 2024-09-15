package org.bartoszwojcik.investmentportfolioapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.service.inflation.InflationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "get inflation(Year-To-Year) from your country")
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public InflationDto getInflation(@Valid @RequestBody InflationCountry inflationCountry) {
        return inflationService.getInflation(inflationCountry);
    }

    @Operation(summary = "get every inflation(Year-To-Year) from our database")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<InflationDto> getInflationS() {
        return inflationService.getInflationS();
    }

    @Operation(summary = "add new country with inflation for you")
    @PutMapping("/add-new")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public InflationDto createInflation(@Valid @RequestBody InflationRequestDto requestDto) {
        return inflationService.createInflation(requestDto);
    }

    @Operation(summary = "update information about inflation & country")
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public InflationDto updateInflation(@Valid @RequestBody InflationRequestDto requestDto) {
        return inflationService.updateInflation(requestDto);
    }
}
