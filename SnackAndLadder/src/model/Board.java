package model;

import java.util.List;

public class Board {

    private final String id;
    private final Integer size;
    private final List<Snack> snacks;
    private final List<Ladder> ladders;

    public Board(String id, Integer size, List<Snack> snacks, List<Ladder> ladders) {
        this.id = id;
        this.size = size;
        this.snacks = snacks;
        this.ladders = ladders;
    }

    public String getId() {
        return id;
    }

    public Integer getSize() {
        return size;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }


}
