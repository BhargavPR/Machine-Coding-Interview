package database;

import model.Ride;
import model.User;
import model.Vehicle;
import model.audit.RideAuditRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Database {

    private final HashMap<String, User> userMap = new HashMap<>();
    private final HashMap<String, Vehicle> vehicleMap = new HashMap<>();
    private final HashMap<String, Ride> rideMap = new HashMap<>();
    private final HashMap<String, List<RideAuditRecord>> rideAuditRecordMap = new HashMap<>();

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {
    }

    public void addUser(User user) {
        userMap.put(user.getName(), user);
    }

    public User getUser(String userName) {
        return userMap.get(userName);
    }

    public List<User> getUsers() {
        return List.copyOf(userMap.values());
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleMap.put(vehicle.getOwnerName(), vehicle);
    }

    public Vehicle getVehicle(String ownerName) {
        return vehicleMap.get(ownerName);
    }

    public void addRide(Ride ride) {
        rideMap.put(ride.getOwnerName(), ride);
    }

    public List<Ride> getRides() {
        return List.copyOf(rideMap.values());
    }

    public Ride getRideById(String rideId) {
        List<Ride> rides = getRides();
        return rides.stream()
                .filter(ride -> ride.getRideId().equals(rideId))
                .findAny().orElse(null);
    }

    public Ride getRideByRegistrationNumber(String registrationNumber) {
        List<Ride> rides = getRides();
        return rides.stream().filter(ride -> ride.getVehicleRegistrationNumber().equals(registrationNumber))
                .findAny()
                .orElse(null);
    }

    public void addRideAuditRecord(RideAuditRecord rideAuditRecord) {
        if (!rideAuditRecordMap.containsKey(rideAuditRecord.getRiderName())) {
            rideAuditRecordMap.put(rideAuditRecord.getRiderName(), new ArrayList<>());
        }
        rideAuditRecordMap.get(rideAuditRecord.getRiderName()).add(rideAuditRecord);
    }

    public List<RideAuditRecord> getRideAuditRecords(String name) {
        List<RideAuditRecord> rideAuditRecords = rideAuditRecordMap.get(name);
        if (Objects.isNull(rideAuditRecords) || rideAuditRecords.isEmpty()) {
            return new ArrayList<>();
        }
        return rideAuditRecords;
    }


}
