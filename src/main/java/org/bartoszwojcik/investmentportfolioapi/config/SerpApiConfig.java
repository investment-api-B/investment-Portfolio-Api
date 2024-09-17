package org.bartoszwojcik.investmentportfolioapi.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.bartoszwojcik.investmentportfolioapi.exception.StockNotFountException;
import org.bartoszwojcik.investmentportfolioapi.model.serpapi.wrappers.GooglePageForStocksWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import serpapi.GoogleSearch;
import serpapi.SerpApiSearchException;

@Configuration
public class SerpApiConfig {
    private static final Map<String, String> configMap = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${serp.api.secret.key}")
    private String secretKey;

    public SerpApiConfig() {
        configMap.put("apiKey", secretKey);
        configMap.put("engine", "google_finance");
    }

    public GooglePageForStocksWrapper getCompanyInformation(String ticker) {
        String company = ticker + ":WSE";
        configMap.put("q", company);

        GoogleSearch search = new GoogleSearch(configMap);

        return getInfo(search);
    }

    private GooglePageForStocksWrapper getInfo(GoogleSearch search) {
        try {
            String jsonResult = search.getJson().toString();
            return objectMapper.readValue(jsonResult, GooglePageForStocksWrapper.class);
        } catch (SerpApiSearchException e) {
            throw new StockNotFountException(
                    "Could not get company information from Google Search.", e
            );
        } catch (JsonProcessingException e) {
            throw new StockNotFountException(
                    "Could not process json result.", e
            );
        }
    }
}
