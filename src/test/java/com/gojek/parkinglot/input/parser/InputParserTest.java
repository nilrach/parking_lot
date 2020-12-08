package com.gojek.parkinglot.input.parser;

import com.gojek.parkinglot.model.Command;
import com.gojek.parkinglot.model.CommandType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class InputParserTest {
    private static InputParser inputParser;
    private final static String INPUT_FILE = "functional_spec\\fixtures\\file_input.txt";

    @Test
    public void shouldBeAbleToParseCommandsFromFile() {
        List<Command> commands = getAllLinesOfInputFiles().stream()
                .map(line -> inputParser.parseLine(line))
                .collect(Collectors.toList());
        assertThat("Commands could not be parsed from given file", commands, notNullValue());
    }

    @Test
    public void shouldBeAbleToParseToValidCommand() {
        List<Command> commands = getAllLinesOfInputFiles().stream()
                .map(line -> inputParser.parseLine(line))
                .collect(Collectors.toList());
        boolean foundCreateCommand = commands.stream().anyMatch(command -> CommandType.CREATE == command.getType());
        assertThat("Could not parse file to valid command.", foundCreateCommand, is(true));

    }

    private List<String> getAllLinesOfInputFiles() {
        try {
            return Files.readAllLines(Paths.get(INPUT_FILE));

        } catch (IOException e) {
            throw new IllegalStateException("Could not read input file.", e);
        }
    }

    @BeforeAll
    public static void setup() {
        inputParser = InputParser.getInstance();
    }
}