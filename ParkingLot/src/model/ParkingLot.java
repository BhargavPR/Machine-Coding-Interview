package model;

import model.parking.ParkingSlot;

import java.util.List;

public class ParkingLot {

    private String id;
    private Integer floors;
    private Integer slots;
    private List<ParkingSlot> parkingSlots;

    public ParkingLot(String id, Integer floors, Integer slots, List<ParkingSlot> parkingSlots) {
        this.id = id;
        this.floors = floors;
        this.slots = slots;
        this.parkingSlots = parkingSlots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }
}
