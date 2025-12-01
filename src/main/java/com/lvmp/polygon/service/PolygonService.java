package com.lvmp.polygon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lvmp.persistance.KeyPersistence;
import com.lvmp.polygon.exception.PolygonException;
import com.lvmp.polygon.model.FinancialItem;
import com.lvmp.polygon.model.RatiosResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PolygonService {
    public static List<FinancialItem> getRatios(String ticker) throws IOException, PolygonException {
        HttpClient client = HttpClient.newHttpClient();
        String API_KEY = KeyPersistence.getApiKey();
        String url = "https://api.massive.com/stocks/financials/v1/ratios?ticker=%s&limit=100&sort=ticker.asc&apiKey=%s";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, ticker, API_KEY)))
                .GET()
                .build();

        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        ObjectMapper mapper = new ObjectMapper();
        RatiosResponse result = mapper.readValue(response, RatiosResponse.class);

        if (!result.status.equals("OK")) {
            throw new PolygonException(result.error == null ? result.message : result.error);
        }

        return result.results;
    }
}
