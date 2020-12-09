package com.gojek.parkinglot.input.mode;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandExecutionService;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.command.parser.CommandParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BatchMode {
    private final CommandParser commandParser;
    private final CommandExecutionService commandExecutionService;

    private BatchMode(CommandParser commandParser, CommandExecutionService commandExecutionService) {
        this.commandParser = commandParser;
        this.commandExecutionService = commandExecutionService;
    }

    public static BatchMode getInstance(CommandParser commandParser, CommandExecutionService commandExecutionService) {
        return new BatchMode(commandParser, commandExecutionService);
    }

    public void execute(String commandsInputFile) {
        List<Command> commands = parse(commandsInputFile);
        commands.forEach(command -> {
            CommandResult commandResult = commandExecutionService.execute(command);
            System.out.println(commandResult.getMessage());
        });

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
