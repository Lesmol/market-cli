package com.lvmp.cli.commands.stock;

import com.lvmp.polygon.model.StockReturnsResponse;
import com.lvmp.polygon.service.PolygonService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "return",
        description = "Return the YTD, 6mo and 1mo returns of a stock."
)
public class StockReturn implements Callable<Integer> {

    @Option (names = {"-t", "--ticker"}, required = true)
    private String ticker;

    @Override
    public Integer call() {
        try {
            StockReturnsResponse result = PolygonService.getReturns(ticker);

            System.out.println("Current price: " + result.currentPrice);
            System.out.println("1 Month Return: " + result.oneMonthReturn + "%");
            System.out.println("6 Month Return: " + result.sixMonthReturn + "%");
            System.out.println("Year-To-Date: " + result.yearToDate + "%");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 1;
        }
        return 0;
    }
}
