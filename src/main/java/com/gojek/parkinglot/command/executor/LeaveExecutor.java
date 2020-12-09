package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.service.ParkingLotService;

public class LeaveExecutor implements CommandExecutor {

    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        Integer slotNumberToLeave = Integer.parseInt(command.getParams().getFirst());
        boolean hasLeft = parkingLotService.leave(slotNumberToLeave);
        if (hasLeft) {
            return new CommandResult(true, "Slot number " + slotNumberToLeave + " is free");
        }
        return new CommandResult(false, "Slot number " + slotNumberToLeave + " is already free.");
    }
}
