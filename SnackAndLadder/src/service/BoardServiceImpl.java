package service;

import database.Database;
import model.Board;
import model.Ladder;
import model.Snack;

import java.util.List;
import java.util.UUID;

public class BoardServiceImpl implements BoardService {

    private final Database database;

    public BoardServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Board createBoard(Integer size, List<Snack> snacks, List<Ladder> ladders) {
        Board board = new Board(UUID.randomUUID().toString(), size, snacks, ladders);
        database.addBoard(board);
        return board;
    }

    @Override
    public Board getBoard(String boardId) {
        return database.getBoard(boardId)
                .orElse(null);
    }
}
