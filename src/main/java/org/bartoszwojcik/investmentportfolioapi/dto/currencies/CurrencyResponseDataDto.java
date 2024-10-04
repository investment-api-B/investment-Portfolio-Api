package org.bartoszwojcik.investmentportfolioapi.dto.currencies;

import java.util.Map;
import lombok.Data;

@Data
public class CurrencyResponseDataDto {
    private Map<String, CurrencyRateData> data;
}
