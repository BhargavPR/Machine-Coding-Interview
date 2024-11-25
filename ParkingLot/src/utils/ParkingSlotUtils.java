package utils;

public class ParkingSlotUtils {

    public static String getParkingSlotId(String parkingLotId, int floor, int slot) {
        return parkingLotId + "_" + floor + "_" + slot;
    }

}
