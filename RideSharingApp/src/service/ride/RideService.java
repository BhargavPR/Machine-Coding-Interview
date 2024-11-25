package service.ride;

public interface RideService {

    void createRide(String rideId, String ownerName, String source, String destination, Integer availableSeats, String vehicleRegistrationNumber);

    void findRide(String riderName, String source, String destination, Integer requiredSeatCount, String preferredVehicle, String type);

    void endRide(String rideId);

    void findRideTakenByUser(String name);

    void findRideOfferedByUser(String name);

    void displayRideStatistics();

}
