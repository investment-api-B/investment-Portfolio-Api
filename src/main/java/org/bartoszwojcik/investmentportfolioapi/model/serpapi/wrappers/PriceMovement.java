package org.bartoszwojcik.investmentportfolioapi.model.serpapi.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PriceMovement {
    @JsonProperty("price")
    private double price;

    @JsonProperty("percentage")
    private double percentage;

    @JsonProperty("movement")
    private String movement;

    @JsonProperty("date")
    private String date;
}
