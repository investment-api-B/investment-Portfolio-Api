package org.bartoszwojcik.investmentportfolioapi.model.serpapi.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TableEntry {
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private Object value;
}
