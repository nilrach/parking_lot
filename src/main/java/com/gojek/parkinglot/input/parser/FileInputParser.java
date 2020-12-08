package com.gojek.parkinglot.input.parser;

import com.gojek.parkinglot.model.Command;
import com.gojek.parkinglot.model.CommandType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FileInputParser {
    public List<Command> parse(String commandsInputFile) {
        List<Command> parsedCommands = new LinkedList<>();
        try {
            List<String> allLine = Files.readAllLines(Paths.get(commandsInputFile));
            allLine.stream().forEach(line -> parsedCommands.add(parseLine(line)));
        } catch (IOException e) {
            throw new IllegalStateException("Could not read input file.", e);
        }
        return parsedCommands;
    }

    private Command parseLine(String line) {
        String[] commandTokens = line.split(" ");
        Optional<CommandType> commandType = CommandType.ofType(commandTokens[0]);
        if (commandType.isPresent()) {
            return new Command(commandType.get(), Arrays.asList(commandTokens));
        } else {
            throw new IllegalStateException("Unable to parse input " + line);
        }
    }
}
