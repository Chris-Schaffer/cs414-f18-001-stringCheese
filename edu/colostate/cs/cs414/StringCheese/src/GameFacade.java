package edu.colostate.cs.cs414.StringCheese.src;

import java.util.HashSet;

public class GameFacade {

    private ChessBoard board;

    public GameFacade(){
        board = new ChessBoard();
        board.initialize();
    }

    public HashSet<String> getValidMoves(String position) {
        return board.selectPiece(position);
    }

    public String getType(String position) {
       return board.getPieceType(position);
    }
}
