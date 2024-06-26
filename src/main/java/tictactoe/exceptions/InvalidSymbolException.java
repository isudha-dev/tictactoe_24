package tictactoe.exceptions;

public class InvalidSymbolException extends RuntimeException {
    public InvalidSymbolException(){
        super("Invalid symbol, restart the game");
    }
}
