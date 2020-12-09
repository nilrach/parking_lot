package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.service.ParkingLotService;

import static com.gojek.parkinglot.config.Constants.SLOT_NUM_IS_ALREADY_FREE_MSG;
import static com.gojek.parkinglot.config.Constants.SLOT_NUM_IS_FREE_MSG;

public class LeaveExecutor implements CommandExecutor {

    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        Integer slotNumberToLeave = Integer.parseInt(command.getParams().getFirst());
        boolean hasLeft = parkingLotService.leave(slotNumberToLeave);
        if (hasLeft) {
            return new CommandResult(true, String.format(SLOT_NUM_IS_FREE_MSG, slotNumberToLeave));
        }
        return new CommandResult(false, String.format(SLOT_NUM_IS_ALREADY_FREE_MSG, slotNumberToLeave));
    }
}
