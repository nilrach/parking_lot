package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ParkingLotService {
    private final ParkingLotDistributor parkingLotDistributor;

    private ParkingLotService(ParkingLotDistributor parkingLotDistributor) {
        this.parkingLotDistributor = parkingLotDistributor;
    }

    public static ParkingLotService getInstance(ParkingLotDistributor parkingLotDistributor) {
        return new ParkingLotService(parkingLotDistributor);
    }

    public Integer park(Vehicle vehicle) {
        boolean isAlreadyParked = parkingLotDistributor.isVehicleParked(vehicle);
        if (isAlreadyParked) {
            throw new IllegalStateException(String.format(
                    "Vehicle with registration number %s already parked.", vehicle.getRegistrationNumber()));
        }
        ParkingSlot nextAvailableSlot = parkingLotDistributor.getNextAvailableSlot();
        if (nextAvailableSlot != null) {
            parkingLotDistributor.allocate(nextAvailableSlot, vehicle);
            return nextAvailableSlot.getNumber();
        }
        return -1;
    }

    public Boolean leave(int slotNumber) {
        return parkingLotDistributor.deAllocate(slotNumber);
    }

    public List<Vehicle> getVehiclesMatching(Predicate<Vehicle> vehiclePredicate) {
        Set<Vehicle> allParkedVehicles = parkingLotDistributor.getAllParkedVehicles();
        return allParkedVehicles.stream().filter(vehiclePredicate).collect(Collectors.toList());
    }

    public List<ParkingSlot> getSlotsMatching(Predicate<Vehicle> vehiclePredicate) {
        final List<ParkingSlot> slotsMatchingCriteria = new ArrayList<>();
        Set<ParkingSlot> allOccupiedSlots = parkingLotDistributor.getAllOccupiedSlots();
        allOccupiedSlots.stream().forEach(s -> {
            Vehicle parkedVehicle = s.getParkedVehicle();
            if (vehiclePredicate.test(parkedVehicle)) {
                slotsMatchingCriteria.add(s);
            }
        });
        return slotsMatchingCriteria;
    }

    public List<ParkingSlot> getStatus() {
        return List.copyOf(parkingLotDistributor.getAllOccupiedSlots());

    }
}
