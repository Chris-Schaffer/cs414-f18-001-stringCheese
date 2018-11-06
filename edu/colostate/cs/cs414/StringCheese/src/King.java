package edu.colostate.cs.cs414.StringCheese.src;

import java.util.Arrays;
import java.util.HashSet;

public class King extends ChessPiece {

    private HashSet<String> legalMoves;

    public King(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves() {
        //FIXME currently doesn't check if the move places the king into check
        //FIXME calling from inner corner does not give outer corner
        legalMoves.clear();
        legalMoves.addAll(getNextDiagonals(getPosition()));
        legalMoves.addAll(getNextForward(getPosition()));
        legalMoves.addAll(getPrevBackward(getPosition()));
        legalMoves.addAll(getPrevDiagonals(getPosition()));
        legalMoves.addAll(getSideways(getPosition()));
        if(isInnerCorner(getPosition())){
            addOuterCorner();
        }
        return removePositionsWithSameColorPiece(legalMoves, getPosition());
}

    private HashSet<String> addOuterCorner() {
        switch (getPosition()) {
            case "b2":
                legalMoves.add("a1");
                break;
            case "b6":
                legalMoves.add("a7");
                break;
            case "f6":
                legalMoves.add("g7");
                break;
                //else it is "f2"
            default:
                legalMoves.add("g1");
                break;
        }
        return legalMoves;
    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2654"; }
        else return "\u265A";
    }
}
