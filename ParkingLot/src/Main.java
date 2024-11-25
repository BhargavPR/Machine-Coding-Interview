import constants.Constants;
import database.Database;
import model.parking.ParkingSlotType;
import service.ParkingLotService;
import service.ParkingLotServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Database database = Database.getInstance();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(database);
        String parkingLotId = null;

        Scanner scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case Constants.CREATE_PARKING_LOT -> {
                    parkingLotId = input[1];
                    parkingLotService.createParkingLot(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                }
                case Constants.DISPLAY -> {
                    if (input[1].equals(Constants.FREE_COUNT)) {
                        parkingLotService.printAvailableParkingLotCountByType(parkingLotId, ParkingSlotType.valueOf(input[2]));
                    } else if (input[1].equals(Constants.FREE_SLOTS)) {
                        parkingLotService.printAvailableParkingLotByType(parkingLotId, ParkingSlotType.valueOf(input[2]));
                    } else if (input[1].equals(Constants.OCCUPIED_SLOTS)) {
                        parkingLotService.printOccupiedParkingLotByType(parkingLotId, ParkingSlotType.valueOf(input[2]));
                    }
                }
                case Constants.PARK_VEHICLE -> {
                    parkingLotService.parkVehicle(parkingLotId, input[1], input[2], input[3]);
                }
                case Constants.UN_PARK_VEHICLE -> {
                    parkingLotService.unParkVehicle(input[1]);
                }
            }
        }
    }
}