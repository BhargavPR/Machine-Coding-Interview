package service;

import model.Board;
import model.Ladder;
import model.Snack;

import java.util.List;

public interface BoardService {

    Board createBoard(Integer size, List<Snack> snacks, List<Ladder> ladders);

    Board getBoard(String boardId);

}
