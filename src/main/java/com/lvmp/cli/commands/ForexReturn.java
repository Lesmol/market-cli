package com.lvmp.cli.commands;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "return",
        description = "Return the YTD, 6mo and 1mo returns of a stock."
)
public class ForexReturn implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("This is the forex return sub-command.");
        return 0;
    }
}
