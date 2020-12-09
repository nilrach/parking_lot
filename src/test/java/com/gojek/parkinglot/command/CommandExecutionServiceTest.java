package com.gojek.parkinglot.command;

import com.gojek.parkinglot.command.executor.CommandExecutor;
import com.gojek.parkinglot.command.executor.SlotForColourExecutor;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;

class CommandExecutionServiceTest {

    private CommandExecutionService commandExecutionService;

    @BeforeEach
    public void beforeEach() {
        commandExecutionService = CommandExecutionService.getInstance();
    }

    @Test
    public void shouldBeAbleToGetExecutorForGivenCommand() {
        CommandExecutor executor = commandExecutionService.getExecutor(CommandType.GET_SLOT_NUM_FOR_COLOUR);
        assertThat("Executor is not of correct type", executor, CoreMatchers.instanceOf(SlotForColourExecutor.class));
    }

    @Test
    public void shouldBeAbleToExecuteBootstrappingCreateCommand() {
        LinkedList<String> params = new LinkedList();
        params.add("5");
        Command createCommand = new Command(CommandType.CREATE, params);
        CommandResult commandResult = commandExecutionService.execute(createCommand);
        assertThat("Create command should create new parking lot", commandResult.isSuccess, CoreMatchers.is(true));
    }

    @Test
    public void shouldNotAcceptAnyCommandsOnParkingLotWhichIsNotYetCreated() {
        LinkedList<String> params = new LinkedList();
        params.add("a");
        params.add("red");
        Command parkCommand = new Command(CommandType.PARK, params);
        CommandResult commandResult = commandExecutionService.execute(parkCommand);
        assertThat("Should not accept any commands when parking lot is not yet created", commandResult.isSuccess,
                CoreMatchers.is(false));
    }

}