package com.gojek.parkinglot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class ParkingLotFactoryTest {
    private ParkingLot parkingLot;

    @BeforeEach
    public void beforeEach() {
        int numberOfSlots = 4;
        parkingLot = ParkingLotFactory.create(numberOfSlots);
    }

    @Test
    public void shouldBeAbleToCreateParkingLotOfGivenSize() {
        assertThat("Parking lot is not created", parkingLot, notNullValue());
        assertThat("Parking lot created but not of given size", parkingLot.getAllSlots().size(), is(4));
    }

    @Test
    public void shouldParkIntoFirstSlotForNewParkingLot() {
        ParkingSlot firstTimeParkingSlot = parkingLot.getNextAvailableSlot();
        parkingLot.allocate(firstTimeParkingSlot, new Car("a", ""));
        assertThat("When newly created parking lot then park car in first slot.",
                firstTimeParkingSlot.getNumber(), is(1));
    }

    @Test
    public void shouldParkIntoNearestAvailableSlot() {
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("a", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("b", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("c", ""));

        assertThat("When newly created parking lot then park car in first slot.",
                parkingLot.getNextAvailableSlot().getNumber(), is(4));
    }

    @Test
    public void deallocatedParkingSlotShouldBeAvailableForParkingAsPerNearestFirstStrategy() {
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("a", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("b", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("c", ""));

        parkingLot.deAllocate(2);

        assertThat("When newly created parking lot then park car in first slot.",
                parkingLot.getNextAvailableSlot().getNumber(), is(2));
    }

}