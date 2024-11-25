import constants.Constants;
import database.Database;
import service.ride.RideService;
import service.ride.RideServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import service.vehicle.VehicleService;
import service.vehicle.VehicleServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Database database = Database.getInstance();
        UserService userService = new UserServiceImpl(database);
        VehicleService vehicleService = new VehicleServiceImpl(database);
        RideService rideService = new RideServiceImpl(database);

        Scanner scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case Constants.ADD_USER -> {
                    userService.addUser(input[1], input[2], Integer.valueOf(input[3]));
                }
                case Constants.ADD_VEHICLE -> {
                    vehicleService.addVehicle(input[1], input[2], input[3]);
                }
                case Constants.OFFER_RIDE -> {
                    rideService.createRide(input[1], input[2], input[3], input[7], Integer.valueOf(input[4]), input[6]);
                }
                case Constants.SELECT_RIDE -> {
                    if (input[5].equals(Constants.SELECTION_TYPE_MOST_VACANT)) {
                        rideService.findRide(input[1], input[2], input[3], Integer.valueOf(input[4]), null, input[5]);
                    } else {
                        rideService.findRide(input[1], input[2], input[3], Integer.valueOf(input[4]), input[6], input[5]);
                    }
                }
                case Constants.END_RIDE -> {
                    rideService.endRide(input[1]);
                }
                case Constants.PRINT_RIDE_STATS -> {
                    rideService.displayRideStatistics();
                }
            }
        }
    }
}