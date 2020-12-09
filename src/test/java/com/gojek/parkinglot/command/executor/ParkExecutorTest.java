package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CommandResult;
import com.gojek.parkinglot.command.CommandType;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkExecutorTest {
    private ParkExecutor parkExecutor;
    @Mock
    ParkingLotService parkingLotService;

    @BeforeEach
    public void beforeEach() {
        parkExecutor = new ParkExecutor();
    }

    @Test
    public void shouldBeAbleToExecuteParkCommandUsingParkingService() {
        parkExecutor.execute(parkingLotService, getParkCommand());

        verify(parkingLotService, Mockito.times(1)).park(any());
    }

    private Command getParkCommand() {
        LinkedList<String> params = new LinkedList();
        params.add("a");
        params.add("Green");
        return new Command(CommandType.PARK, params);
    }

}