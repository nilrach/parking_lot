package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.service.ParkingLotService;

public interface CommandExecutor {
    CommandResult execute(ParkingLotService parkingLotService, Command command);

    default CommandResult executeIfInitialized(ParkingLotService parkingLotService, Command command) {
        if (parkingLotService != null) {
            return execute(parkingLotService, command);
        } else {
            return parkingLotNotCreatedResponse();
        }
    }

    default CommandResult parkingLotNotCreatedResponse() {
        return new CommandResult(false, "Parking lot not created yet.");
    }
}
