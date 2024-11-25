package model;

import java.util.UUID;

public class Vehicle {

    private String id;
    private String ownerName;
    private String model;
    private String registrationNumber;

    public Vehicle(String ownerName, String model, String registrationNumber) {
        this.id = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.model = model;
        this.registrationNumber = registrationNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
