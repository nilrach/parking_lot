package com.gojek.parkinglot.command.parser;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandType;

import java.util.LinkedList;
import java.util.Optional;

public class CommandParser {

    private CommandParser() {

    }

    public static CommandParser getInstance() {
        return new CommandParser();
    }

    public Command parseLine(String line) {
        String[] commandTokens = line.split(" ");
        Optional<CommandType> optionalCommandType = CommandType.ofType(commandTokens[0]);
        if (optionalCommandType.isPresent()) {
            CommandType commandType = optionalCommandType.get();
            validateCommandParams(commandType, commandTokens);
            LinkedList<String> params = new LinkedList<>();
            if (commandTokens.length > 1) {
                for (int i = 1; i < commandTokens.length; i++) {
                    params.add(commandTokens[i]);
                }
            }

            return new Command(commandType, params);
        } else {
            throw new IllegalStateException("Unable to parse input " + line);
        }
    }

    private void validateCommandParams(CommandType commandType, String[] commandTokens) {
        if (commandType.getNumberOfParams() != commandTokens.length - 1) {
            throw new IllegalStateException("Incorrect number of arguments for given command " + commandTokens);
        }

    }
}
