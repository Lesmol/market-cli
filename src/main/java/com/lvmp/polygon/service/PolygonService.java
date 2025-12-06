package com.lvmp.polygon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lvmp.persistance.KeyPersistence;
import com.lvmp.polygon.exception.PolygonException;
import com.lvmp.polygon.model.DailyTickerSummaryResponse;
import com.lvmp.polygon.model.FinancialItem;
import com.lvmp.polygon.model.RatiosResponse;
import com.lvmp.polygon.model.StockReturnsResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class PolygonService {
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(30))
            .build();

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<FinancialItem> getRatios(String ticker) throws IOException, PolygonException, InterruptedException {
        String API_KEY = KeyPersistence.getApiKey();
        String url = "https://api.massive.com/stocks/financials/v1/ratios?ticker=%s&limit=100&sort=ticker.asc&apiKey=%s";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, ticker, API_KEY)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        RatiosResponse result = mapper.readValue(response.body(), RatiosResponse.class);

        if (!result.status.equals("OK")) {
            throw new PolygonException(result.error == null ? result.message : result.error);
        }

        return result.results;
    }

    public static StockReturnsResponse getReturns(String ticker) throws IOException, InterruptedException, PolygonException {
        String API_KEY = KeyPersistence.getApiKey();
        String url = "https://api.massive.com/v1/open-close/%s/%s?adjusted=true&apiKey=%s";

        HttpRequest presentRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, ticker, getDate(LocalDate.now()), API_KEY)))
                .GET()
                .build();

        HttpRequest oneMonthPriorRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, ticker, getDate(LocalDate.now().minusMonths(1)), url)))
                .GET()
                .build();

        HttpRequest sixMonthsPriorRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, ticker, getDate(LocalDate.now().minusMonths(6)), url)))
                .GET()
                .build();

        HttpRequest startOfYearRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, ticker, getDate(LocalDate.now().withDayOfYear(1)), url)))
                .GET()
                .build();

        HttpResponse<String> presentResponse = client.send(presentRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> oneMonthPriorResponse = client.send(oneMonthPriorRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> sixMonthsPriorResponse = client.send(sixMonthsPriorRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> startOfYearResponse = client.send(startOfYearRequest, HttpResponse.BodyHandlers.ofString());

        DailyTickerSummaryResponse presentResult = mapper.readValue(presentResponse.body(), DailyTickerSummaryResponse.class);
        DailyTickerSummaryResponse oneMonthPriorResult = mapper.readValue(oneMonthPriorResponse.body(), DailyTickerSummaryResponse.class);
        DailyTickerSummaryResponse sixMonthsPriorResult = mapper.readValue(sixMonthsPriorResponse.body(), DailyTickerSummaryResponse.class);
        DailyTickerSummaryResponse startOfYearResult = mapper.readValue(startOfYearResponse.body(), DailyTickerSummaryResponse.class);

        if (!"OK".equals(presentResult.status)) {
            throw new PolygonException(presentResult.error == null ? presentResult.message : presentResult.error);
        } else if (!"OK".equals(oneMonthPriorResult.status)) {
            throw new PolygonException(oneMonthPriorResult.error == null ? oneMonthPriorResult.message : oneMonthPriorResult.error);
        } else if (!"OK".equals(sixMonthsPriorResult.status)) {
            throw new PolygonException(sixMonthsPriorResult.error == null ? sixMonthsPriorResult.message : sixMonthsPriorResult.error);
        } else if (!"OK".equals(startOfYearResult.status)) {
            throw new PolygonException(startOfYearResult.error == null ? startOfYearResult.message : startOfYearResult.error);
        }

        return getReturn(
                Double.parseDouble(presentResult.close),
                Double.parseDouble(oneMonthPriorResult.close),
                Double.parseDouble(sixMonthsPriorResult.close),
                Double.parseDouble(startOfYearResult.close)
        );
    }

    private static StockReturnsResponse getReturn(double present, double oneMonth, double sixMonths, double startOfYear) {
        return StockReturnsResponse.builder()
                .currentPrice(present)
                .oneMonthReturn(((present - oneMonth) / oneMonth) * 100)
                .sixMonthReturn(((present - sixMonths) / sixMonths) * 100)
                .yearToDate(((present - startOfYear) / startOfYear) * 100)
                .build();
    }

    private static String getDate(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case DayOfWeek.SATURDAY -> date.minusDays(1);
            case DayOfWeek.SUNDAY -> date.minusDays(2);
        }

        return date.toString();
    }
}
