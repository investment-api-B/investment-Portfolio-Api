package org.bartoszwojcik.investmentportfolioapi.model.serpapi.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GooglePageForStocksWrapper {
    @JsonProperty("answer_box")
    private AnswerBox answerBox;
}
