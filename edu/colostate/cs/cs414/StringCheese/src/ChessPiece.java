package edu.colostate.cs.cs414.StringCheese.src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ChessPiece {

    public enum Color {White, Black};
    protected ChessBoard board; // the board it belongs to, default null
    //private int row; // the index of the horizontal rows 0..7
    //private int column; //the index of the vertical coloumn 0..7
    private Color color; // the color of the piece
    private String position;

    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
        position = "";
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
    public HashSet<String> getNextForward(String position) { //throws IllegalPositionException {
        int index;
        String newPos = "";
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
            //throw new IllegalPositionException("Position given not valid");
        }
        HashSet<String> moves = new HashSet<>();
        if(board.getPiece(newPos) != null && board.getPiece(newPos).color == board.getPiece(position).color){
            return moves;
        }else{
            moves.add(newPos);
            return moves;
        }
    }
    //returns zero if previous backward has piece of same color
    //returns set of size one if not
    public HashSet<String> getPrevBackward(String position){ //throws IllegalPositionException {
        int index;
        String newPos="";
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
        if(board.getPiece(newPos) != null && board.getPiece(newPos).color == board.getPiece(position).color){
            return new HashSet<>();
        }else{
            HashSet<String> legalMoves = new HashSet<>();
            legalMoves.add(newPos);
            return legalMoves;
        }
    }
    //can return between zero and two next diagnonal positions from given position
    //returns zero if next diagonal has piece of same color
    public HashSet<String> getNextDiagonals(String position) {
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
        else if(row >1 && letter >= 'f'){
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
    //can return between zero and two previous diagnonal positions from given position
    //returns zero if previous diagonals have piece of same color
    public HashSet<String> getPrevDiagonals(String position) {
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
        else if(row >=5 && letter > 'b'){
            letter -= 1;
            if(row == 5) legalMoves.add(Character.toString(letter).concat(Integer.toString(6)));
            else legalMoves.add(Character.toString(letter).concat(Integer.toString(5)));
        }
        else if(row <= 4 && letter >= 'f'){
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

    }

    //returns adjacent tiles on opposite ring
    public HashSet<String> getSideways(String position){ //throws IllegalPositionException {
        HashSet<String> legalMoves = new HashSet<>();
        //outer corner squares have no sideways
        if(isOuterCorner(position)){ return legalMoves;}
        //special case where inner corner has two sideways positions
        else if(isInnerCorner(position)){
            if(position.equals("b2")){
                legalMoves.addAll(Arrays.asList("b1","a2"));
            }
            else if(position.equals("b6")){
                legalMoves.addAll(Arrays.asList("b7","a6"));
            }
            else if(position.equals("f6")){
                legalMoves.addAll(Arrays.asList("f7","g6"));
            }
            //else it is "f2"
            else{
                legalMoves.addAll(Arrays.asList("f1","g2"));
            }
            return legalMoves;
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
            else offset = 7;
            String oldPosition = outerRing.get(index-offset);
            if(board.getPiece(position) != null && board.getPiece(position).color == board.getPiece(oldPosition).color){
                return legalMoves;
            }else{
                legalMoves.add(innerRing.get(index-offset));
            }
            return legalMoves;
        }
        else{
            System.out.println("Position " + position + " is invalid in getSideways()");
            System.exit(1);
            //throw new IllegalPositionException("Position " + position + " not valid");
            return legalMoves;
        }
    }

    private boolean isOuterCorner(String position){
        return position.equals("a1") || position.equals("a7")|| position.equals("g7")|| position.equals("g1");
    }
    private boolean isInnerCorner(String position){
        return position.equals("b2") || position.equals("b6")|| position.equals("f6")|| position.equals("f2");
    }
    private int getRow(String position) {
        return position.charAt(0) - 'a';
    }
    private int getCol(String position){
        return Character.getNumericValue(position.charAt(1)) - 1;
    }

    abstract public HashSet<String> legalMoves(); //throws IllegalPositionException;
    abstract public String toString();

}




