package com.gojek.parkinglot.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLot {
    private final Integer numberOfSlots;
    private final Set<ParkingSlot> allSlots = new HashSet<>();
    private final PriorityQueue<ParkingSlot> availableSlots;

    public ParkingLot(Integer numberOfSlots, Comparator<ParkingSlot> parkingSlotComparator) {
        this.numberOfSlots = numberOfSlots;
        availableSlots = new PriorityQueue<>(numberOfSlots, parkingSlotComparator);
        IntStream.rangeClosed(1, numberOfSlots).forEach(
                i -> allSlots.add(new ParkingSlot(i))
        );
        availableSlots.addAll(allSlots);
    }

    public Set<ParkingSlot> getAllSlots() {
        return allSlots;
    }

    public Set<ParkingSlot> getAllAvailableSlots() {
        return allSlots.stream().filter(s -> s.isEmpty()).collect(Collectors.toSet());
    }

    public Set<ParkingSlot> getAllOccupiedSlots() {
        return allSlots.stream().filter(s -> !s.isEmpty()).collect(Collectors.toSet());
    }

    public ParkingSlot getNextAvailableSlot() {
        return availableSlots.peek();
    }

    public Set<Vehicle> getAllParkedVehicles() {
        return allSlots.stream().filter(s -> !s.isEmpty()).map(s -> s.getParkedVehicle()).collect(Collectors.toSet());
    }

    public void allocate(ParkingSlot slot, Vehicle vehicle) {
        availableSlots.remove(slot);
        slot.setParkedVehicle(vehicle);
    }

    public Boolean deAllocate(int slotNumber) {
        ParkingSlot parkingSlot = allSlots.stream().filter(s -> s.getNumber() == slotNumber).findFirst().get();
        if (parkingSlot.isEmpty()) {
            return false;
        } else {
            parkingSlot.setParkedVehicle(null);
            return availableSlots.add(parkingSlot);
        }
    }
}
