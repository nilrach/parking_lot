package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RegistrationNumExecutor implements CommandExecutor {
    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        String matchingColour = command.getParams().getFirst();
        Predicate<Vehicle> vehiclePredicate = (v) -> matchingColour.equalsIgnoreCase(v.getColour());
        List<Vehicle> vehiclesMatching = parkingLotService.getVehiclesMatching(vehiclePredicate);
        if (vehiclesMatching != null && !vehiclesMatching.isEmpty()) {
            String matchingVehicleNumbers = vehiclesMatching.stream()
                    .map(vehicle -> vehicle.getRegistrationNumber())
                    .collect(Collectors.joining(", "));
            return new CommandResult(true, matchingVehicleNumbers);
        }
        return new CommandResult(false, "Not found");
    }
}
