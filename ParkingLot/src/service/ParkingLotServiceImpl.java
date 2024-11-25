package service;

import database.Database;
import model.ParkingLot;
import model.parking.ParkingSlot;
import model.parking.ParkingSlotType;
import model.ticket.Ticket;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;
import utils.ParkingSlotUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingLotServiceImpl implements ParkingLotService {

    private final Database database;

    public ParkingLotServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void createParkingLot(String parkingLotId, Integer floors, Integer slots) {
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        for (int floor = 1; floor <= floors; floor++) {
            for (int slot = 1; slot <= slots; slot++) {
                ParkingSlot parkingSlot = getParkingLot(parkingLotId, floor, slot);
                parkingSlots.add(parkingSlot);
                database.addParkingSlot(ParkingSlotUtils.getParkingSlotId(parkingLotId, floor, slot), parkingSlot);
            }
        }
        database.addParkingLot(new ParkingLot(parkingLotId, floors, slots, parkingSlots));
    }

    @Override
    public void parkVehicle(String parkingLotId, String vehicleType, String vehicleNumber, String vehicleColor) {
        Ticket ticket = findParking(parkingLotId, vehicleType, vehicleNumber, vehicleColor);
        if (Objects.isNull(ticket)) {
            System.out.println("Parking Lot Full");
        } else {
            System.out.println("Parked vehicle. Ticket ID: " + ticket.getId());
        }
    }

    @Override
    public void unParkVehicle(String ticketId) {
        Ticket ticket = database.getTicket(ticketId);
        if (Objects.isNull(ticket)) {
            System.out.println("Invalid ticket");
            return;
        }

        Vehicle vehicle = database.getVehicle(ticket.getVehicleNumber());
        database.removeTicket(ticketId);
        database.removeVehicle(vehicle.getVehicleNumber());

        System.out.println("Unparked vehicle with Registration Number: " + ticket.getVehicleNumber());
    }

    @Override
    public void printAvailableParkingLotCountByType(String parkingLotId, ParkingSlotType parkingSlotType) {
        ParkingLot parkingLot = database.getParkingLot(parkingLotId);
        for (int floor = 1; floor <= parkingLot.getFloors(); floor++) {
            int count = 0;
            for (int slot = 1; slot <= parkingLot.getSlots(); slot++) {
                String ticketId = ParkingSlotUtils.getParkingSlotId(parkingLotId, floor, slot);
                Ticket ticket = database.getTicket(ticketId);
                ParkingSlot parkingSlot = database.getParkingSlot(ticketId);

                if (Objects.nonNull(parkingSlot) &&
                        parkingSlot.getType().equals(parkingSlotType) &&
                        Objects.isNull(ticket)) {
                    count++;
                }
            }
            System.out.println("No. of free slots for " + parkingSlotType.toString() + " on Floor " + floor + ": " + count);
        }
    }

    @Override
    public void printAvailableParkingLotByType(String parkingLotId, ParkingSlotType parkingSlotType) {
        ParkingLot parkingLot = database.getParkingLot(parkingLotId);
        for (int floor = 1; floor <= parkingLot.getFloors(); floor++) {
            StringBuilder slotsText = new StringBuilder();
            for (int slot = 1; slot <= parkingLot.getSlots(); slot++) {
                String ticketId = ParkingSlotUtils.getParkingSlotId(parkingLotId, floor, slot);
                Ticket ticket = database.getTicket(ticketId);
                ParkingSlot parkingSlot = database.getParkingSlot(ticketId);

                if (Objects.nonNull(parkingSlot) &&
                        parkingSlot.getType().equals(parkingSlotType) &&
                        Objects.isNull(ticket)) {
                    slotsText.append(slot).append(", ");
                }
            }
            System.out.println("No. of free slots for " + parkingSlotType.toString() + " on Floor " + floor + ": " + slotsText);
        }
    }

    @Override
    public void printOccupiedParkingLotByType(String parkingLotId, ParkingSlotType parkingSlotType) {
        ParkingLot parkingLot = database.getParkingLot(parkingLotId);
        for (int floor = 1; floor <= parkingLot.getFloors(); floor++) {
            StringBuilder slotsText = new StringBuilder();
            for (int slot = 1; slot <= parkingLot.getSlots(); slot++) {
                String ticketId = ParkingSlotUtils.getParkingSlotId(parkingLotId, floor, slot);
                Ticket ticket = database.getTicket(ticketId);
                ParkingSlot parkingSlot = database.getParkingSlot(ticketId);

                if (Objects.nonNull(parkingSlot) &&
                        parkingSlot.getType().equals(parkingSlotType) &&
                        Objects.nonNull(ticket)) {
                    slotsText.append(slot).append(", ");
                }
            }
            System.out.println("Occupied slots for " + parkingSlotType.toString() + " on Floor " + floor + ": " + slotsText);
        }
    }

    private Ticket findParking(String parkingLotId, String vehicleType, String vehicleNumber, String vehicleColor) {
        ParkingLot parkingLot = database.getParkingLot(parkingLotId);
        for (int floor = 1; floor <= parkingLot.getFloors(); floor++) {
            for (int slot = 1; slot <= parkingLot.getSlots(); slot++) {
                String id = ParkingSlotUtils.getParkingSlotId(parkingLotId, floor, slot);
                ParkingSlot parkingSlot = database.getParkingSlot(id);
                Ticket parkingTicket = database.getTicket(id);
                if (Objects.nonNull(parkingSlot) &&
                        Objects.isNull(parkingTicket) &&
                        parkingSlot.getType().equals(ParkingSlotType.valueOf(vehicleType))) {
                    Ticket ticket = new Ticket(id, vehicleNumber, parkingLotId);
                    Vehicle vehicle = new Vehicle(VehicleType.valueOf(vehicleType), vehicleNumber, vehicleColor);
                    database.addTicket(ticket);
                    database.addVehicle(vehicle);
                    return ticket;
                }
            }
        }
        return null;
    }

    private ParkingSlot getParkingLot(String parkingLotId, int floor, int slot) {
        if (slot == 1) {
            return new ParkingSlot(parkingLotId, floor, slot, ParkingSlotType.TRUCK);
        } else if (slot == 2 || slot == 3) {
            return new ParkingSlot(parkingLotId, floor, slot, ParkingSlotType.BIKE);
        }
        return new ParkingSlot(parkingLotId, floor, slot, ParkingSlotType.CAR);
    }

}
