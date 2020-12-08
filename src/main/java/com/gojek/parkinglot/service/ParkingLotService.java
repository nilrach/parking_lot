package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingSlot;

import java.util.Set;
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
}
