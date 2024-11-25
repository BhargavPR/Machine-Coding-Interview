import constants.Constants;
import database.Database;
import model.Board;
import model.Ladder;
import model.Player;
import model.Snack;
import service.BoardService;
import service.BoardServiceImpl;
import service.game.GameService;
import service.game.GameServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));

        Database database = Database.getInstance();
        GameService gameService = new GameServiceImpl(database);
        BoardService boardService = new BoardServiceImpl(database);

        List<Snack> snacks = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        Board board = null;

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case Constants.CREATE_BOARD -> {
                    board = boardService.createBoard(Integer.valueOf(input[1]), snacks, ladders);
                }
                case Constants.CREATE_SNACK -> {
                    snacks.add(new Snack(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
                }
                case Constants.CREATE_LADDER -> {
                    ladders.add(new Ladder(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
                }
                case Constants.CREATE_PLAYER -> {
                    Player player = new Player(input[1]);
                    players.add(player);
                    database.addPlayer(player);
                }
                case Constants.START_GAME -> {
                    gameService.startGame(board, players.stream()
                            .map(Player::getId)
                            .collect(Collectors.toList()));
                }


            }
        }


    }
}