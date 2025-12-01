package com.lvmp.cli;

import com.lvmp.cli.commands.forex.Forex;
import com.lvmp.cli.commands.stock.Stock;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "market",
        mixinStandardHelpOptions = true,
        version = "0.01",
        description = "Interact with Financial Market Data seamlessly from the command line.",
        subcommands = {
                Stock.class,
                Forex.class
        }
)
public class RootCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine.usage(this, System.out);
        return 0;
    }

}
