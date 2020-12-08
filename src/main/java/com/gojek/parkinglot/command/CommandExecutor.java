package com.gojek.parkinglot.command;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingLotFactory;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.LinkedList;

public class CommandExecutor {
    private ParkingLotService parkingLotService;

    private CommandExecutor() {

    }

    public static CommandExecutor getInstance() {
        return new CommandExecutor();
    }

    public CommandResult execute(Command command) {
        CommandResult commandResult;
        try {
            switch (command.getType()) {
                case CREATE:
                    ParkingLot parkingLot = ParkingLotFactory.create(11);
                    parkingLotService = ParkingLotService.getInstance(parkingLot);
                    break;
                case PARK:
                    if (parkingLotService != null) {
                        LinkedList<String> params = command.getParams();
                        Vehicle vehicle = new Car(params.getFirst(), params.getLast());
                        Boolean isParked = parkingLotService.park(vehicle);
                        if (isParked) {
                            return new CommandResult(true, "");
                        }
                    } else {
                        sendParkingLotNotCreatedResponse();
                    }

            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            return new CommandResult(false, e.getMessage());
        }
        return new CommandResult(true, "");
    }

    private CommandResult sendParkingLotNotCreatedResponse() {
        return new CommandResult(false, "");
    }
}
