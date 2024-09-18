package org.bartoszwojcik.investmentportfolioapi.dto.stock.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class AnswerBox {
    @JsonProperty("type")
    private String type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("stock")
    private String stock;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("price_movement")
    private PriceMovement priceMovement;

    @JsonProperty("market")
    private Market market;

    @JsonProperty("previous_close")
    private double previousClose;

    @JsonProperty("table")
    private List<TableEntry> table;
}
