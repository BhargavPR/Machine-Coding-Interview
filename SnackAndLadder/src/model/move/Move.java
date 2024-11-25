package model.move;

import java.util.UUID;

public class Move {

    private String id;
    private String playerId;
    private Integer startPosition;
    private Integer endPosition;

    public Move(String playerId, Integer startPosition, Integer endPosition) {
        this.id = UUID.randomUUID().toString();
        this.playerId = playerId;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }
}
