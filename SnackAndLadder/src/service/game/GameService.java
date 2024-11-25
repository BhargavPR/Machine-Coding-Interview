package service.game;

import model.Board;
import model.game.Game;
import model.move.Move;

import java.util.List;

public interface GameService {

    Game startGame(Board board, List<String> playerIds);

    Move makeMove(String gameId, String playerId, int diceValue);

    Integer getPlayerPosition(String gameId, String playerId);

    Boolean isGameCompleted(String gameId);

}
