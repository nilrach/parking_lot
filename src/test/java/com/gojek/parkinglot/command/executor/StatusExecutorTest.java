package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandType;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatusExecutorTest {
    private StatusExecutor statusExecutor;
    @Mock
    ParkingLotService parkingLotService;

    @BeforeEach
    public void beforeEach() {
        statusExecutor = new StatusExecutor();
    }

    @Test
    public void shouldBeAbleToGetSlotForGivenColourUsingParkingService() {
        statusExecutor.execute(parkingLotService, getCommand());

        verify(parkingLotService, times(1)).getStatus();
    }

    private Command getCommand() {
        LinkedList<String> params = new LinkedList();
        return new Command(CommandType.STATUS, params);
    }

}