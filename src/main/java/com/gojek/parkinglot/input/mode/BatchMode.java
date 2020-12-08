package com.gojek.parkinglot.input.mode;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandExecutor;
import com.gojek.parkinglot.command.parser.CommandParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BatchMode {
    private final CommandParser commandParser;
    private final CommandExecutor commandExecutor;

    private BatchMode(CommandParser commandParser, CommandExecutor commandExecutor) {
        this.commandParser = commandParser;
        this.commandExecutor = commandExecutor;
    }

    public static BatchMode getInstance(CommandParser commandParser, CommandExecutor commandExecutor) {
        return new BatchMode(commandParser, commandExecutor);
    }

    public void execute(String commandsInputFile) {
        List<Command> commands = parse(commandsInputFile);

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
