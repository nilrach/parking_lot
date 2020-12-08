package com.gojek.parkinglot.acceptance;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingLotFactory;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ParkingLotServiceAcceptanceTest {

    @Test
    public void shouldBeAbleToCreateParkingLotOfGivenSize() {
        int numberOfSlots = 11;
        ParkingLot parkingLot = ParkingLotFactory.create(numberOfSlots);
        assertThat("Parking lot is not created", parkingLot, notNullValue());
        assertThat("Parking lot created but not of given size", parkingLot.getSlots().size(), is(numberOfSlots));
    }

    @Test
    public void shouldBeAbleToGetStatusOfGivenParkingLot() {
        ParkingLot parkingLot = ParkingLotFactory.create(11);
        ParkingLotService parkingLotService = ParkingLotService.getInstance(parkingLot);
        String status = parkingLotService.getStatus();
        assertThat("Could not get parking lot status.", status, notNullValue());
        assertThat("Could not get parking lot status.", status.isBlank(), is(false));
    }
}
