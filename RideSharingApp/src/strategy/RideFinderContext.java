package strategy;

import constants.Constants;
import database.Database;
import model.Ride;

import java.util.Optional;

public class RideFinderContext {

    private final RideFinder mostVacantRideFinder;
    private final RideFinder preferredVehicleRideFinder;

    public RideFinderContext(Database database) {
        this.mostVacantRideFinder = new MostVacantRideFinder(database);
        this.preferredVehicleRideFinder = new PreferredVehicleRideFinder(database);
    }

    public Optional<Ride> findRide(String source, String destination, Integer requiredSeatCount, String preferredVehicle, String type) {
        if (Constants.SELECTION_TYPE_MOST_VACANT.equals(type)) {
            return mostVacantRideFinder.findRide(source, destination, requiredSeatCount, preferredVehicle);
        } else if (Constants.SELECTION_TYPE_PREFERRED_VEHICLE.equals(type)) {
            return preferredVehicleRideFinder.findRide(source, destination, requiredSeatCount, preferredVehicle);
        }
        throw new RuntimeException("Unimplemented type found " + type);
    }

}
