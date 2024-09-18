package org.bartoszwojcik.investmentportfolioapi.dto.portfolio;

import lombok.Data;

@Data
public class PortfolioDto {
    private String ticker;
    private Integer quantity;
}
