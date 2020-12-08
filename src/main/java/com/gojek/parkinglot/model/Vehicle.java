package com.gojek.parkinglot.model;

public abstract class Vehicle {
    protected final String registrationNumber;
    protected final String colour;
    protected VehicleType vehicleType;

    public Vehicle(String registrationNumber, String colour) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract VehicleType getVehicleType();

    public String getColour() {
        return colour;
    }

}
