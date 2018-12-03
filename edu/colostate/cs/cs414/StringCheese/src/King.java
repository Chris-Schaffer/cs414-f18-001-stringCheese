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
        //先检查king是不是处于被攻击状态
        if(isUnderAttack()){
            //是的话选择king移动或者另一个棋子移动过来让king不受攻击，不然就是checkmate了
            // (和下面逻辑是一模一样的，找到一个可移动的点，让移动后的king不受攻击。另一个棋子移动过来不在这个函数考察范围)
            legalMoves.clear();
            legalMoves.addAll(getNextDiagonals(getPosition(), getColor()));
            legalMoves.addAll(getNextForward(getPosition(), getColor()));
            legalMoves.addAll(getPrevBackward(getPosition(), getColor()));
            legalMoves.addAll(getPrevDiagonals(getPosition(), getColor()));
            legalMoves.addAll(getSideways(getPosition(), getColor()));
            if (isInnerCorner(getPosition())) {
                addOuterCorner();
            }
            HashSet<String> tmpLegalMoves = removePositionsWithSameColorPiece(legalMoves, getColor());
            //确保移动后king不处于被攻击范围
            HashSet<String> res = new HashSet<>();
            String pos = getPosition();
            for(String move: tmpLegalMoves) {
                board.placePiece(this,move);
                if(!isUnderAttack()) {
                    res.add(move);
                }
                board.placePiece(this,pos);
            }
            return res;
        }else {
            //不是的话只要正常的legalmove就好了，
            legalMoves.clear();
            legalMoves.addAll(getNextDiagonals(getPosition(), getColor()));
            legalMoves.addAll(getNextForward(getPosition(), getColor()));
            legalMoves.addAll(getPrevBackward(getPosition(), getColor()));
            legalMoves.addAll(getPrevDiagonals(getPosition(), getColor()));
            legalMoves.addAll(getSideways(getPosition(), getColor()));
            if (isInnerCorner(getPosition())) {
                addOuterCorner();
            }
            HashSet<String> tmpLegalMoves = removePositionsWithSameColorPiece(legalMoves, getColor());
            //确保移动后king不处于被攻击范围
            HashSet<String> res = new HashSet<>();
            String pos = getPosition();
            for(String move: tmpLegalMoves) {
                board.placePiece(this,move);
                if(!isUnderAttack()) {
                    res.add(move);
                }
                board.placePiece(this,pos);
            }
            return res;
        }
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

    private boolean isUnderAttack(){
        String position = getPosition();
        for(String pos: board.innerRing){
            ChessPiece p = board.getPiece(pos);
            if(p!=null && p.getColor()!=getColor()){
                if(p.legalMoves().contains(position)){
                    return true;
                }
            }
        }
        for(String pos: board.outerRing){
            ChessPiece p = board.getPiece(pos);
            if(p!=null && p.getColor()!=getColor()){
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
