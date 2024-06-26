package tictactoe.strategies.playing;

import tictactoe.models.Board;
import tictactoe.models.BoardCell;

public interface PlayingStrategy {

    BoardCell makeMove(Board board);
}
