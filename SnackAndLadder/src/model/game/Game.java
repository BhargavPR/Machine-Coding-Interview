package model.game;

import model.move.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Game {

    private String id;
    private String boardId;
    private List<String> playerIds;
    private HashMap<String, Integer> positionMap;
    private List<Move> moves;
    private GameStatus status;

    public Game(String boardId, List<String> playerIds) {
        this.id = UUID.randomUUID().toString();
        this.boardId = boardId;
        this.playerIds = playerIds;
        this.moves = new ArrayList<>();
        this.status = GameStatus.IN_PROGRESS;
        this.positionMap = new HashMap<>();
        playerIds.forEach(playerId -> positionMap.put(playerId, 0));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public List<String> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<String> playerIds) {
        this.playerIds = playerIds;
    }

    public HashMap<String, Integer> getPositionMap() {
        return positionMap;
    }

    public void setPositionMap(HashMap<String, Integer> positionMap) {
        this.positionMap = positionMap;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

}
