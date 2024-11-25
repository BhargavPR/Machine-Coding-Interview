package database;

import model.Board;
import model.Player;
import model.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    private final List<Player> players = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Game> games = new ArrayList<>();


    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {

    }

    public void addBoard(Board board) {
        boards.add(board);
    }

    public Optional<Board> getBoard(String boardId) {
        return boards.stream()
                .filter(board -> board.getId().equals(boardId))
                .findFirst();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Optional<Player> getPlayer(String id) {
        return players.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public Optional<Game> getGame(String gameId) {
        return games.stream()
                .filter(game -> game.getId().equals(gameId))
                .findFirst();
    }

}
