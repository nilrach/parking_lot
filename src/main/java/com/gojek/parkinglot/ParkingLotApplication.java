package com.gojek.parkinglot;

import com.gojek.parkinglot.command.parser.CommandParser;
import com.gojek.parkinglot.input.mode.BatchMode;
import com.gojek.parkinglot.input.mode.InteractiveMode;

public class ParkingLotApplication {
    public static void main(String[] args) {

        CommandParser commandParser = CommandParser.getInstance();
        switch (args.length) {
            case 0:
                InteractiveMode interactiveMode = InteractiveMode.getInstance(commandParser);
                interactiveMode.execute();
                break;
            case 1:
                BatchMode batchMode = BatchMode.getInstance(commandParser);
                batchMode.execute(args[1]);
                break;
            default:
                System.out.println("Invalid number of arguments");
        }
    }
}
