package com.lvmp.cli.commands;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "earnings",
        description = "Returns the latest earnings results of a stock."
)
public class StockEarnings implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Stock earnings call results.");
        return 0;
    }
}
