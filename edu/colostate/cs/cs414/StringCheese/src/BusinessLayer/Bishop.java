package edu.colostate.cs.cs414.StringCheese.src.BusinessLayer;

import java.util.HashSet;


public class Bishop extends ChessPiece {

    private enum Direction{DOWNLEFT,DOWNRIGHT,UPLEFT,UPRIGHT,INVALID}

    private HashSet<String> legalMoves;

    public Bishop(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves(){

        String position = getPosition();

        HashSet<String> backwardMoves = new HashSet<>();
        backwardMoves.addAll(getPrevDiagonals(position,this.getColor()));

        HashSet<String> forwardMoves;
        forwardMoves = getNextDiagonals(position,this.getColor());
        HashSet<String> returnedMoves = new HashSet<>();
        for(String nextPosition : forwardMoves){
            Direction direction = computeDirection(position, nextPosition);
            String lastPosition = getMoves(position,direction,returnedMoves);
            Direction oppositeDirection = computeOppositeDirection(lastPosition,direction);
            getMoves(lastPosition,oppositeDirection,returnedMoves);
        }

        forwardMoves.addAll(backwardMoves);
        forwardMoves.addAll(returnedMoves);

        return forwardMoves;
    }

    private String getMoves(String position, Direction direction,HashSet<String> moves) {
        String lastPosition = position;
        boolean movesLeft = true;
        while (movesLeft){
            movesLeft = false;
            HashSet<String>nextMoves = getNextDiagonals(lastPosition,this.getColor());
            for(String nextMove: nextMoves){
                if(computeDirection(lastPosition,nextMove) == direction){
                    ChessPiece possiblePiece = this.board.getPiece(nextMove);
                    if(possiblePiece != null){
                        moves.add(nextMove);
                        return  lastPosition;
                    }
                    else{
                        moves.add(nextMove);
                        lastPosition = nextMove;
                        movesLeft = true;

                    }
                }
            }
        }

        return lastPosition;
    }

    private Direction computeDirection(String position, String nextPosition) {
        char startingColumn = position.charAt(0);
        int startingRow = Character.digit(position.charAt(1),10);
        char endColumn = nextPosition.charAt(0);
        int endRow = Character.digit(nextPosition.charAt(1),10);

        if(startingColumn > endColumn && startingRow > endRow){
            return Direction.DOWNLEFT;
        }
        else if(startingColumn > endColumn && startingRow < endRow){
            return Direction.UPLEFT;
        }
        else if(startingColumn < endColumn && startingRow > endRow){
            return Direction.DOWNRIGHT;
        }
        else if(startingColumn < endColumn && startingRow < endRow) {
            return Direction.UPRIGHT;
        }
        else {
            return Direction.INVALID;
        }

    }

    private Direction computeOppositeDirection(String lastPosition, Direction direction) {
        int row = Character.digit(lastPosition.charAt(1),10);
        if(direction == Direction.DOWNLEFT){
            if(row < 2){
                return Direction.UPLEFT;
            }
            else{
                return Direction.DOWNRIGHT;
            }
        }
        else if(direction == Direction.UPLEFT){
            if(row <= 2){
                return Direction.DOWNLEFT;
            }
            else{
                return Direction.UPRIGHT;
            }
        }
        else if(direction == Direction.DOWNRIGHT){
            if(row > 5){
                return Direction.UPRIGHT;
            }
            else {
                return Direction.DOWNLEFT;
            }
        }
        else{
            if(row < 6){
                return Direction.UPLEFT;
            }
            else{
                return Direction.DOWNRIGHT;
            }
        }
    }


    public String toString(){
        if (getColor() == Color.White) { return "\u2657"; }
        else return "\u265D";
    }
}
