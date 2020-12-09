package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ParkingLotService {
    private final ParkingLot parkingLot;

    private ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public static ParkingLotService getInstance(ParkingLot parkingArea) {
        return new ParkingLotService(parkingArea);
    }

    public Integer park(Vehicle vehicle) {
        ParkingSlot nextAvailableSlot = parkingLot.getNextAvailableSlot();
        if (nextAvailableSlot != null) {
            parkingLot.allocate(nextAvailableSlot, vehicle);
            return nextAvailableSlot.getNumber();
        }
        return -1;
    }

    public Boolean leave(int slotNumber) {
        return parkingLot.deAllocate(slotNumber);
    }

    public List<Vehicle> getVehiclesMatching(Predicate<Vehicle> vehiclePredicate) {
        Set<Vehicle> allParkedVehicles = parkingLot.getAllParkedVehicles();
        return allParkedVehicles.stream().filter(vehiclePredicate).collect(Collectors.toList());
    }

    public List<ParkingSlot> getSlotsMatching(Predicate<Vehicle> vehiclePredicate) {
        final List<ParkingSlot> slotsMatchingCriteria = new ArrayList<>();
        Set<ParkingSlot> allOccupiedSlots = parkingLot.getAllOccupiedSlots();
        allOccupiedSlots.stream().forEach(s -> {
            Vehicle parkedVehicle = s.getParkedVehicle();
            if (vehiclePredicate.test(parkedVehicle)) {
                slotsMatchingCriteria.add(s);
            }
        });
        return slotsMatchingCriteria;
    }

    public List<ParkingSlot> getStatus() {
        return List.copyOf(parkingLot.getAllOccupiedSlots());

    }
}
