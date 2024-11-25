package model;

import java.util.UUID;

public class Player {

    private final String id;
    private final String name;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
