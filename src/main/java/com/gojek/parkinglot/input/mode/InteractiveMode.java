package com.gojek.parkinglot.input.mode;

import com.gojek.parkinglot.command.CommandExecutor;
import com.gojek.parkinglot.command.parser.CommandParser;

public class InteractiveMode {
    private final CommandParser commandParser;

    private InteractiveMode(CommandParser commandParser, CommandExecutor commandExecutor) {
        this.commandParser = commandParser;

    }

    public static InteractiveMode getInstance(CommandParser commandParser, CommandExecutor commandExecutor) {
        return new InteractiveMode(commandParser, commandExecutor);
    }

    public void execute() {

    }
}
