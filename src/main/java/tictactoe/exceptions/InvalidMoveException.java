package tictactoe.exceptions;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(int row, int col){
        super("Invalid move by player for row: "+row + "and col: "+col);
    }
}
