package edu.colostate.cs.cs414.StringCheese.src;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

public class King extends ChessPiece implements Serializable {

    private HashSet<String> legalMoves;
    private static final long serialVersionUID = -5130928689559383866L;

    public King(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves() {
        legalMoves.clear();
        legalMoves.addAll(getNextDiagonals(getPosition(), getColor()));
        legalMoves.addAll(getNextForward(getPosition(), getColor()));
        legalMoves.addAll(getPrevBackward(getPosition(), getColor()));
        legalMoves.addAll(getPrevDiagonals(getPosition(), getColor()));
        legalMoves.addAll(getSideways(getPosition(), getColor()));
        if (isInnerCorner(getPosition())) {
            addOuterCorner();
        }
        legalMoves = removePositionsWithSameColorPiece(legalMoves, getColor());
        return legalMoves;
        //Check if the king is under attack

        /*
        if (isInCheck()) {
            //If so, choose king to move or move another piece to let King not be attacked, otherwise it is checkmate.
            // (The same logic as below, find a movable point, so that the moved king is not attacked. Another piece moves beyond the scope of this function)

            //Make sure that king is not in the attack range after moving
            HashSet<String> res = new HashSet<>();
            String pos = getPosition();
            for (String move : legalMoves) {
                //pseudo move king then see if in check
                setPosition(move);
                if (!isInCheck()) {
                    res.add(move);
                }
            }
            setPosition(pos);
            return res;
        }
        return legalMoves;
*/
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

    public boolean isInCheck(){
        String position = getPosition();
        for(String pos: board.innerRing){
            ChessPiece p = board.getPiece(pos);
            //check if any piece of the opposite color contains the square the king is on
            if(p!=null && p.getColor()!=getColor() && !p.getPosition().equals(getPosition())){
                if(p.legalMoves().contains(position)){
                    return true;
                }
            }
        }
        for(String pos: board.outerRing){
            ChessPiece p = board.getPiece(pos);
            if(p!=null && p.getColor()!=getColor() && !p.getPosition().equals(getPosition())){
                if(p.legalMoves().contains(position)){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2654"; }
        else return "\u265A";
    }
}
