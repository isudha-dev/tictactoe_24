package tictactoe;

import tictactoe.exceptions.InvalidSymbolException;
import tictactoe.models.*;
import tictactoe.strategies.playing.RandomPlayingStrategy;

import java.util.Scanner;

public class Main {

    private static final int BOARD_SIZE = 3;

    public static void main(String[] args) {
        HumanPlayer humanPlayer = getUserInput();

        System.out.println("Human player created with symbol - "+ humanPlayer.getSymbol());

        Game game = createGame(humanPlayer);
        game.start();

        while (game.getStatus() == GameStatus.IN_PROGRESS){
            Player player = game.getNextPlayer();
            System.out.println("Next player: " + player.getSymbol());

            game.makeMove();
            game.getBoard().printBoard();
        }

    }

    private static Game createGame(HumanPlayer humanPlayer) {
        Player bot = BotPlayer.builder().symbol(decideBotSymbol(humanPlayer)).level(GameLevel.EASY).playingStrategy(new RandomPlayingStrategy()).build();

        return Game.builder().
                withSize(BOARD_SIZE).
                withPlayer(humanPlayer).
                withPlayer(bot).
                build();

    }

    // TODO get the list of all symbols
    // Filter out used symbols
    // Randomly select one of from remaining list
    private static GameSymbol decideBotSymbol(HumanPlayer humanPlayer) {
        if(humanPlayer.getSymbol() == GameSymbol.O)
            return GameSymbol.X;

        return GameSymbol.O;
    }

    private static HumanPlayer getUserInput() {
        System.out.println("Welcome to TicTacToe");
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter your name - ");
        String name = scn.next();

        System.out.println("Enter your email - ");
        String email = scn.next();

        System.out.println("Select a symbol (X or O) : ");
        String symbol = scn.next();
        GameSymbol gameSymbol;
        try {
            gameSymbol = GameSymbol.valueOf(symbol);
        } catch (IllegalArgumentException e) {
            throw new InvalidSymbolException();
        }

        User user = new User(name, email, null);
        return new HumanPlayer(gameSymbol, user);

    }
}