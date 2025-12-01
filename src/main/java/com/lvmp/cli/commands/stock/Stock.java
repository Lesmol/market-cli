package com.lvmp.cli.commands.stock;

import com.lvmp.polygon.model.FinancialItem;
import com.lvmp.polygon.model.RatiosResponse;
import com.lvmp.polygon.service.PolygonService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.concurrent.Callable;

@Command (
        name = "stock",
        description = "Provides market and fundamental data for a specified stock ticker. Use subcommands to retrieve returns, earnings, or perform valuation analysis.",
        subcommands = {
                StockReturn.class,
                StockEarnings.class,
                DiscountedCashFlow.class
        }
)
public class Stock implements Callable<Integer> {

    @Option( names = {"-t", "--ticker"}, required = true)
    private String ticker;

    @Override
    public Integer call() {
        try {
            List<FinancialItem> result = PolygonService.getRatios(ticker);
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 1;
        }
        return 0;
    }
}
