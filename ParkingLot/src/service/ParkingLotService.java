package service;

import model.parking.ParkingSlotType;

public interface ParkingLotService {

    void createParkingLot(String parkingLotId, Integer floors, Integer slots);

    void parkVehicle(String parkingLotId, String vehicleType, String vehicleNumber, String vehicleColor);

    void unParkVehicle(String ticketId);

    void printAvailableParkingLotCountByType(String parkingLotId, ParkingSlotType parkingSlotType);

    void printAvailableParkingLotByType(String parkingLotId, ParkingSlotType parkingSlotType);

    void printOccupiedParkingLotByType(String parkingLotId, ParkingSlotType parkingSlotType);

}
