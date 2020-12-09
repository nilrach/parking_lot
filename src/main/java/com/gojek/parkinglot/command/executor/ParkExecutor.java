package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.LinkedList;

public class ParkExecutor implements CommandExecutor {
    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        if (parkingLotService != null) {
            LinkedList<String> params = command.getParams();
            Vehicle vehicle = new Car(params.getFirst(), params.getLast());
            Integer parkedSlot = parkingLotService.park(vehicle);
            if (parkedSlot != -1) {
                return new CommandResult(true, "Allocated slot number: " + parkedSlot);
            } else {
                return new CommandResult(false, "Sorry, parking lot is full");
            }
        } else {
            return parkingLotNotCreatedResponse();
        }
    }
}
