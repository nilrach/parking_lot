package com.gojek.parkinglot.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class ParkingLot {
    private final Set<ParkingSlot> slots = new HashSet<>();

    public ParkingLot(int numberOfSlots) {
        IntStream.rangeClosed(1, numberOfSlots).forEach(
                i -> slots.add(new ParkingSlot(i))
        );
    }

    public Set<ParkingSlot> getSlots() {
        return slots;
    }
}
