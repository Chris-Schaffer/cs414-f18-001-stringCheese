package edu.colostate.cs.cs414.StringCheese.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ChessPiece {

    public enum Color {White, Black};
    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; //the index of the vertical coloumn 0..7
    protected Color color; // the color of the piece
    protected String position;
    private ArrayList<String> innerRing = new ArrayList<>(Arrays.asList("a1", "a2", "a3", "a4", "a5", "a6", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "g6", "g5", "g4", "g3", "g2", "g1", "f1", "e1", "d1", "c1", "b1"));
    private ArrayList<String> outerRing = new ArrayList<>(Arrays.asList("b2", "b3", "b4", "b5", "b6", "c6", "d6", "e6", "f6", "f5", "f4", "f3", "f2", "e2", "d2", "c2"));






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

    abstract public HashSet<String> legalMoves();
    abstract public String toString();

    public HashSet<String> getNextStraightClockwise(String position) throws IllegalPositionException {
        int index;
        String newPos;
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
        int row = board.getRow(newPos);
        int col = board.getCol(newPos);
        int oldRow = board.getRow(position);
        int oldCol = board.getCol(position);
        HashSet<String> moves = new HashSet<>();
        if(board.getPiece(row,col) != null && board.getPiece(row,col).color == board.getPiece(oldRow,oldCol).color){
            return moves;
        }else{
            moves.add(newPos);
            return moves;
        }
    }

    public HashSet<String> getNextDiagonalClockwise(String position) {
        HashSet<String> legalMoves = new HashSet<>();
        char letter = position.charAt(0);
        int row = board.getRow(position);
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

    public HashSet<String> getBackwardStraight(String position) throws IllegalPositionException {
        int index;
        String newPos;
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
        int row = board.getRow(newPos);
        int col = board.getCol(newPos);
        int oldRow = board.getRow(position);
        int oldCol = board.getCol(position);
        HashSet<String> moves = new HashSet<>();
        if(board.getPiece(row,col) != null && board.getPiece(row,col).color == board.getPiece(oldRow,oldCol).color){
            return new HashSet<String>();
        }else{
            return new HashSet<String>(Arrays.asList(newPos));
        }
    }

    public HashSet<String> getBackwardDiagonals(String position) {
        HashSet<String> legalMoves = new HashSet<>();
        char letter = position.charAt(0);
        int row = board.getRow(position);
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
    public HashSet<String> getSideways(String position) {
        /*
        //if on innerRing corners add straight on outer ring
        {
            if (position.equals("b2") && (board[getRow("a2")][getCol("a2")] != null && board[getRow("a2")][getCol("a2")].color != board[oldRow][oldCol].color)) {
                moves.add("a2");
            }
            if (position.equals("b6") && (board[getRow("b7")][getCol("b7")] != null && board[getRow("b7")][getCol("b7")].color != board[oldRow][oldCol].color)) {
                moves.add("b7");
            }
            if (position.equals("f6") && (board[getRow("g6")][getCol("g6")] != null && board[getRow("g6")][getCol("g6")].color != board[oldRow][oldCol].color)) {
                moves.add("g6");
            }
            if (position.equals("f2") && (board[getRow("f1")][getCol("f1")] != null && board[getRow("f1")][getCol("f1")].color != board[oldRow][oldCol].color)) {
                moves.add("f1");
            }
        }


        //add straight on opposite ring
        if(position.equals("a2") && (board[getRow("b2")][getCol("b2")] != null && board[getRow("b2")][getCol("b2")].color != board[oldRow][oldCol].color)) {
            moves.add("b2");
        }
        if(position.equals("b7") && (board[getRow("b6")][getCol("b6")] != null && board[getRow("b6")][getCol("b6")].color != board[oldRow][oldCol].color)) {
            moves.add("b6");
        }
        if(position.equals("g6") && (board[getRow("f6")][getCol("f6")] != null && board[getRow("f6")][getCol("f6")].color != board[oldRow][oldCol].color)) {
            moves.add("f6");
        }
        if(position.equals("f1") && (board[getRow("f2")][getCol("f2")] != null && board[getRow("f2")][getCol("f2")].color != board[oldRow][oldCol].color)) {
            moves.add("f2");
        }
        */



        return new HashSet<String>();
    }
}






