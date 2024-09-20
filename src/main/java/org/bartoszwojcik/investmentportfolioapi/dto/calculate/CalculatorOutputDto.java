package org.bartoszwojcik.investmentportfolioapi.dto.calculate;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CalculatorOutputDto {
    private String info;
    private BigDecimal earnings;
}
