package model;

public class Ride {

    private String rideId;
    private String ownerName;
    private String source;
    private String destination;
    private Integer availableSeat;
    private String vehicleRegistrationNumber;
    private RideStatus status;

    public Ride(String rideId, String ownerName, String source, String destination, Integer availableSeat, String vehicleRegistrationNumber, RideStatus status) {
        this.rideId = rideId;
        this.ownerName = ownerName;
        this.source = source;
        this.destination = destination;
        this.availableSeat = availableSeat;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.status = status;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
