package com.gojek.parkinglot.input.parser;

import com.gojek.parkinglot.model.Command;
import com.gojek.parkinglot.model.CommandType;

import java.util.Arrays;
import java.util.Optional;

public class InputParser {

    private InputParser() {

    }

    public static InputParser getInstance() {
        return new InputParser();
    }

    public Command parseLine(String line) {
        String[] commandTokens = line.split(" ");
        Optional<CommandType> commandType = CommandType.ofType(commandTokens[0]);
        if (commandType.isPresent()) {
            return new Command(commandType.get(), Arrays.asList(commandTokens));
        } else {
            throw new IllegalStateException("Unable to parse input " + line);
        }
    }
}
