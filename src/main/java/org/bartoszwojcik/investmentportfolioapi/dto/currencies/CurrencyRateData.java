package org.bartoszwojcik.investmentportfolioapi.dto.currencies;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CurrencyRateData {
    private String code;
    private BigDecimal value;
}
