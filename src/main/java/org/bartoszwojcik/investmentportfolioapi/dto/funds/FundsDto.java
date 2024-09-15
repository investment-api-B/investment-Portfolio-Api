package org.bartoszwojcik.investmentportfolioapi.dto.funds;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class FundsDto {
    private Long id;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;
}
