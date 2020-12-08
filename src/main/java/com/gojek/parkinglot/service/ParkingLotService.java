package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ParkingLotService {
    private final ParkingLot parkingArea;

    private ParkingLotService(ParkingLot parkingArea) {
        this.parkingArea = parkingArea;
    }

    public static ParkingLotService getInstance(ParkingLot parkingArea) {
        return new ParkingLotService(parkingArea);
    }

    public String getStatus() {
        Set<ParkingSlot> parkingSlots = parkingArea.getSlots();
        String parkingSlotStatus = parkingSlots.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        return parkingSlotStatus;
    }

    public Boolean park(Vehicle vehicle) {
        return true;
    }

    public Boolean leave(int i) {
        return true;
    }

    public List<Vehicle> getVehiclesMatching(Predicate<Vehicle> vehiclePredicate) {
        return List.of(new Car("", ""));
    }

    public List<ParkingSlot> getSlotsMatching(Predicate<Vehicle> vehiclePredicate) {
        return List.of(new ParkingSlot(1));
    }
}
