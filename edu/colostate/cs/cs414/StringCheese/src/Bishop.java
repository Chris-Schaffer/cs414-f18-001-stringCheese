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

        HashSet<String> moves = new HashSet<>();
        boolean hasBounced = false;
        boolean hasMoved = false;
        String prevPosition = "";
        return getNextMovesRecurse(position,prevPosition,hasBounced,moves);
    }

    private HashSet<String> getNextMovesRecurse(String position, String prevPosition, boolean hasBounced,HashSet<String> moves){
        HashSet<String> nextMoves = getNextDiagonals(position, getColor());

        //base case if it has moved and has bounced then there are no legal moves left
        if(nextMoves.size() == 1 && hasBounced && prevPosition.equals("")){
            return moves;
        }

        //bishop has moved but not bounced, add next move set bounced to true
        else if(nextMoves.size() == 1 && !hasBounced && prevPosition.equals("")){

        }

        return null;
    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2657"; }
        else return "\u265D";
    }
}
