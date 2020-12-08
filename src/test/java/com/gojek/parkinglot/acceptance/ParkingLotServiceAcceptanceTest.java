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
        assertThat("Parking lot created but not of given size", parkingLot.getAllSlots().size(), is(numberOfSlots));
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
        Vehicle vehicle1 = new Car("KA-01-HH-1234", "White");
        parkingLotService.park(vehicle1);
        Vehicle vehicle2 = new Car("KA-01-HH-3141", "Red");
        parkingLotService.park(vehicle2);

        Boolean status = parkingLotService.leave(2);
        assertThat("Could not leave.", status, notNullValue());
        assertThat("Could not leave.", status, is(true));
    }

    @Test
    public void shouldBeAbleToGetVehiclesWithGivenCriteria() {
        Vehicle vehicle1 = new Car("KA-01-HH-1234", "White");
        parkingLotService.park(vehicle1);
        Vehicle vehicle2 = new Car("KA-01-HH-3141", "Red");
        parkingLotService.park(vehicle2);

        Predicate<Vehicle> vehiclePredicate = v -> "White".equalsIgnoreCase(v.getColour());
        List<Vehicle> vehicles = parkingLotService.getVehiclesMatching(vehiclePredicate);
        assertThat("Could not get vehicles matching given criteria.", vehicles, notNullValue());
        assertThat("Could not get vehicles matching given criteria.", vehicles.isEmpty(), is(false));
    }

    @Test
    public void shouldBeAbleToGetSlotWithGivenCriteria() {
        Vehicle vehicle = new Car("KA-01-HH-3141", "White");
        parkingLotService.park(vehicle);

        Predicate<Vehicle> vehiclePredicate = (v) -> "KA-01-HH-3141".equalsIgnoreCase(v.getRegistrationNumber());
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
