package com.gojek.parkinglot.input.parser;

import com.gojek.parkinglot.model.Command;
import com.gojek.parkinglot.model.CommandType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class FileInputParserTest {
    private static FileInputParser fileInputParser;
    private final static String INPUT_FILE = "functional_spec\\fixtures\\file_input.txt";

    @Test
    public void shouldBeAbleToParseCommandsFromFile() {
        List<Command> commands = fileInputParser.parse(Paths.get(INPUT_FILE).toFile().getAbsolutePath());
        assertThat("Commands could not be parsed from given file", commands, notNullValue());
    }

    @Test
    public void shouldBeAbleToParseToValidCommand() {
        List<Command> commands = fileInputParser.parse(Paths.get(INPUT_FILE).toFile().getAbsolutePath());
        boolean foundCreateCommand = commands.stream().anyMatch(command -> CommandType.CREATE == command.getType());
        assertThat("Could not parse file to valid command.", foundCreateCommand, is(true));

    }

    @BeforeAll
    public static void setup() {
        fileInputParser = new FileInputParser();
    }
}
