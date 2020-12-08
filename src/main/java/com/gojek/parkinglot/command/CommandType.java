package com.gojek.parkinglot.command;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    CREATE("create_parking_lot", 1),
    PARK("park", 2),
    LEAVE("leave", 1),
    GET_REGISTRATION_NUM_FOR_COLOUR("registration_numbers_for_cars_with_colour", 1),
    STATUS("status", 0);

    private final String userFacingInput;
    private final Integer numberOfParams;

    CommandType(String userFacingInput, Integer numberOfParams) {
        this.userFacingInput = userFacingInput;
        this.numberOfParams = numberOfParams;
    }

    public String getUserFacingInput() {
        return userFacingInput;
    }

    public static Optional<CommandType> ofType(String userFacingInput) {
        Optional<CommandType> commandType = Arrays.stream(CommandType.values()).
                filter(c -> c.getUserFacingInput().equalsIgnoreCase(userFacingInput))
                .findAny();
        return commandType;

    }
}
