package service.vehicle;

import database.Database;
import model.Vehicle;

public class VehicleServiceImpl implements VehicleService {

    private final Database database;

    public VehicleServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void addVehicle(String name, String model, String number) {
        database.addVehicle(new Vehicle(name, model, number));
    }

}
