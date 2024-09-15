package org.bartoszwojcik.investmentportfolioapi.service.inflation;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationCountry;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationDto;
import org.bartoszwojcik.investmentportfolioapi.dto.inflation.InflationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.mapper.InflationMapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.bartoszwojcik.investmentportfolioapi.repository.inflation.InflationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InflationServiceImpl implements InflationService {
    private final InflationRepository inflationRepository;
    private final InflationMapper inflationMapper;

    @Override
    public List<InflationDto> getInflationS() {
        return inflationRepository.findAll().stream()
                .map(inflationMapper::toInflationDto)
                .toList();
    }

    @Override
    public InflationDto getInflation(InflationCountry country) {
        Inflation inflation = inflationRepository.findByCountryName(country.countryName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Could not find Inflation with country name " + country.countryName()
                                + " please add information about this inflation"
                )
        );
        return inflationMapper.toInflationDto(
                inflation
        );
    }

    @Override
    public InflationDto createInflation(InflationRequestDto requestDto) {
        return inflationMapper.toInflationDto(
                inflationRepository.save(
                        inflationMapper.toInflation(requestDto)));
    }

    @Override
    public InflationDto updateInflation(InflationRequestDto requestDto) {
        Inflation inflation = inflationRepository.findByCountryName(requestDto.countryName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Could not find Inflation with country name " + requestDto.countryName()
                                + " please add information about this inflation"
                )
        );
        inflation.setYearToYear(requestDto.yearToYear());
        return inflationMapper.toInflationDto(
                inflationRepository.save(inflation)
        );
    }
}
