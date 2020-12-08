package com.gojek.parkinglot.command;

import java.util.List;

public class Command {
    private final CommandType type;
    private final List<String> params;

    public Command(CommandType type, List<String> params) {
        this.type = type;
        this.params = params;
    }

    public CommandType getType() {
        return type;
    }

    public List<String> getParams() {
        return params;
    }
}
