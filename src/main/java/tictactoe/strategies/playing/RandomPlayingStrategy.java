package tictactoe.strategies.playing;

import tictactoe.models.Board;
import tictactoe.models.BoardCell;

import java.util.List;

public class RandomPlayingStrategy implements PlayingStrategy{
    @Override
    public BoardCell makeMove(Board board) {

        List<BoardCell> emptyCells = board.getEmptyCells();
        int randomIndex = (int) (Math.random() * emptyCells.size());

        BoardCell boardCell = emptyCells.get(randomIndex);
        return new BoardCell(boardCell.getRow(), boardCell.getCol());

    }
}
