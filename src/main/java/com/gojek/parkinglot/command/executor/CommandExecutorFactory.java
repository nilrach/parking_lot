package com.gojek.parkinglot.command.executor;

import com.gojek.parkinglot.command.CommandType;

import java.util.HashMap;
import java.util.Map;

import static com.gojek.parkinglot.command.CommandType.*;
import static com.gojek.parkinglot.command.CommandType.STATUS;

public class CommandExecutorFactory {

    private static Map<CommandType, CommandExecutor> executorMapping = new HashMap<>();

    static  {
        executorMapping.put(PARK, new ParkExecutor());
        executorMapping.put(LEAVE, new LeaveExecutor());
        executorMapping.put(GET_REGISTRATION_NUM_FOR_COLOUR, new RegistrationNumExecutor());
        executorMapping.put(GET_SLOT_NUM_FOR_COLOUR, new SlotForColourExecutor());
        executorMapping.put(GET_SLOT_NUM_FOR_REGISTRATION_NUM, new SlotForRegistrationNumExecutor());
        executorMapping.put(STATUS, new StatusExecutor());
    }

    public static CommandExecutor getExecutorFor(CommandType commandType) {
        return executorMapping.get(commandType);
    }
}
