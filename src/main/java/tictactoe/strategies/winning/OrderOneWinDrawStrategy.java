package tictactoe.strategies.winning;


import tictactoe.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinDrawStrategy implements WinDrawStrategy {
    private int dimension;

    List<HashMap<GameSymbol, Integer>> rows = new ArrayList<>();
    List<HashMap<GameSymbol, Integer>> cols = new ArrayList<>();
    HashMap<GameSymbol, Integer> diag1 = new HashMap<>(); // left to right
    HashMap<GameSymbol, Integer> diag2 = new HashMap<>(); // right to left

    public OrderOneWinDrawStrategy(int dimension){
        this.dimension = dimension;
        for(int i = 0; i < dimension; i++){
            rows.add(new HashMap<>());
            cols.add(new HashMap<>());
        }
    }

    private boolean isLeftToRightDiagonal(int row, int col){
        return row == col;
    }

    private boolean isRightToLeftDiagonal(int row, int col){
        return row+col == dimension-1;
    }

    @Override
    public boolean checkWinner(Board board, BoardCell lastPlayedMove) {
        // TODO: implement this

        GameSymbol symbol =  lastPlayedMove.getSymbol();
        int row = lastPlayedMove.getRow();
        int col = lastPlayedMove.getCol();

        rows.get(row).put(symbol, rows.get(row).getOrDefault(symbol, 0) + 1);
        cols.get(col).put(symbol, cols.get(col).getOrDefault(symbol, 0) + 1);

        if(isLeftToRightDiagonal(row, col)){
            diag1.put(symbol, diag1.getOrDefault(symbol, 0)+1);
        }

        if(isRightToLeftDiagonal(row, col)){
            diag2.put(symbol, diag2.getOrDefault(symbol, 0)+1);
        }

        if((rows.get(row).get(symbol) == dimension) || cols.get(col).get(symbol) == dimension) {
            return true;
        }
        if(isLeftToRightDiagonal(row, col) && diag1.get(symbol) == dimension) {
            return true;
        }
        if(isRightToLeftDiagonal(row, col) && diag2.get(symbol) == dimension) {
            return true;
        }

        return false;
    }

    public boolean checkDraw(){
        List<Integer> sums = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            sums.add(rows.get(i).values().stream().mapToInt(Integer::intValue).sum());
        }

        if(sums.stream().filter(v -> v == 3).count() == 3){
            return true;
        }

        return false;
    }
}
