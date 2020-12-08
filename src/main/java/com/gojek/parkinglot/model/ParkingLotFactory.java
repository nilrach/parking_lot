package com.gojek.parkinglot.model;


public class ParkingLotFactory {
    public static ParkingLot create(int numberOfSlots) {
        return new ParkingLot(numberOfSlots);
    }
}
