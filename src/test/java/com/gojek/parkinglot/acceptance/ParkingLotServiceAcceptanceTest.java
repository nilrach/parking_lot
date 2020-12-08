package com.gojek.parkinglot.acceptance;

import com.gojek.parkinglot.model.*;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ParkingLotServiceAcceptanceTest {
    private ParkingLotService parkingLotService;

    @Test
    public void shouldBeAbleToCreateParkingLotOfGivenSize() {
        int numberOfSlots = 11;
        ParkingLot parkingLot = ParkingLotFactory.create(numberOfSlots);
        assertThat("Parking lot is not created", parkingLot, notNullValue());
        assertThat("Parking lot created but not of given size", parkingLot.getSlots().size(), is(numberOfSlots));
    }

    @Test
    public void shouldBeAbleToGetStatusOfGivenParkingLot() {
        String status = parkingLotService.getStatus();
        assertThat("Could not get parking lot status.", status, notNullValue());
        assertThat("Could not get parking lot status.", status.isBlank(), is(false));
    }

    @Test
    public void shouldBeAbleToParkCar() {
        Vehicle vehicle = new Car("KA-01-HH-1234", "White");
        Boolean status = parkingLotService.park(vehicle);
        assertThat("Could not get parking lot status.", status, notNullValue());
        assertThat("Could not get parking lot status.", status, is(true));
    }

    @Test
    public void shouldBeAbleToLeaveParkingSlot() {
        Boolean status = parkingLotService.leave(4);
        assertThat("Could not get parking lot status.", status, notNullValue());
        assertThat("Could not get parking lot status.", status, is(true));
    }

    @Test
    public void shouldBeAbleToGetVehiclesWithGivenCriteria() {
        Predicate<Vehicle> vehiclePredicate = null;
        List<Vehicle> vehicles = parkingLotService.getVehiclesMatching(vehiclePredicate);
        assertThat("Could not get vehicles matching given criteria.", vehicles, notNullValue());
        assertThat("Could not get vehicles matching given criteria.", vehicles.isEmpty(), is(false));
    }

    @Test
    public void shouldBeAbleToGetSlotWithGivenCriteria() {
        Predicate<Vehicle> vehiclePredicate = null;
        List<ParkingSlot> parkingSlots = parkingLotService.getSlotsMatching(vehiclePredicate);
        assertThat("Could not get parking slots matching given criteria.", parkingSlots, notNullValue());
        assertThat("Could not get parking slots matching given criteria.", parkingSlots.isEmpty(), is(false));
    }

    @BeforeEach
    private void setup() {
        ParkingLot parkingLot = ParkingLotFactory.create(11);
        parkingLotService = ParkingLotService.getInstance(parkingLot);
    }
}
