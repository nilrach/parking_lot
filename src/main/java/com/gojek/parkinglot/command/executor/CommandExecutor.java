package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.service.ParkingLotService;

public interface CommandExecutor {
    CommandResult execute(ParkingLotService parkingLotService, Command command);

    default CommandResult sendParkingLotNotCreatedResponse() {
        return new CommandResult(false, "");
    }
}
