package tictactoe;

import org.junit.jupiter.api.Test;
import tictactoe.models.*;
import tictactoe.strategies.playing.RandomPlayingStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    private static final int BOARD_SIZE = 3;
    private static final int PLAYERS_COUNT = 2;

    @Test
    public void testCreateGame(){
        Player humanPlayer = HumanPlayer.builder().user(new User()).symbol(GameSymbol.O).build();
        Player bot = BotPlayer.builder().symbol(GameSymbol.X).level(GameLevel.EASY).playingStrategy(new RandomPlayingStrategy()).build();

        Game game = Game.builder().
                withSize(BOARD_SIZE).
                withPlayer(humanPlayer).
                withPlayer(bot).
                build();

        assertEquals(BOARD_SIZE, game.getBoard().getSize());
        assertEquals(PLAYERS_COUNT, game.getPlayers().size());
        assertSame(game.getStatus(), GameStatus.IN_PROGRESS);

    }

    @Test
    public void testCreateBoard(){
        Board board = new Board(3);
        List<BoardCell> firstRow = board.getCells().getFirst();
        assertEquals( 3, board.getCells().size(),  "If the ctor of the board is called with n, it should create n rows.");
        assertEquals( 3, firstRow.size(),  "If the ctor of the board is called with n, it should create n*n cells.");
    }


}
