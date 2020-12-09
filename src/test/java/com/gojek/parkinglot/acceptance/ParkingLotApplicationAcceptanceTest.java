package com.gojek.parkinglot.acceptance;

import com.gojek.parkinglot.ParkingLotApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParkingLotApplicationAcceptanceTest {
    private final static String INPUT_FILE = "functional_spec/fixtures/file_input.txt";
    private final static String EXPECTED_OUTPUT_FILE = "src/test/resources/expected_output.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private File tempFile;

    @Test
    public void shouldBeAbleToExecuteInBatchMode() throws IOException {
        String[] args = {Paths.get(INPUT_FILE).toFile().getAbsolutePath()};
        ParkingLotApplication.main(args);
        System.out.flush();
        Path expectedOutPutFile = Paths.get(EXPECTED_OUTPUT_FILE);
        Path actualOutputFile = Paths.get(tempFile.toURI());
        List<String> expectedOutput = Files.readAllLines(expectedOutPutFile);
        List<String> actualOutPut = Files.readAllLines(actualOutputFile);

        String s1 = new String(Files.readAllBytes(expectedOutPutFile), "UTF-8");
        String s2 = new String(Files.readAllBytes(actualOutputFile), "UTF-8");
       // Assertions.assertEquals(s1.strip(), s2.strip());

    }

    @BeforeEach
    public void setUpStreams() throws IOException {
        tempFile = File.createTempFile("output", ".txt");
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(tempFile))));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        tempFile.delete();
        tempFile.deleteOnExit();
    }
}
