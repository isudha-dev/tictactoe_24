package tictactoe.strategies.winning;

import tictactoe.models.Board;
import tictactoe.models.BoardCell;

public interface WinDrawStrategy {
    public boolean checkWinner(Board board, BoardCell boardCell);
    public boolean checkDraw();
}
