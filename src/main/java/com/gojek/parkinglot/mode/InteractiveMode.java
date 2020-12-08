package com.gojek.parkinglot.mode;

import com.gojek.parkinglot.input.parser.InputParser;

public class InteractiveMode {
    private final InputParser inputParser;

    private InteractiveMode(InputParser inputParser) {
        this.inputParser = inputParser;

    }

    public static InteractiveMode getInstance(InputParser inputParser) {
        return new InteractiveMode(inputParser);
    }

    public void execute() {

    }
}
