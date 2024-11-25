package strategy;

import database.Database;
import model.Ride;
import model.Vehicle;

import java.util.List;
import java.util.Optional;

public class PreferredVehicleRideFinder implements RideFinder {

    private final Database database;

    public PreferredVehicleRideFinder(Database database) {
        this.database = database;
    }

    @Override
    public Optional<Ride> findRide(String source, String destination, Integer requiredSeatCount, String preferredVehicle) {
        List<Ride> rides = database.getRides();
        for (Ride ride : rides) {
            Vehicle vehicle = database.getVehicle(ride.getOwnerName());
            if (ride.getSource().equals(source) &&
                    ride.getDestination().equals(destination) &&
                    ride.getAvailableSeat() >= requiredSeatCount &&
                    vehicle.getModel().equals(preferredVehicle)) {
                return Optional.of(ride);
            }
        }
        return Optional.empty();
    }

}