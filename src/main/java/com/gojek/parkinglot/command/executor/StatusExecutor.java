package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.Comparator;
import java.util.List;

import static com.gojek.parkinglot.config.Constants.GET_STATUS_HEADER_MSG;
import static com.gojek.parkinglot.config.Constants.PARKING_LOT_EMPTY_MSG;

public class StatusExecutor implements CommandExecutor {

    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        List<ParkingSlot> occupiedSlots = parkingLotService.
                getStatus();
        if (occupiedSlots != null) {
            StringBuffer stringBuffer = new StringBuffer(GET_STATUS_HEADER_MSG);

            occupiedSlots.stream().
                    sorted(Comparator.comparingInt((ParkingSlot::getNumber)))
                    .map(s -> "\n" + s.getNumber() + "\t" + s.getParkedVehicle().getRegistrationNumber() + "\t" + s.getParkedVehicle().getColour())
                    .forEach(d -> stringBuffer.append(d));
            return new CommandResult(true, stringBuffer.toString());

        }
        return new CommandResult(false, PARKING_LOT_EMPTY_MSG);
    }
}
