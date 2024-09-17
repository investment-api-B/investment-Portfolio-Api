package org.bartoszwojcik.investmentportfolioapi.dto.stock.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PriceMovement {
    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("percentage")
    private BigDecimal percentage;

    @JsonProperty("movement")
    private String movement;

    @JsonProperty("date")
    private String date;
}
