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
class SlotForRegistrationNumExecutorTest {
    private SlotForRegistrationNumExecutor slotForRegistrationNumExecutor;
    @Mock
    ParkingLotService parkingLotService;

    @BeforeEach
    public void beforeEach() {
        slotForRegistrationNumExecutor = new SlotForRegistrationNumExecutor();
    }

    @Test
    public void shouldBeAbleToGetSlotForGivenRegistrationNumberUsingParkingService() {
        slotForRegistrationNumExecutor.execute(parkingLotService, getCommand());

        verify(parkingLotService, times(1)).getSlotsMatching(any());
    }

    private Command getCommand() {
        LinkedList<String> params = new LinkedList();
        params.add("KA 1111");
        return new Command(CommandType.GET_SLOT_NUM_FOR_COLOUR, params);
    }

}