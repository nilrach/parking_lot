package com.gojek.parkinglot.model;

import static com.gojek.parkinglot.model.VehicleType.CAR;

public class Car extends Vehicle {

    public Car(String registrationNumber, String colour) {
        super(registrationNumber, colour);
        this.vehicleType = CAR;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
