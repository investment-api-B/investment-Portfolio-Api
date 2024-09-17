package org.bartoszwojcik.investmentportfolioapi.dto.stock;

import lombok.Data;

@Data
public class StockDto {
    private Long id;
    private String stockSymbol;
}
