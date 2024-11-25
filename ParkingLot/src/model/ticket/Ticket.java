package model.ticket;

import java.util.UUID;

public class Ticket {

    private String id;
    private String vehicleNumber;
    private String parkingLotId;

    public Ticket(String id, String vehicleNumber, String parkingLotId) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.parkingLotId = parkingLotId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
