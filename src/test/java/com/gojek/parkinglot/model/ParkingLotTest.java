package com.gojek.parkinglot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    public void beforeEach() {
        int numberOfSlots = 4;
        parkingLot = ParkingLotFactory.create(numberOfSlots);
    }

    @Test
    public void whenNewlyCreatedAllSlotsShouldBeAvailableForParking() {
        assertThat("All parking slots should be in available state for newly created parking lot",
                parkingLot.getAllAvailableSlots().size(), equalTo(parkingLot.getAllSlots().size()));
    }

    @Test
    public void shouldDecrementAvailableSlotCountByOneWhenParkedCar() {
        int numberOfAvailableSlotBefore = parkingLot.getAllAvailableSlots().size();
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("fsdfsd", ""));
        assertThat("All parking slots should be in available state for newly created parking lot",
                numberOfAvailableSlotBefore, equalTo(parkingLot.getAllAvailableSlots().size() + 1));
    }

    @Test
    public void shouldBeAbleToParkCarWhenParkingIsFull() {
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("abc", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("pqr", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("xyz", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("lmn", ""));

        ParkingSlot nextAvailableSlot = parkingLot.getNextAvailableSlot();
        assertThat("When parking is full then should restrict allocating further slots.", nextAvailableSlot, nullValue());
    }

    @Test
    public void sumOfAvailableAndOccupiedSlotsShouldBeAlwaysEqualToTotalSlots() {
        assertThat("Sum of available and occupied slots should match total slots.",
                parkingLot.getAllAvailableSlots().size() + parkingLot.getAllOccupiedSlots().size(),
                equalTo(parkingLot.getAllSlots().size()));

        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("pqr", ""));
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("xyz", ""));
        assertThat("Sum of available and occupied slots should match total slots.",
                parkingLot.getAllAvailableSlots().size() + parkingLot.getAllOccupiedSlots().size(),
                equalTo(parkingLot.getAllSlots().size()));

        parkingLot.deAllocate(1);
        assertThat("Sum of available and occupied slots should match total slots.",
                parkingLot.getAllAvailableSlots().size() + parkingLot.getAllOccupiedSlots().size(),
                equalTo(parkingLot.getAllSlots().size()));
    }

    @Test
    public void shouldBeAbleToDeallocateIfAlreadyAllocated() {
        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("pqr", ""));
        boolean isDeallocated = parkingLot.deAllocate(1);
        assertThat("If not allocated then should not be able to de-allocate slot", isDeallocated, is(true));
    }

    @Test
    public void shouldNotBeAbleToDeallocateIfNotAlreadyAllocated() {
        Boolean isDeallocated = parkingLot.deAllocate(1);
        assertThat("If not allocated then should not be able to de-allocate slot", isDeallocated, is(false));

        parkingLot.allocate(parkingLot.getNextAvailableSlot(), new Car("pqr", ""));
        isDeallocated = parkingLot.deAllocate(2);
        assertThat("If not allocated then should not be able to de-allocate slot", isDeallocated, is(false));
    }

}