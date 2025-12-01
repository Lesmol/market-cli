package com.lvmp.cli.commands.stock;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "dcf",
        description = "Discounted cash-flow calculations for a stock."
)
public class DiscountedCashFlow implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("Discounted cash-flow calculations.");
        return 0;
    }
}
