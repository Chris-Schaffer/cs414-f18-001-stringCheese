package edu.colostate.cs.cs414.StringCheese.src;

import java.util.HashSet;


public class Bishop extends ChessPiece {

    private HashSet<String> legalMoves;

    public Bishop(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves(){
        String position = getPosition();



        return legalMoves;
    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2657"; }
        else return "\u265D";
    }
}
