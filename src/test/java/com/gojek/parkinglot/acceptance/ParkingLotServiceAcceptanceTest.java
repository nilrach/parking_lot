package com.gojek.parkinglot.acceptance;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingLotFactory;
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
}
