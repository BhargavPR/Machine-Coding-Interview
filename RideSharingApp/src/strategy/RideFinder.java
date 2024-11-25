package strategy;

import model.Ride;

import java.util.Optional;

public interface RideFinder {

    Optional<Ride> findRide(String source, String destination, Integer requiredSeatCount, String preferredVehicle);

}
