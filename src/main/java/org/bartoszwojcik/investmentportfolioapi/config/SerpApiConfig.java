package org.bartoszwojcik.investmentportfolioapi.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.stock.internal.GooglePageForStocksWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import serpapi.GoogleSearch;
import serpapi.SerpApiSearchException;

@Configuration
@RequiredArgsConstructor
public class SerpApiConfig {
    private static final Map<String, String> parameter = new HashMap<>();
    private final ObjectMapper objectMapper;
    @Value("${serp.api.secret.key}")
    private String secretKey;

    public GooglePageForStocksWrapper getCompanyInformation(String ticker) {
        parameter.put("q", ticker + ":WSE");
        parameter.put("api_key", secretKey);
        parameter.put("engine", "google_finance");

        GoogleSearch search = new GoogleSearch(parameter);

        JsonObject results = null;

        try {
            results = search.getJson();
            return objectMapper.readValue(results.toString(),
                    GooglePageForStocksWrapper.class);
        } catch (JsonProcessingException | SerpApiSearchException e) {
            throw new RuntimeException(e);
        }
    }

}
