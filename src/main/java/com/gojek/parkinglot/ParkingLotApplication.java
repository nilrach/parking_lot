package com.gojek.parkinglot;

import com.gojek.parkinglot.input.parser.InputParser;
import com.gojek.parkinglot.mode.BatchMode;
import com.gojek.parkinglot.mode.InteractiveMode;

public class ParkingLotApplication {
    public static void main(String[] args) {

        InputParser inputParser = InputParser.getInstance();
        switch (args.length) {
            case 0:
                InteractiveMode interactiveMode = InteractiveMode.getInstance(inputParser);
                interactiveMode.execute();
                break;
            case 1:
                BatchMode batchMode = BatchMode.getInstance(inputParser);
                batchMode.execute(args[1]);
                break;
            default:
                System.out.println("Invalid number of arguments");
        }
    }
}
