package org.bartoszwojcik.investmentportfolioapi.dto.portfolio;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PortfolioValueDto {
    private BigDecimal inCash;
    private BigDecimal inStock;
    private BigDecimal all;
}
