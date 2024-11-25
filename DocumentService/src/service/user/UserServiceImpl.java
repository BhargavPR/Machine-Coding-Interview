package service.user;

import database.Database;
import model.User;

public class UserServiceImpl implements UserService {

    private final Database database;

    public UserServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public User addUser(String name) {
        User user = new User(name);
        database.addUser(user);
        return user;
    }

}
