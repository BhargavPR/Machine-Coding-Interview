package model.vehicle;

import java.util.UUID;

public class Vehicle {

    private String id;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String color;

    public Vehicle(VehicleType vehicleType, String vehicleNumber, String color) {
        this.id = UUID.randomUUID().toString();
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
