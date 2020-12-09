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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LeaveExecutorTest {
    private LeaveExecutor leaveExecutor;
    @Mock
    ParkingLotService parkingLotService;

    @BeforeEach
    public void beforeEach() {
        leaveExecutor = new LeaveExecutor();
    }

    @Test
    public void shouldBeAbleToExecuteLeaveCommandUsingParkingService() {
        leaveExecutor.execute(parkingLotService, getLeaveCommand());

        verify(parkingLotService, times(1)).leave(anyInt());
    }

    private Command getLeaveCommand() {
        LinkedList<String> params = new LinkedList();
        params.add("1");
        return new Command(CommandType.LEAVE, params);
    }

}