package service.ride;

import database.Database;
import model.Ride;
import model.RideStatus;
import model.User;
import model.Vehicle;
import model.audit.RideAuditRecord;
import strategy.RideFinderContext;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RideServiceImpl implements RideService {

    private final Database database;
    private final RideFinderContext rideFinderContext;

    public RideServiceImpl(Database database) {
        this.database = database;
        this.rideFinderContext = new RideFinderContext(database);
    }

    @Override
    public void createRide(String rideId, String ownerName, String source, String destination, Integer availableSeats, String vehicleRegistrationNumber) {
        Ride rideWithRegistrationNumber = database.getRideByRegistrationNumber(vehicleRegistrationNumber);
        if (Objects.nonNull(rideWithRegistrationNumber)) {
            System.out.println("Ride is already present with given registration number" + vehicleRegistrationNumber);
            return;
        }

        Ride ride = new Ride(rideId, ownerName, source, destination, availableSeats, vehicleRegistrationNumber, RideStatus.ACTIVE);
        RideAuditRecord rideAuditRecord = new RideAuditRecord(rideId, ownerName, RideAuditRecord.TYPE_OFFERED);
        database.addRide(ride);
        database.addRideAuditRecord(rideAuditRecord);
    }

    @Override
    public void findRide(String riderName, String source, String destination, Integer requiredSeatCount, String preferredVehicle, String type) {
        Optional<Ride> rideOptional = rideFinderContext.findRide(source, destination, requiredSeatCount, preferredVehicle, type);
        if (rideOptional.isEmpty()) {
            System.out.println("No rides found");
        } else {
            Ride ride = rideOptional.get();
            database.addRideAuditRecord(new RideAuditRecord(ride.getRideId(), riderName, RideAuditRecord.TYPE_TAKEN));
            Vehicle vehicle = database.getVehicle(ride.getOwnerName());
            System.out.println("Ride found:- you're travelling with " + vehicle.getOwnerName() + " with " + vehicle.getModel() + " " + vehicle.getRegistrationNumber());
        }
    }

    @Override
    public void endRide(String rideId) {
        Ride ride = database.getRideById(rideId);
        if (Objects.isNull(ride)) {
            System.out.println("Ride not found");
            return;
        }
        ride.setStatus(RideStatus.COMPLETED);
    }

    @Override
    public void findRideTakenByUser(String name) {
        List<RideAuditRecord> rideAuditRecords = database.getRideAuditRecords(name);
        long count = rideAuditRecords.stream()
                .filter(rideAuditRecord -> rideAuditRecord.getType().equals(RideAuditRecord.TYPE_TAKEN))
                .count();
        System.out.println("Ride taken by " + name + ":- " + count);
    }

    @Override
    public void findRideOfferedByUser(String name) {
        List<RideAuditRecord> rideAuditRecords = database.getRideAuditRecords(name);
        long count = rideAuditRecords.stream()
                .filter(rideAuditRecord -> rideAuditRecord.getType().equals(RideAuditRecord.TYPE_OFFERED))
                .count();
        System.out.println("Ride offered by " + name + ":- " + count);
    }

    @Override
    public void displayRideStatistics() {
        List<User> users = database.getUsers();
        for (User user : users) {
            List<RideAuditRecord> rideAuditRecords = database.getRideAuditRecords(user.getName());
            long offeredCount = rideAuditRecords.stream()
                    .filter(rideAuditRecord -> rideAuditRecord.getType().equals(RideAuditRecord.TYPE_OFFERED))
                    .count();
            long takenCount = rideAuditRecords.size() - offeredCount;
            System.out.println(user.getName() + " taken:- " + takenCount + " offered:- " + offeredCount);
        }
    }


}
