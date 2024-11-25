package service.user;

import database.Database;
import model.doctor.Doctor;
import model.doctor.Speciality;
import model.user.User;

public class UserServiceImpl implements UserService {

    private final Database database;

    public UserServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void registerUser(String name) {
        User user = new User(name);
        database.addUser(user);
        System.out.println(name + " registered successfully.");
    }

    @Override
    public void registerDoctor(String name, String speciality) {
        Doctor doctor = new Doctor(name, Speciality.valueOf(speciality.toUpperCase().replace(" ", "_")));
        database.addDoctor(doctor);
        System.out.println("Welcome Dr. " + name + " !!");
    }

}
