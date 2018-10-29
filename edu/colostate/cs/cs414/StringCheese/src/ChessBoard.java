package edu.colostate.cs.cs414.StringCheese.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ChessBoard {
    private ChessPiece[][] board;
    private ArrayList<String> innerRing, outerRing;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        outerRing = new ArrayList<>(Arrays.asList("a1", "a2", "a3", "a4", "a5", "a6", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "g6", "g5", "g4", "g3", "g2", "g1", "f1", "e1", "d1", "c1", "b1"));
        innerRing = new ArrayList<>(Arrays.asList("b2", "b3", "b4", "b5", "b6", "c6", "d6", "e6", "f6", "f5", "f4", "f3", "f2", "e2", "d2", "c2"));

        //outer ring clockwise a1, a2, a3, a4, a5, a6, a7, b7, c7, d7, e7, f7, g7, g6, g5, g4, g3, g2, g1, f1, e1, d1, c1, b1, a1
        //inner ring clockwise b2, b3, b4, b5, b6, c6, d6, e6, f6, f5, f4, f3, f2, e2, d2, c2, b2
    }

    public void initialize() {
        // This method initializes the board to the standard chess opening state with indexing as shown in the figure.
        // This method should use the constructors of the appropriate pieces, and call placePiece below to place the
        // newly constructed pieces in the right position.
        // a = 0, b = 1, ..., h = 7
        placePiece( new Rook(this, ChessPiece.Color.White), "e1");
		placePiece( new Rook(this, ChessPiece.Color.White), "e2");
        placePiece( new Bishop(this, ChessPiece.Color.White), "d1");
        placePiece( new King(this, ChessPiece.Color.White), "d2");
        placePiece( new Pawn(this, ChessPiece.Color.White),"c1");
		placePiece( new Pawn(this, ChessPiece.Color.White),"c2");

		placePiece( new Rook(this, ChessPiece.Color.Black), "c7");
		placePiece( new Rook(this, ChessPiece.Color.Black), "c6");
		placePiece( new Bishop(this, ChessPiece.Color.Black), "d7");
		placePiece( new King(this, ChessPiece.Color.Black), "d6");
		placePiece( new Pawn(this, ChessPiece.Color.Black),"e6");
		placePiece( new Pawn(this, ChessPiece.Color.Black),"e7");

    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        // This method returns the chess piece at a given position. The position is represented as a two-character
        // string (e.g., e8) as described above. The first letter is in lowercase (a..h) and the second letter is a
        // digit (1..8). If the position is illegal because the string contains illegal characters or represents a
        // position outside the board, the exception is thrown.
        checkValidPosition(position);
        return board[getRow(position)][getCol(position)];
    }

    public HashSet<String> selectPiece(String position){
        HashSet<String> moves = new HashSet<>();

        try {
            ChessPiece piece = getPiece(position);
            if(piece == null){
                return moves;
            }
            moves = piece.getValidMoves();
            return moves;
        } catch (IllegalPositionException e) {
            return moves;
        }
    }

    // This method tries to place the given piece at a given position, and returns true if successful, and false if
    // the position was illegal.
    // If successful, this method should call an appropriate method in the ChessPiece class (i.e., setPosition) to
    // set the piece's position.
    // This method is used for initialization as well as debugging a specific board setup
    public boolean placePiece(ChessPiece piece, String newPosition) {
        if(newPosition.length() != 2) return false;
        try {
            //piece is not currently on board i.e. initializing board
            if(piece.getPosition() == null){
                piece.setPosition(newPosition);    
                int row = getRow(newPosition);
                int col = getCol(newPosition);
                board[row][col] = piece;
                return true;
            }
            else {
                return false;
            }
        } catch (IllegalPositionException e) {
            return false;
        }
    }
 
    private int getRow(String position) {
        return position.charAt(0) - 'a';
    }

    private int getCol(String position){
        return Character.getNumericValue(position.charAt(1)) - 1;
    }

    //Checks that position is a location on the board
    private void checkValidPosition(String position) throws IllegalPositionException{
        if(position.length() != 2) throw new IllegalPositionException("Position " + position + " invalid");
        if(position.charAt(0) < 'a' || position.charAt(0) > 'h' ||
                position.charAt(1) < '1' || position.charAt(1) > '8')
            throw new IllegalPositionException("");
    }
    
    public void move(String fromPosition, String toPosition) throws IllegalPositionException, IllegalMoveException {
        // This method checks if moving the piece from the fromPosition to toPosition is a legal move. If the move is legal,
        // it executes the move changing the value of the board as needed. Otherwise, the stated exception is thrown.
        checkValidPosition(fromPosition);
        checkValidPosition(toPosition);
        ChessPiece piece = getPiece(fromPosition);
        if (piece == null) throw new IllegalMoveException("No piece found at " + fromPosition);
        ChessPiece capturedPiece = getPiece(toPosition);
        if (capturedPiece == null || capturedPiece.getColor() != piece.getColor()) {
            HashSet<String> legalMoves = piece.legalMoves();
            if (legalMoves.contains(toPosition)) {
                board[getRow(toPosition)][getCol(toPosition)] = piece;
                board[getRow(fromPosition)][getCol(fromPosition)] = null;
                piece.setPosition(toPosition);
            }
        }else{
            throw new IllegalMoveException("Cannot move to position " + toPosition);
        }
    }
	
	 public HashSet<String> getNextForward(String position) throws IllegalPositionException {
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
        int row = getRow(newPos);
        int col = getCol(newPos);
        int oldRow = getRow(position);
        int oldCol = getCol(position);
        HashSet<String> moves = new HashSet<>();
        if(board[row][col] != null && board[row][col].color == board[oldRow][oldCol].color){
            return moves;
        }else{
            moves.add(newPos);
            return moves;
        }
    }

    public HashSet<String> getPrevBackward(String position) throws IllegalPositionException {
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
        int row = getRow(newPos);
        int col = getCol(newPos);
        int oldRow = getRow(position);
        int oldCol = getCol(position);
        HashSet<String> moves = new HashSet<>();
        if(board[row][col] != null && board[row][col].color == board[oldRow][oldCol].color){
            return new HashSet<String>();
        }else{
            return new HashSet<String>(Arrays.asList(newPos));
        }
    }



    /*
         HashSet<String> move = new HashSet<>();
        int row = getRow(position);
        int col = getCol(position);

        if (row - 1 >= 0 && col - 1 >= 0) {
            move.add(board[row - 1][col - 1].getPosition());
        }
        if (row + 1 <= board.length - 1 && col - 1 >= 0) {
            move.add(board[row + 1][col - 1].getPosition());
        }
        if (row + 1 <= board.length - 1 && col + 1 <= board.length - 1) {
            move.add(board[row + 1][col + 1].getPosition());
        }
        if (row - 1 >= 0 && col + 1 <= board.length - 1) {
            move.add(board[row - 1][col + 1].getPosition());
        }
        return move;

     */

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
        char letter = position.charAt(0);
        int row = getRow(position);
        int col = getCol(position);
        int index, offset = 0;
        int oldCol, oldRow;
        //if not on corner then add opposite number or corner
        //if 2 <= row < 6 || 'c' <= col < 'f'

        //if on inner Ring finding outerRing
        if(innerRing.contains(position)){
            index = innerRing.indexOf(position);
            if(index < 5) offset = 1;
            else if(index <= 7 ) offset = 3;
            else if(index <= 11 ) offset = 5;
            else offset = 7;
            oldRow = getRow(outerRing.get(index+offset));
            oldCol = getCol(outerRing.get(index+offset));
            if(board[row][col] != null && board[row][col].color == board[oldRow][oldCol].color){
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
            else if(index == 0 || index == 6 || index == 12 || index == 18){

            }else{
                offset = 7;
            }
            oldRow = getRow(innerRing.get(index-offset));
            oldCol = getCol(innerRing.get(index-offset));
            if(board[row][col] != null && board[row][col].color == board[oldRow][oldCol].color){
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



	
	
	

    public String toString() {
        String s = "";
        int row = 0;
        int col = 0;
        for( row = 0; row < 7; row++){
            if(row == 0){
                s+= 7 + " ";
            }
            else{
                s+= (7-row) + " ";
            }
            for(col = 0; col < 7; col++){
            	if(board[col][row] == null){
            	    if(row >= 2 && col >= 2 && row <= 4 && col <=4){
                        s += "X" + " | ";
                    }
                    else {
                        s += "\u25A1" + " | ";
                    }
            	}
				else {
					s += board[col][row] + " | ";
				}
            	}
            s += '\n';
        }
        return s;
        // call ChessPiece toString(), just for debugging
    }

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);
        //board.move("c2", "c4");
        //System.out.println(board);
    }

}

