package edu.colostate.cs.cs414.StringCheese.src;

import java.util.HashSet;

public class King extends ChessPiece {

    private HashSet<String> legalMoves;

    public King(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves() { //throws IllegalPositionException {
        //FIXME currently doesn't check if the move places the king into check
        legalMoves.clear();
        legalMoves.addAll(getNextDiagonals(getPosition()));
        legalMoves.addAll(getNextForward(getPosition()));
        legalMoves.addAll(getPrevBackward(getPosition()));
        legalMoves.addAll(getPrevDiagonals(getPosition()));
        legalMoves.addAll(getSideways(getPosition()));
        return legalMoves;
}

    public String toString(){
        if (getColor() == Color.White) { return "\u2654"; }
        else return "\u265A";
    }
}
