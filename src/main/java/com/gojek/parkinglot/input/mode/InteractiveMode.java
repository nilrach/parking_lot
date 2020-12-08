package com.gojek.parkinglot.input.mode;

import com.gojek.parkinglot.command.parser.CommandParser;

public class InteractiveMode {
    private final CommandParser commandParser;

    private InteractiveMode(CommandParser commandParser) {
        this.commandParser = commandParser;

    }

    public static InteractiveMode getInstance(CommandParser commandParser) {
        return new InteractiveMode(commandParser);
    }

    public void execute() {

    }
}
