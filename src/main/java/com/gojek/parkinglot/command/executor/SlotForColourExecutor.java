package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SlotForColourExecutor implements CommandExecutor {

    @Override
    public CommandResult execute(ParkingLotService parkingLotService, Command command) {
        String matchingColour = command.getParams().getFirst();
        Predicate<Vehicle> vehiclePredicate = (v) -> matchingColour.equalsIgnoreCase(v.getColour());
        List<ParkingSlot> slotsMatching = parkingLotService.getSlotsMatching(vehiclePredicate);
        if (slotsMatching != null && !slotsMatching.isEmpty()) {
            String desiredSlotNumbers = slotsMatching.stream()
                    .map(parkingSlot -> parkingSlot.getNumber().toString())
                    .collect(Collectors.joining(", "));
            return new CommandResult(true, desiredSlotNumbers);
        }
        return new CommandResult(false, "Not found");
    }
}