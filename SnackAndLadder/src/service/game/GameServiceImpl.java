package service.game;

import database.Database;
import model.Board;
import model.Ladder;
import model.Snack;
import model.game.Game;
import model.game.GameStatus;
import model.move.Move;
import utils.DiceUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GameServiceImpl implements GameService {

    private final Database database;

    public GameServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Game startGame(Board board, List<String> playerIds) {
        Game game = new Game(board.getId(), playerIds);
        database.addGame(game);

        int turn = 0;
        while (!isGameCompleted(game.getId())) {
            String currentPlayerId = playerIds.get(turn % playerIds.size());
            int diceValue = DiceUtils.roll();
            Move move = makeMove(game.getId(), currentPlayerId, diceValue);
            game.getMoves().add(move);
            System.out.println(database.getPlayer(currentPlayerId).get().getName() + " roll " + diceValue + " move from " + move.getStartPosition() + " to " + move.getEndPosition());
            turn++;
        }
        game.setStatus(GameStatus.COMPLETED);
        return game;
    }

    @Override
    public Move makeMove(String gameId, String playerId, int diceValue) {
        Optional<Game> gameOptional = database.getGame(gameId);
        if (gameOptional.isEmpty()) {
            throw new RuntimeException("Game not found");
        }

        Game game = gameOptional.get();
        Optional<Board> boardOptional = database.getBoard(game.getBoardId());
        if (boardOptional.isEmpty()) {
            throw new RuntimeException("Board not found");
        }

        Board board = boardOptional.get();
        if (!game.getPositionMap().containsKey(playerId)) {
            throw new RuntimeException("Invalid player id");
        }

        int currentPosition = game.getPositionMap().get(playerId);
        int nextPosition = getNextPosition(board, currentPosition, diceValue);
        game.getPositionMap().put(playerId, nextPosition);
        return new Move(playerId, currentPosition, nextPosition);
    }

    @Override
    public Integer getPlayerPosition(String gameId, String playerId) {
        Optional<Game> gameOptional = database.getGame(gameId);
        if (gameOptional.isEmpty()) {
            throw new RuntimeException("Game not found");
        }

        Game game = gameOptional.get();
        if (!game.getPositionMap().containsKey(playerId)) {
            throw new RuntimeException("Invalid player id");
        }

        return game.getPositionMap().get(playerId);
    }

    @Override
    public Boolean isGameCompleted(String gameId) {
        Optional<Game> gameOptional = database.getGame(gameId);
        if (gameOptional.isEmpty()) {
            throw new RuntimeException("Game not found");
        }

        Game game = gameOptional.get();
        Optional<Board> boardOptional = database.getBoard(game.getBoardId());
        if (boardOptional.isEmpty()) {
            throw new RuntimeException("Board not found");
        }

        Board board = boardOptional.get();
        for (String playerId : game.getPlayerIds()) {
            if (getPlayerPosition(gameId, playerId) >= board.getSize()) {
                return true;
            }
        }
        return false;
    }

    private Integer getNextPosition(Board board, Integer currentPosition, Integer diceValue) {
        int nextPosition = currentPosition + diceValue;
        Snack snack = getSnack(board, nextPosition);
        Ladder ladder = getLadder(board, nextPosition);
        if (Objects.nonNull(snack)) {
            return snack.getEnd();
        } else if (Objects.nonNull(ladder)) {
            return ladder.getEnd();
        } else {
            if (nextPosition > board.getSize()) {
                return currentPosition;
            } else {
                return nextPosition;
            }
        }
    }

    private Snack getSnack(Board board, int position) {
        return board.getSnacks().stream()
                .filter(snack -> snack.getStart() == position)
                .findAny()
                .orElse(null);
    }

    private Ladder getLadder(Board board, int position) {
        return board.getLadders().stream()
                .filter(ladder -> ladder.getStart() == position)
                .findAny()
                .orElse(null);
    }


}
