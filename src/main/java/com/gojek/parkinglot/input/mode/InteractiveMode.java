package com.gojek.parkinglot.input.mode;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandExecutionService;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.command.parser.CommandParser;

import java.util.Scanner;

public class InteractiveMode {
    private final CommandParser commandParser;
    private final CommandExecutionService commandExecutionService;

    private InteractiveMode(CommandParser commandParser, CommandExecutionService commandExecutionService) {
        this.commandParser = commandParser;
        this.commandExecutionService = commandExecutionService;

    }

    public static InteractiveMode getInstance(CommandParser commandParser, CommandExecutionService commandExecutionService) {
        return new InteractiveMode(commandParser, commandExecutionService);
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        printHelp();
        System.out.print("Enter your commands one by one: ");
        while (true) {
            String inputLine = scanner.nextLine();
            if ("exit".equalsIgnoreCase(inputLine)) {
                break;
            } else {
                Command command = commandParser.parseLine(inputLine);
                CommandResult commandResult = commandExecutionService.execute(command);
                System.out.println(commandResult.getMessage());

            }
        }
    }

    private static void printHelp() {
        StringBuffer sb = new StringBuffer();
        sb = sb.append("1) For creating parking lot of size n               ---> create_parking_lot {capacity}\n");
        sb = sb.append("2) To park a car                                    ---> park <<car_number>> {car_clour}\n");
        sb = sb.append("3) :Leave car from parking slot                ---> leave {slot_number}\n");
        sb = sb.append("4) Print status of parking slot                     ---> status").append("\n");
        sb = sb.append("5) Get cars registration no for the given car color ---> registration_numbers_for_cars_with_color {car_color}\n");
        sb = sb.append(
                "6) Get slot numbers for the given car color         ---> slot_numbers_for_cars_with_color {car_color}\n");
        sb = sb.append(
                "7) Get slot number for the given car number         ---> slot_number_for_registration_number {car_number}\n");
        sb.append("8) To exit           ---> exit \n");
        System.out.println(sb.toString());
    }
}
