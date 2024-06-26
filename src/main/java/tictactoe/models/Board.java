package tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
@Setter
public class Board {
    private int size;
    private List<List<BoardCell>> cells = new ArrayList<>();

    public Board(int size){
        this.size = size;
        this.cells = initialiseCells(size);
    }

    private List<List<BoardCell>> initialiseCells(int size) {
        List<List<BoardCell>> cells = new ArrayList<>();
        IntStream.range(0, size).forEach(row -> {
            List<BoardCell> rowCells = new ArrayList<>();
            IntStream.range(0, size).forEach(col -> rowCells.add(new BoardCell(row, col)));
            cells.add(rowCells);
        });
        return cells;
    }

    public boolean isEmpty(int row, int col) {
        return getBoardCell(row, col).getSymbol() == null;
    }

    public void update(BoardCell move) {
        int row = move.getRow();
        int col = move.getCol();

        BoardCell cell = getBoardCell(row, col);
        cell.setRow(row);
        cell.setCol(col);
        cell.setSymbol(move.getSymbol());

//        getBoardCell(move.getRow(), move.getCol()).setSymbol(move.getSymbol());
    }

    private BoardCell getBoardCell(int row, int col) {
        return cells.get(row).get(col);
    }

    public void printBoard() {
        for (int i = 0; i < cells.size(); ++i) {
            for (int j = 0; j < cells.size(); ++j) {
                GameSymbol symbol = cells.get(i).get(j).getSymbol();

                if (symbol == null) {
                    System.out.print(" | - | ");
                } else {
                    System.out.printf(" | " + symbol + " | ");
                }
            }
            System.out.print("\n");
        }

    }

    public List<BoardCell> getEmptyCells() {
        return cells.stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getSymbol() == null)
                .toList();
    }
}
