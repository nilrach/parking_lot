package com.gojek.parkinglot.command;

import java.util.LinkedList;
import java.util.List;

public class Command {
    private final CommandType type;
    private final LinkedList<String> params;

    public Command(CommandType type, LinkedList<String> params) {
        this.type = type;
        this.params = params;
    }

    public CommandType getType() {
        return type;
    }

    public LinkedList<String> getParams() {
        return params;
    }
}
