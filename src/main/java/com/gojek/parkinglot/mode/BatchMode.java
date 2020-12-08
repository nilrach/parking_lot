package com.gojek.parkinglot.mode;

import com.gojek.parkinglot.command.parser.CommandParser;
import com.gojek.parkinglot.command.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BatchMode {
    private final CommandParser commandParser;

    private BatchMode(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public static BatchMode getInstance(CommandParser commandParser) {
        return new BatchMode(commandParser);
    }

    public void execute(String commandsInputFile) {
        parse(commandsInputFile);
    }

    private List<Command> parse(String commandsInputFile) {
        List<Command> parsedCommands = new LinkedList<>();
        try {
            List<String> allLine = Files.readAllLines(Paths.get(commandsInputFile));
            allLine.stream().forEach(line -> parsedCommands.add(commandParser.parseLine(line)));
        } catch (IOException e) {
            throw new IllegalStateException("Could not read input file.", e);
        }
        return parsedCommands;
    }
}
