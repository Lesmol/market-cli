package com.lvmp.cli.commands;

import picocli.CommandLine.Command;

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
    @Override
    public Integer call() throws Exception {
        System.out.println("This is the stock sub-command");
        return 0;
    }
}
