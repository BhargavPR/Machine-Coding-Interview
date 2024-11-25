package model.parking;

public class ParkingSlot {

    private String parkingLotId;
    private Integer floor;
    private Integer slot;
    private ParkingSlotType type;

    public ParkingSlot(String parkingLotId,
                       Integer floor,
                       Integer slot,
                       ParkingSlotType type) {
        this.parkingLotId = parkingLotId;
        this.floor = floor;
        this.slot = slot;
        this.type = type;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public ParkingSlotType getType() {
        return type;
    }

    public void setType(ParkingSlotType type) {
        this.type = type;
    }
}
