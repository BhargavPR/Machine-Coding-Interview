package strategy;

import database.Database;
import model.Ride;

import java.util.List;
import java.util.Optional;

public class MostVacantRideFinder implements RideFinder {

    private final Database database;

    public MostVacantRideFinder(Database database) {
        this.database = database;
    }

    @Override
    public Optional<Ride> findRide(String source, String destination, Integer requiredSeatCount, String preferredVehicle) {
        List<Ride> rides = database.getRides();
        List<Ride> availableRides = rides.stream()
                .filter(ride ->
                        ride.getSource().equals(source) &&
                                ride.getDestination().equals(destination) &&
                                ride.getAvailableSeat() >= requiredSeatCount)
                .toList();
        if (availableRides.isEmpty()) {
            return Optional.empty();
        }
        return availableRides.stream().max((o1, o2) -> Integer.compare(o2.getAvailableSeat(), o1.getAvailableSeat()));
    }

}
