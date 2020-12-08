package com.gojek.parkinglot.model;

public class ParkingSlot {
    private final Integer number;

    public ParkingSlot(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingSlot that = (ParkingSlot) o;

        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
