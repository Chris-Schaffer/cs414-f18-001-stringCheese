package edu.colostate.cs.cs414.StringCheese.src;

import java.util.HashSet;

public class Pawn extends ChessPiece {

    private HashSet<String> legalMoves;

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves(){
        //MOVE NEEDS TO ALWAYS BE CLOCKWISE.
        //CAN MOVE EITHER STRAIGHT UP OR DIAGONALLY UP BUT NEVER SIDEWAYS OR BACKWARDS
        //CAN BE PROMOTED TO A BISHOP OR ROOK WHEN REACHING EITHER STARTING SQUARE OF THE OPPOSITE COLOR PAWNS
        //NEED CODE THAT CAN DETERMINE IF A POSITION IS CLOCKWISE RELATIVE TO IT TO USE FOR ALL PIECES.
        legalMoves.clear();
        adjacentSquares();
        removeInvalidSquares();
        return legalMoves;

        /*
       outer ring clockwise a1, a2, a3, a4, a5, a6, a7, b7, c7, d7, e7, f7, g7, g6, g5, g4, g3, g2, g1, f1, e1, d1, c1, b1, a1
       inner ring clockwise b2, b3, b4, b5, b6, c6, d6, e6, f6, f5, f4, f3, f2, e2, d2, c2, b2
        if ( row <=1 and col > b ) move left i.e. decrease col by 1 and row can be 1 or 2. with exception if c2 then also b3
        if ( col <=b and row < 5 ) move up i.e. increase row by 1 and col can be a or b. with exception if b5 then also c6
        if ( row >=5 and col < f ) move right i.e. increase col by 1 and row can be 6 or 7. with exception if e6 then also f5
        if ( col >=f and row > 1 ) move down i.e. decrease row by 1 and col can be f or g. with exception f3 can move e2
        exceptions to above rule on outer ring - b1 can move to a1, a6 can move a7, f7 can move g7, g2 can move g1
        exceptions to inner ring from above - c2 can move b3, b5 can move c6, e6 can move f5, f3 can move e2
        16 inner 24 outer rings 40 total rings
        */

    }

    private void removeInvalidSquares() {
        ChessPiece p;
        HashSet<String> invalids = new HashSet<>();
        for(String s: legalMoves){
            try {
                p = board.getPiece(s);
                if(p != null && p.getColor() == this.color){
                    invalids.add(s);
                }
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }
        }
        legalMoves.removeAll(invalids);
    }

    //adds all squares adjacent that the pawn could move to, from its current position
    //does not check whether or not another piece of the same color is on that square
    //FIXME also does not check if the movement of the piece will cause king to become in check
    private void adjacentSquares() {
        char letter = position.charAt(0);
        //if ( row <=2 and col > b ) move left i.e. decrease col by 1 and row can be 1 or 2. with exception if c2 then also b3
        if(row <=1 && letter > 'b'){
            letter -= 1;
            if(position.equals("g2")){
                legalMoves.add("g1");
                legalMoves.add("f1");
            }
            else {
                legalMoves.add(Character.toString(letter).concat(Integer.toString(0)));
                legalMoves.add(Character.toString(letter).concat(Integer.toString(1)));
            }
        }
        //if ( col <=b and row < 5 ) move up i.e. increase row by 1 and col can be a or b. with exception if b5 then also c6
        if(letter <= 'b' && row < 5){
            if(position.equals("b1")){
                legalMoves.add("a1");
                legalMoves.add("a2");
            }
            else {
                legalMoves.add(Character.toString('a').concat(Integer.toString(row+1)));
                legalMoves.add(Character.toString('b').concat(Integer.toString(row+1)));
            }
        }
        //if ( row >=5 and col < f ) move right i.e. increase col by 1 and row can be 6 or 7. with exception if e6 then also f5
        if(row >=5 && letter < 'f'){
            letter += 1;
            if(position.equals("a6")){
                legalMoves.add("a7");
                legalMoves.add("b7");
            }
            else {
                legalMoves.add(Character.toString(letter).concat(Integer.toString(6)));
                legalMoves.add(Character.toString(letter).concat(Integer.toString(7)));
            }
        }
        //if ( col >=f and row > 1 ) move down i.e. decrease row by 1 and col can be f or g. with exception f3 can move e2
        if(row >1 && letter >= 'f'){
            if(position.equals("f7")){
                legalMoves.add("g7");
                legalMoves.add("g6");
            }
            else {
                legalMoves.add(Character.toString('f').concat(Integer.toString(row-1)));
                legalMoves.add(Character.toString('g').concat(Integer.toString(row-1)));
            }
        }
        //special cases with 3 possible moves
        if(position.equals("c2")){
            legalMoves.add("b3");
        }
        if(position.equals("b5")){
            legalMoves.add("c6");
        }
        if(position.equals("e6")){
            legalMoves.add("f5");
        }
        if(position.equals("f3")){
            legalMoves.add("e2");
        }
    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2659"; }
        else return "\u265F";
    }
}
