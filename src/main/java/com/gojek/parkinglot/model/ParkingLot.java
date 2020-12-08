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
        return allSlots.stream().filter(s -> !s.isEmpty()).collect(Collectors.toSet());
    }

    public ParkingSlot getNextAvailableSlots() {
        return availableSlots.poll();
    }

}
