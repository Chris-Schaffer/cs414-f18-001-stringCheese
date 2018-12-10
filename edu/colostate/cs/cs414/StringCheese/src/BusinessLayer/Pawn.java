package edu.colostate.cs.cs414.StringCheese.src.BusinessLayer;

import java.util.HashSet;

public class Pawn extends ChessPiece {

    private HashSet<String> legalMoves;

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves(){ //throws IllegalPositionException {
        legalMoves.clear();
        legalMoves.addAll(getNextForward(getPosition(), getColor()));
        legalMoves.addAll(getNextDiagonals(getPosition(), getColor()));
        return legalMoves;
    }



    public String toString(){
        if (getColor() == Color.White) { return "\u2659"; }
        else return "\u265F";
    }
}
