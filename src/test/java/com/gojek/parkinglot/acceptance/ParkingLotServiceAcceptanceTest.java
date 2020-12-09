package com.gojek.parkinglot.acceptance;

import com.gojek.parkinglot.model.*;
import com.gojek.parkinglot.service.ParkingLotDistributor;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParkingLotServiceAcceptanceTest {
    private ParkingLotService parkingLotService;

    @BeforeEach
    private void setup() {
        ParkingLot parkingLot = ParkingLotFactory.create(11);

        parkingLotService = ParkingLotService.getInstance(ParkingLotDistributor.getInstance(parkingLot));
    }

    @Test
    public void shouldBeAbleToHandleMoreThanOneParkingLot() {

    }

    @Test
    public void shouldBeAbleToParkCar() {
        Vehicle vehicle = new Car("KA-01-HH-1234", "White");
        Integer parkedSlot = parkingLotService.park(vehicle);
        assertThat("Could not get parking lot status.", parkedSlot, notNullValue());
        assertThat("Could not get parking lot status.", parkedSlot, not(-1));
    }

    @Test
    public void shouldNotBeAbleToParkSameCarMoreThanOnce() {
        Vehicle vehicle = new Car("KA-01-HH-1234", "White");
        parkingLotService.park(vehicle);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            parkingLotService.park(vehicle);
            ;
        });
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

    @Test
    public void shouldBeAbleToGetStatusOfGivenParkingLot() {
        parkingLotService.park(new Car("KA-01-HH-1234", "White"));
        parkingLotService.park(new Car("KA-01-HH-3141", "Black"));
        parkingLotService.park(new Car("KA-01-HH-9999", "White"));

        List<ParkingSlot> occupiedSlots = parkingLotService.getStatus();
        assertThat("Could not get parking lot status.", occupiedSlots, notNullValue());
        assertThat("Could not get parking lot status.", occupiedSlots.isEmpty(), is(false));
    }

}
