package com.gojek.parkinglot.model;


import java.util.Comparator;

public class ParkingLotFactory {
    private static final Comparator<ParkingSlot> parkingSlotComparator =
            Comparator.comparingInt(ParkingSlot::getNumber);

    public static ParkingLot create(int numberOfSlots) {

        return new ParkingLot(numberOfSlots, parkingSlotComparator);
    }
}
