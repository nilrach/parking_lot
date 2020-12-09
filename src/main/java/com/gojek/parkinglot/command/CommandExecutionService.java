package com.gojek.parkinglot.command;

import com.gojek.parkinglot.command.executor.*;
import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.ParkingLotFactory;
import com.gojek.parkinglot.service.ParkingLotDistributor;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.HashMap;

import static com.gojek.parkinglot.command.CommandType.*;

public class CommandExecutionService {
    private ParkingLotService parkingLotService;
    private HashMap<CommandType, CommandExecutor> executorMapping = new HashMap<>();

    private CommandExecutionService() {
        executorMapping.put(PARK, new ParkExecutor());
        executorMapping.put(LEAVE, new LeaveExecutor());
        executorMapping.put(GET_REGISTRATION_NUM_FOR_COLOUR, new RegistrationNumExecutor());
        executorMapping.put(GET_SLOT_NUM_FOR_COLOUR, new SlotForColourExecutor());
        executorMapping.put(GET_SLOT_NUM_FOR_REGISTRATION_NUM, new SlotForRegistrationNumExecutor());
        executorMapping.put(STATUS, new StatusExecutor());
    }

    public static CommandExecutionService getInstance() {
        return new CommandExecutionService();
    }

    public CommandResult execute(Command command) {
        try {
            switch (command.getType()) {
                case CREATE:
                    int numberOfSlots = Integer.parseInt(command.getParams().getFirst());
                    ParkingLot parkingLot = ParkingLotFactory.create(numberOfSlots);
                    ParkingLotDistributor.getInstance(parkingLot);
                    parkingLotService = ParkingLotService.getInstance(ParkingLotDistributor.getInstance(parkingLot));

                    return new CommandResult(true, String.format("Created a parking lot with %d slots ", numberOfSlots));
                default:
                    CommandExecutor commandExecutor = executorMapping.get(command.getType());
                    if (commandExecutor != null) {
                        return commandExecutor.executeIfInitialized(parkingLotService, command);
                    }

            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            return new CommandResult(false, e.getMessage());
        }
        return new CommandResult(false, "Unknown error from command execution service.");
    }

    public CommandExecutor getExecutor(CommandType commandType) {
        return executorMapping.get(commandType);
    }
}
