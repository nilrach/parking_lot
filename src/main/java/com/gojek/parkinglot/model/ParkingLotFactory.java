package com.gojek.parkinglot.model;


import java.util.Comparator;

public class ParkingLotFactory {
    private static final Comparator<ParkingSlot> parkingSlotComparator = (slot1, slot2) -> slot1.getNumber() - slot1.getNumber();

    public static ParkingLot create(int numberOfSlots) {

        return new ParkingLot(numberOfSlots, parkingSlotComparator);
    }
}
