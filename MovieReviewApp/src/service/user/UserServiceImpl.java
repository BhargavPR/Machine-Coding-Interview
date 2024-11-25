package service.user;

import database.Database;
import model.user.User;
import model.user.UserType;

public class UserServiceImpl implements UserService {

    private final Database database;

    public UserServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void addUser(String name) {
        User user = new User(name, UserType.REGULAR);
        database.addUser(user);
    }

}
