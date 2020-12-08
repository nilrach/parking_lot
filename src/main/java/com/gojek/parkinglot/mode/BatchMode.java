package com.gojek.parkinglot.mode;

import com.gojek.parkinglot.input.parser.InputParser;
import com.gojek.parkinglot.model.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BatchMode {
    private final InputParser inputParser;

    private BatchMode(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public static BatchMode getInstance(InputParser inputParser) {
        return new BatchMode(inputParser);
    }

    public void execute(String commandsInputFile) {
        parse(commandsInputFile);
    }

    private List<Command> parse(String commandsInputFile) {
        List<Command> parsedCommands = new LinkedList<>();
        try {
            List<String> allLine = Files.readAllLines(Paths.get(commandsInputFile));
            allLine.stream().forEach(line -> parsedCommands.add(inputParser.parseLine(line)));
        } catch (IOException e) {
            throw new IllegalStateException("Could not read input file.", e);
        }
        return parsedCommands;
    }
}
