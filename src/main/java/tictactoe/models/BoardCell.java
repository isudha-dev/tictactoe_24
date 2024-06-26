package tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardCell {
    private int row;
    private int col;
    private GameSymbol symbol;

    public BoardCell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
