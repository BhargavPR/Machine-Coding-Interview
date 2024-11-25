package database;

import model.ParkingLot;
import model.parking.ParkingSlot;
import model.ticket.Ticket;
import model.vehicle.Vehicle;

import java.util.HashMap;

public class Database {

    private static Database INSTANCE = null;


    private final HashMap<String, ParkingLot> parkingLotMap = new HashMap<>();
    private final HashMap<String, ParkingSlot> parkingSlotMap = new HashMap<>();
    private final HashMap<String, Vehicle> vehicleMap = new HashMap<>();
    private final HashMap<String, Ticket> ticketMap = new HashMap<>();

    private Database() {
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.put(parkingLot.getId(), parkingLot);
    }

    public ParkingLot getParkingLot(String id) {
        return parkingLotMap.get(id);
    }

    public void addParkingSlot(String id, ParkingSlot parkingSlot) {
        parkingSlotMap.put(id, parkingSlot);
    }

    public ParkingSlot getParkingSlot(String id) {
        return parkingSlotMap.get(id);
    }

    public void addTicket(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String ticketId) {
        return ticketMap.get(ticketId);
    }

    public void removeTicket(String ticketId) {
        ticketMap.remove(ticketId);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleMap.put(vehicle.getVehicleNumber(), vehicle);
    }

    public Vehicle getVehicle(String vehicleNumber) {
        return vehicleMap.get(vehicleNumber);
    }

    public void removeVehicle(String vehicleNumber) {
        vehicleMap.remove(vehicleNumber);
    }

}
