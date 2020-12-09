package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParkingLotDistributor {
    private List<ParkingLot> parkingLots;

    private ParkingLotDistributor(ParkingLot parkingLot) {
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

    }

    public static ParkingLotDistributor getInstance(ParkingLot parkingLot) {
        return new ParkingLotDistributor(parkingLot);
    }

    public boolean add(ParkingLot parkingLot) {
        return parkingLots.add(parkingLot);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return parkingLots.get(0).isVehicleParked(vehicle);
    }

    public ParkingSlot getNextAvailableSlot() {
        return parkingLots.stream().findFirst().get().getNextAvailableSlot();
    }

    public void allocate(ParkingSlot nextAvailableSlot, Vehicle vehicle) {
        parkingLots.stream().findFirst().get().allocate(nextAvailableSlot, vehicle);

    }

    public Boolean deAllocate(int slotNumber) {
        return parkingLots.stream().findFirst().get().deAllocate(slotNumber);
    }

    public Set<Vehicle> getAllParkedVehicles() {
        return parkingLots.stream().findFirst().get().getAllParkedVehicles();
    }

    public Set<ParkingSlot> getAllOccupiedSlots() {
        return parkingLots.stream().findFirst().get().getAllOccupiedSlots();
    }
}
