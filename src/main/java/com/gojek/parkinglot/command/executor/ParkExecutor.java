package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.LinkedList;

import static com.gojek.parkinglot.config.Constants.PARKING_ALLOCATED_MSG;
import static com.gojek.parkinglot.config.Constants.PARKING_FULL_MSG;

public class ParkExecutor implements CommandExecutor {
    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        if (parkingLotService != null) {
            LinkedList<String> params = command.getParams();
            Vehicle vehicle = new Car(params.getFirst(), params.getLast());
            Integer parkedSlot = parkingLotService.park(vehicle);
            if (parkedSlot != -1) {
                return new CommandResult(true, PARKING_ALLOCATED_MSG + parkedSlot);
            } else {
                return new CommandResult(false, PARKING_FULL_MSG);
            }
        } else {
            return parkingLotNotCreatedResponse();
        }
    }
}
