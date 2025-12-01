package com.lvmp.cli.commands.forex;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "forex",
        description = "Provides the current exchange rate of a specified currency relative to the US dollar. Use subcommands to view returns.\n",
        subcommands = {
                ForexReturn.class
        }
)
public class Forex implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("This is the forex sub-command");
        return 0;
    }
}
