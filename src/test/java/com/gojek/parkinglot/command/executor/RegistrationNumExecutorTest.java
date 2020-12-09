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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationNumExecutorTest {
    private RegistrationNumExecutor registrationNumExecutor;
    @Mock
    ParkingLotService parkingLotService;

    @BeforeEach
    public void beforeEach() {
        registrationNumExecutor = new RegistrationNumExecutor();
    }

    @Test
    public void shouldBeAbleToGetRegistrationNumberUsingParkingService() {
        registrationNumExecutor.execute(parkingLotService, getCommand());

        verify(parkingLotService, times(1)).getVehiclesMatching(any());
    }

    private Command getCommand() {
        LinkedList<String> params = new LinkedList();
        params.add("white");
        return new Command(CommandType.GET_REGISTRATION_NUM_FOR_COLOUR, params);
    }

}