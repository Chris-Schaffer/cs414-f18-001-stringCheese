package edu.colostate.cs.cs414.StringCheese.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ChessPiece {

    public enum Color {White, Black};
    protected ChessBoard board; // the board it belongs to, default null
    //private int row; // the index of the horizontal rows 0..7
    //private int column; //the index of the vertical coloumn 0..7
    private Color color; // the color of the piece
    private String position;    //NOTE: purposely not initialized i.e. left null

    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
    }

    public Color getColor(){ return color; }

    public String getPosition() { return position; }

    public void setPosition(String position) { //throws IllegalPositionException {

        if(position.charAt(0) < 'a' || position.charAt(0) > 'h' ||
           position.charAt(1) < '1' || position.charAt(1) > '8' ||
                position.length() != 2){
            System.out.println("Position " + position + " is not a valid position in setPosition()");
            System.exit(1);
            //throw new IllegalPositionException("");
        }
        //row = position.charAt(0) - 'a';
        //column = Character.getNumericValue(position.charAt(1)) - 1;
        this.position = position;
    }
    //returns zero if previous backward has piece of same color
    //returns set of size one if not
     public HashSet<String> getNextForward(String position,Color color) { //throws IllegalPositionException {
        int index;
        String newPos = "";
        HashSet<String> moves = new HashSet<>();
        ArrayList<String> innerRing = board.getInnerRing();
        ArrayList<String> outerRing = board.getOuterRing();
        if(innerRing.contains(position)){
            index = innerRing.indexOf(position);
            //circular array, so next of .size() is index 0
            if(index+1 >= innerRing.size()){
                newPos = innerRing.get(0);
            }else {
                newPos = innerRing.get(index + 1);
            }
        }else if(outerRing.contains(position)){
            index = outerRing.indexOf(position);
            if(index+1 >= outerRing.size()){
                newPos = outerRing.get(0);
            }else {
                newPos = outerRing.get(index + 1);
            }
        }else {
            System.out.println("Position " + position + " not valid. getNextForward()");
            System.exit(1);
        }
        moves.add(newPos);
        return removePositionsWithSameColorPiece(moves, color);
    }
    //returns zero if previous backward has piece of same color
    //returns set of size one if not
    public HashSet<String> getPrevBackward(String position,Color color){ //throws IllegalPositionException {
        int index;
        String newPos="";
        HashSet<String> legalMoves = new HashSet<>();
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
                newPos = outerRing.get(outerRing.size()-1);
            }else {
                newPos = outerRing.get(index - 1);
            }
        }else {
            //throw new IllegalPositionException("Position given not valid");
            System.out.println("Position " + position + " is invalid in getPrevBackward()");
            System.exit(1);
        }
        legalMoves.add(newPos);
        return removePositionsWithSameColorPiece(legalMoves, color);
    }
    //can return between zero and two next diagnonal positions from given position
    //returns zero if next diagonal has piece of same color
    public HashSet<String> getNextDiagonals(String position,Color color) {
        HashSet<String> legalMoves = new HashSet<>();
        char letter = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));
        //move left i.e. decrease col by 1 and row can be 1 or 2
        if(row <=2 && letter > 'b'){
            letter -= 1;
            if(row == 1) legalMoves.add(Character.toString(letter).concat(Integer.toString(2)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(1)));
        }
        //move up i.e. increase row by 1 and col can be a or b
        else if(letter <= 'b' && row < 6){
            //don't need?
            if(letter == 'a') legalMoves.add(Character.toString('b').concat(Integer.toString(row+1)));
            else legalMoves.add(Character.toString('a').concat(Integer.toString(row+1)));
        }
        //move right i.e. increase col by 1 and row can be 6 or 7
        else if(row >=6 && letter < 'f'){
            letter += 1;
            if(row == 6) legalMoves.add(Character.toString(letter).concat(Integer.toString(7)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(6)));
        }
        //move down i.e. decrease row by 1 and col can be f or g
        else if(row >2 && letter >= 'f'){
            if(letter == 'f')  legalMoves.add(Character.toString('g').concat(Integer.toString(row-1)));
            else legalMoves.add(Character.toString('f').concat(Integer.toString(row-1)));
        }

        //special cases with 2 possible moves
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
        return removePositionsWithSameColorPiece(legalMoves, color);
    }
    //can return between zero and two previous diagnonal positions from given position
    //returns zero if previous diagonals have piece of same color
    public HashSet<String> getPrevDiagonals(String position, Color color) {
        HashSet<String> legalMoves = new HashSet<>();
        char letter = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));
        if(row <=2 && letter < 'f'){
            letter += 1;
            if(row == 2) legalMoves.add(Character.toString(letter).concat(Integer.toString(1)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(2)));
        }
        else if(letter <= 'b' && row > 2 ){
            if(letter == 'a') legalMoves.add(Character.toString('b').concat(Integer.toString(row-1)));
            else legalMoves.add(Character.toString('a').concat(Integer.toString(row-1)));
        }
        else if(row >5 && letter > 'b'){
            letter -= 1;
            if(row == 6) legalMoves.add(Character.toString(letter).concat(Integer.toString(7)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(6)));
        }
        else if(row <= 5 && letter >= 'f'){
            if(letter == 'f')  legalMoves.add(Character.toString('g').concat(Integer.toString(row+1)));
            else legalMoves.add(Character.toString('f').concat(Integer.toString(row+1)));
        }

        //special cases with 2 possible moves
        if(position.equals("e2")){
            legalMoves.add("f3");
        }
        if(position.equals("b3")){
            legalMoves.add("c2");
        }
        if(position.equals("c6")){
            legalMoves.add("b5");
        }
        if(position.equals("f5")){
            legalMoves.add("e6");
        }
        return removePositionsWithSameColorPiece(legalMoves, color);

    }

    //returns adjacent tiles on opposite ring
    public HashSet<String> getSideways(String position, Color color){
        HashSet<String> legalMoves = new HashSet<>();
        //outer corner squares have no sideways
        if(isOuterCorner(position)){ return legalMoves;}
        //special case where inner corner has two sideways positions
        else if(isInnerCorner(position)){
            return getSidewaysSpecialCase(position,color );
        }

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
            legalMoves.add(outerRing.get(index+offset));
            return removePositionsWithSameColorPiece(legalMoves,color);
        }
        //if index is inbetween
        else if(outerRing.contains(position)) {
            index=outerRing.indexOf(position);
            if(index >= 1 && index <= 5) offset = 1;
            else if(index > 6 && index <= 11) offset = 3;
            else if(index > 13 && index <= 17) offset = 5;
            else offset = 7;
            //If statement for corner case where piece calls getSideways() from B1
            if(index-offset == innerRing.size()){
                legalMoves.add(innerRing.get(0));
            }else {
                legalMoves.add(innerRing.get(index - offset));
            }
            return removePositionsWithSameColorPiece(legalMoves,color);
        }
        else{
            System.out.println("Position " + position + " is invalid in getSideways()");
            System.exit(1);
            return legalMoves;
        }
    }
    //getSideways helper method for case when position is on inner corner and has two sideways moves
    private HashSet<String> getSidewaysSpecialCase(String position, Color color) {
        HashSet<String> legalMoves = new HashSet<>();
        switch (position) {
            case "b2":
                legalMoves.addAll(Arrays.asList("b1", "a2"));
                break;
            case "b6":
                legalMoves.addAll(Arrays.asList("b7", "a6"));
                break;
            case "f6":
                legalMoves.addAll(Arrays.asList("f7", "g6"));
                break;
            //else it is "f2"
            default:
                legalMoves.addAll(Arrays.asList("f1", "g2"));
                break;
        }
        return removePositionsWithSameColorPiece(legalMoves, color);
    }
    //package private

    HashSet<String> removePositionsWithSameColorPiece(HashSet<String> legalMoves, Color origionalColor) {
        HashSet<String> newSet = new HashSet<>();

         for(String move: legalMoves) {
            if (board.getPiece(move) == null || board.getPiece(move).color != origionalColor) {
                newSet.add(move);
            }
        }

        return newSet;
    }
    //package private
    boolean isInnerCorner(String position){
        return position.equals("b2") || position.equals("b6")|| position.equals("f6")|| position.equals("f2");
    }
    private boolean isOuterCorner(String position){
        return position.equals("a1") || position.equals("a7")|| position.equals("g7")|| position.equals("g1");
    }
    private int getRow(String position) { return position.charAt(0) - 'a'; }
    private int getCol(String position){ return Character.getNumericValue(position.charAt(1)) - 1; }

    abstract public HashSet<String> legalMoves();
    abstract public String toString();

}




