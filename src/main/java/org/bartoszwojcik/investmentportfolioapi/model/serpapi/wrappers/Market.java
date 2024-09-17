package org.bartoszwojcik.investmentportfolioapi.model.serpapi.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Market {
    @JsonProperty("closed")
    private boolean closed;

    @JsonProperty("date")
    private String date;
}
