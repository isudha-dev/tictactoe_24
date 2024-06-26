package tictactoe.models;

import lombok.Getter;
import tictactoe.exceptions.InvalidMoveException;
import tictactoe.strategies.winning.OrderOneWinDrawStrategy;
import tictactoe.strategies.winning.WinDrawStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Game {
    private static final int DEFAULT_PLAYER_COUNT = 2;
    private static final GameStatus DEFAULT_GAME_STATUS = GameStatus.IN_PROGRESS;
    private Board board;
    private List<Player> players = new ArrayList<>();
    private GameStatus status;
    private int nextPlayerIndex = 0;
    private WinDrawStrategy winDrawStrategy;

    private Game(){

    }

    public static Builder builder(){
        return new Builder();
    }

    public void start() {
        nextPlayerIndex = (int) (Math.random() * players.size());
        status = GameStatus.IN_PROGRESS;
    }

    public void makeMove() {
        BoardCell move = getNextMove();
        board.update(move);

        if(checkWinner(board, move)){
            status = GameStatus.FINISHED;
            System.out.println("Game won by player with symbol: "+ move.getSymbol());
            return;
        }

        if(checkDraw()){
            status = GameStatus.DRAWN;
            System.out.println("Game drawn. Restart game.");
            return;
        }

        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
    }

    private void validateMove(BoardCell move) {
        if(!board.isEmpty(move.getRow(), move.getCol())){
            throw new InvalidMoveException(move.getRow(), move.getCol());
        }
    }

    public Player getNextPlayer() {
        return players.get(nextPlayerIndex);
    }

    private BoardCell getNextMove() {
        Player player = players.get(nextPlayerIndex);

        BoardCell move =  player.makeMove(board);
        validateMove(move);

        return move;
    }

    private boolean checkWinner(Board board, BoardCell move) {
        return winDrawStrategy.checkWinner(board, move);
    }

    private boolean checkDraw() {
        return winDrawStrategy.checkDraw();
    }

    public static class Builder {
        private Game game;

        private Builder(){
            this.game = new Game();
        }

        public Builder withSize(int size){
            this.game.board = new Board(size);
            return this;
        }

        public Builder withPlayer(Player player){
            this.game.players.add(player);
            return this;
        }

        public Game build(){
            boolean isValid = validate();
            if(!isValid){
                throw new RuntimeException("Invalid game");
            }
            Game newGame = new Game();
            newGame.board = game.board;
            newGame.players = game.players;
            newGame.winDrawStrategy = new OrderOneWinDrawStrategy(newGame.board.getSize());
            return newGame;
        }

        private boolean validate() {
            if(this.game.players.size() != this.game.board.getSize()-1){
                return false;
            }

            Set<GameSymbol> symbolSet = this.game.getPlayers().stream().map(
                    Player::getSymbol
            ).collect(Collectors.toSet());

            return symbolSet.size() == this.game.players.size();
        }
    }
}
