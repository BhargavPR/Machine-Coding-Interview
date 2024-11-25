package service.user;

import database.Database;
import model.User;

public class UserServiceImpl implements UserService {

    private final Database database;

    public UserServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void addUser(String name, String gender, Integer age) {
        database.addUser(new User(name, gender, age));
    }


}
