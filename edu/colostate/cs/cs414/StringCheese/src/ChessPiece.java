package edu.colostate.cs.cs414.StringCheese.src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ChessPiece {

    public abstract HashSet<String> getValidMoves();

    public enum Color {White, Black};
    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; //the index of the vertical coloumn 0..7
    protected Color color; // the color of the piece
    protected String position;

    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
    }

    public Color getColor(){ return color; }

    public String getPosition() { return position; }

    public void setPosition(String position) throws IllegalPositionException {

        if(position.charAt(0) < 'a' || position.charAt(0) > 'h' ||
           position.charAt(1) < '1' || position.charAt(1) > '8')
            throw new IllegalPositionException("");

        row = position.charAt(0) - 'a';
        column = Character.getNumericValue(position.charAt(1)) - 1;
        this.position = position;
    }

    public HashSet<String> getNextForward(String position) throws IllegalPositionException {
        int index;
        String newPos;
        ArrayList<String> innerRing = board.getInnerRing();
        ArrayList<String> outerRing = board.getOuterRing();
        if(innerRing.contains(position)){
            index = innerRing.indexOf(position);
            //circular array, so next of .size() is index 0
            if(index+1 > innerRing.size()){
                newPos = innerRing.get(0);
            }else {
                newPos = innerRing.get(index + 1);
            }
        }else if(outerRing.contains(position)){
            index = outerRing.indexOf(position);
            if(index+1 > outerRing.size()){
                newPos = outerRing.get(0);
            }else {
                newPos = outerRing.get(index + 1);
            }
        }else {
            throw new IllegalPositionException("Position given not valid");
        }
        HashSet<String> moves = new HashSet<>();
        if(board.getPiece(newPos) != null && board.getPiece(newPos).color == board.getPiece(position).color){
            return moves;
        }else{
            moves.add(newPos);
            return moves;
        }
    }

    public HashSet<String> getPrevBackward(String position) throws IllegalPositionException {
        int index;
        String newPos;
        ArrayList<String> innerRing = board.getInnerRing();
        ArrayList<String> outerRing = board.getOuterRing();
        if(innerRing.contains(position)){
            index = innerRing.indexOf(position);
            //circular array, so prev of index(0) is .size()-1
            if(index-1 < 0){
                newPos = innerRing.get(innerRing.size()-1);
            }else {
                newPos = innerRing.get(index - 1);
            }
        }else if(outerRing.contains(position)){
            index = outerRing.indexOf(position);
            if(index-1 < 0){
                newPos = outerRing.get(innerRing.size()-1);
            }else {
                newPos = outerRing.get(index - 1);
            }
        }else {
            throw new IllegalPositionException("Position given not valid");
        }
        HashSet<String> moves = new HashSet<>();
        if(board.getPiece(newPos) != null && board.getPiece(newPos).color == board.getPiece(position).color){
            return new HashSet<String>();
        }else{
            return new HashSet<String>(Arrays.asList(newPos));
        }
    }

    public HashSet<String> getNextDiagonals(String position) {
        HashSet<String> legalMoves = new HashSet<>();
        char letter = position.charAt(0);
        int row = getRow(position);
        //if ( row <=2 and col > b ) move left i.e. decrease col by 1 and row can be 1 or 2. with exception if c2 then also b3
        if(row <=1 && letter > 'b'){
            letter -= 1;
            if(row == 1) legalMoves.add(Character.toString(letter).concat(Integer.toString(0)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(1)));
        }
        //if ( col <=b and row < 5 ) move up i.e. increase row by 1 and col can be a or b. with exception if b5 then also c6
        if(letter <= 'b' && row < 5){
            //don't need?
            if(letter == 'a') legalMoves.add(Character.toString('b').concat(Integer.toString(row+1)));
            else legalMoves.add(Character.toString('a').concat(Integer.toString(row+1)));
        }
        //if ( row >=5 and col < f ) move right i.e. increase col by 1 and row can be 6 or 7. with exception if e6 then also f5
        if(row >=5 && letter < 'f'){
            letter += 1;
            if(row == 5) legalMoves.add(Character.toString(letter).concat(Integer.toString(6)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(5)));
        }
        //if ( col >=f and row > 1 ) move down i.e. decrease row by 1 and col can be f or g. with exception f3 can move e2
        if(row >1 && letter >= 'f'){
            if(letter == 'f')  legalMoves.add(Character.toString('g').concat(Integer.toString(row-1)));
            else legalMoves.add(Character.toString('f').concat(Integer.toString(row-1)));
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
        return legalMoves;
    }

    public HashSet<String> getPrevDiagonals(String position) {
        HashSet<String> legalMoves = new HashSet<>();
        char letter = position.charAt(0);
        int row = getRow(position);
        //if ( row <=2 and col > b ) move left i.e. decrease col by 1 and row can be 1 or 2. with exception if c2 then also b3
        if(row <=1 && letter < 'f'){
            letter += 1;
            if(row == 1) legalMoves.add(Character.toString(letter).concat(Integer.toString(0)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(1)));
        }
        //if ( col <=b and row < 5 ) move up i.e. increase row by 1 and col can be a or b. with exception if b5 then also c6
        if(letter <= 'b' && row > 2 ){
            if(letter == 'a') legalMoves.add(Character.toString('b').concat(Integer.toString(row-1)));
            else legalMoves.add(Character.toString('a').concat(Integer.toString(row-1)));
        }
        //if ( row >=5 and col < f ) move right i.e. increase col by 1 and row can be 6 or 7. with exception if e6 then also f5
        if(row >=5 && letter > 'b'){
            letter -= 1;
            if(row == 5) legalMoves.add(Character.toString(letter).concat(Integer.toString(6)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(5)));
        }
        //if ( col >=f and row > 1 ) move down i.e. decrease row by 1 and col can be f or g. with exception f3 can move e2
        if(row <= 4 && letter >= 'f'){
            if(letter == 'f')  legalMoves.add(Character.toString('g').concat(Integer.toString(row+1)));
            else legalMoves.add(Character.toString('f').concat(Integer.toString(row+1)));
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
        return legalMoves;

        //return new HashSet<String>();
    }

    //returns adjacent tiles on opposite ring
    public HashSet<String> getSideways(String position) throws IllegalPositionException {
        HashSet<String> legalMoves = new HashSet<>();
        ArrayList<String> innerRing = board.getInnerRing();
        ArrayList<String> outerRing = board.getOuterRing();
        int index, offset = 0;
        //if on inner Ring finding outerRing
        if(innerRing.contains(position)){
            index = innerRing.indexOf(position);
            if(index < 5) offset = 1;
            else if(index <= 7 ) offset = 3;
            else if(index <= 11 ) offset = 5;
            else offset = 7;
            String oldPosition = outerRing.get(index+offset);
            if(board.getPiece(position) != null && board.getPiece(position).color == board.getPiece(oldPosition).color){
                return legalMoves;
            }else{
                legalMoves.add(outerRing.get(index+offset));
            }
            return legalMoves;
        }
        //if index is inbetween
        else if(outerRing.contains(position)) {
            index=outerRing.indexOf(position);
            if(index >= 1 && index <= 5) offset = 1;
            else if(index > 6 && index <= 11) offset = 3;
            else if(index > 13 && index <= 17) offset = 5;
                //if on corner there is no sideways
            else if(isCorner(index)){
                return legalMoves;
            }else{
                offset = 7;
            }
            String oldPosition = outerRing.get(index-offset);
            if(board.getPiece(position) != null && board.getPiece(position).color == board.getPiece(oldPosition).color){
                return legalMoves;
            }else{
                legalMoves.add(innerRing.get(index+offset));
            }
            return legalMoves;
        }
        else{
            throw new IllegalPositionException("Position " + position + " not valid");
        }
    }

    private boolean isCorner(int index){ return (index == 0 || index == 6 || index == 12 || index == 18); }
    private int getRow(String position) {
        return position.charAt(0) - 'a';
    }
    private int getCol(String position){
        return Character.getNumericValue(position.charAt(1)) - 1;
    }




    abstract public HashSet<String> legalMoves();
    abstract public String toString();

}




