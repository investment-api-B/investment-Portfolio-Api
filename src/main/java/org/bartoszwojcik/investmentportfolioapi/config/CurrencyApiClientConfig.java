package org.bartoszwojcik.investmentportfolioapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.currencies.CurrencyRateData;
import org.bartoszwojcik.investmentportfolioapi.dto.currencies.CurrencyResponseDataDto;
import org.bartoszwojcik.investmentportfolioapi.exception.PaymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyApiClientConfig {
    private static final String BASE_URL = "https://api.currencyapi.com/v3/latest?apikey=%s&currencies=%s&base_currency=%s";
    private final ObjectMapper objectMapper;
    @Value("${currency.api.key}")
    private String apiKey;

    public CurrencyRateData getCurrenciesRates(String currency) {
        HttpClient httpClient = HttpClient.newHttpClient();

        String url = BASE_URL.formatted(apiKey, "PLN", currency);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CurrencyResponseDataDto readValue = objectMapper
                    .readValue(response.body(), CurrencyResponseDataDto.class);
            return readValue.getData().get("PLN");
        } catch (IOException | InterruptedException e) {
            throw new PaymentException(e);
        }
    }
}
