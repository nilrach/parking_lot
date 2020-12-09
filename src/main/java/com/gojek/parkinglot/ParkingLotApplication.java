package com.gojek.parkinglot;

import com.gojek.parkinglot.command.CommandExecutionService;
import com.gojek.parkinglot.command.parser.CommandParser;
import com.gojek.parkinglot.input.mode.BatchMode;
import com.gojek.parkinglot.input.mode.InteractiveMode;

public class ParkingLotApplication {
    public static void main(String[] args) {
        try {


            CommandParser commandParser = CommandParser.getInstance();
            CommandExecutionService commandExecutionService = CommandExecutionService.getInstance();
            switch (args.length) {
                case 0:
                    InteractiveMode interactiveMode = InteractiveMode.getInstance(commandParser, commandExecutionService);
                    interactiveMode.execute();
                    break;
                case 1:
                    BatchMode batchMode = BatchMode.getInstance(commandParser, commandExecutionService);
                    batchMode.execute(args[0]);
                    break;
                default:
                    System.out.println("Invalid number of arguments");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
