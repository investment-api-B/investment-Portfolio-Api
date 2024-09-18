package org.bartoszwojcik.investmentportfolioapi.dto.stock.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TableEntry {
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private Object value;
}
