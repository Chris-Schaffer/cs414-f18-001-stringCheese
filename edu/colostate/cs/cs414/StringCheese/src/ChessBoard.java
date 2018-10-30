package edu.colostate.cs.cs414.StringCheese.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ChessBoard {
    private ChessPiece[][] board;
    public ArrayList<String> innerRing, outerRing;
    private ChessPiece selectedPiece;
    private HashSet<String> selectedPieceMoves;

    public ChessBoard() {
        board = new ChessPiece[7][7];
        outerRing = new ArrayList<>(Arrays.asList("a1", "a2", "a3", "a4", "a5", "a6", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "g6", "g5", "g4", "g3", "g2", "g1", "f1", "e1", "d1", "c1", "b1"));
        innerRing = new ArrayList<>(Arrays.asList("b2", "b3", "b4", "b5", "b6", "c6", "d6", "e6", "f6", "f5", "f4", "f3", "f2", "e2", "d2", "c2"));
        selectedPiece = null;
        selectedPieceMoves = new HashSet<>();
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
        checkValidPosition(position);
        return board[getRow(position)][getCol(position)];
    }
    //This method is used to select a piece on the board and returns valid moves for that piece
    // The position is represented as a two-character string (e.g., e8). The first letter is in lowercase (a..h) and the second letter is a
    // digit (1..8). If the position is illegal because the string contains illegal characters or represents a
    // position outside the board, the exception is caught and an empty HashSet is returned.
    public HashSet<String> selectPiece(String position){
        HashSet<String> moves = new HashSet<>();
        try {
            ChessPiece piece = getPiece(position);
            if(piece == null){
                return moves;
            }
            moves = piece.legalMoves();

            selectedPiece = piece;
            selectedPieceMoves = moves;
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
    private boolean placePiece(ChessPiece piece, String newPosition) {
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
        return board[0].length - Character.getNumericValue(position.charAt(1));
    }

    private int getCol(String position){
        return position.charAt(0) - 'a';

    }

    //Checks that position is a location on the board
    private void checkValidPosition(String position) throws IllegalPositionException{
        if(position.length() != 2) throw new IllegalPositionException("Position " + position + " invalid");
        if(position.charAt(0) < 'a' || position.charAt(0) > 'g' ||
                position.charAt(1) < '1' || position.charAt(1) > '7')
            throw new IllegalPositionException("Position " + position + " is invalid");
    }
    
    public void move(String fromPosition, String toPosition) throws IllegalPositionException {
        if(selectedPiece.getPosition().equals(fromPosition)){
            if(selectedPieceMoves.contains(toPosition)){
                ChessPiece piece = getPiece(fromPosition);
                piece.setPosition(toPosition);
                board[getRow(toPosition)][getCol(toPosition)] = piece;
                board[getRow(fromPosition)][getCol(fromPosition)] = null;
            }
        }
    }

    public ArrayList<String> getInnerRing() { return innerRing; }

    public ArrayList<String> getOuterRing() { return outerRing; }

    public String toString() {
        String s = "";
        int row = 0;
        int col = 0;
        for( row = 0; row < 7; row++){
            for(col = 0; col < 7; col++){
            	if(board[row][col] == null){
            	    if(row >= 2 && col >= 2 && row <= 4 && col <=4){
                        s += "X" + " | ";
                    }
                    else {
                        s += "\u25A1" + " | ";
                    }
            	}
				else {
					s += board[row][col] + " | ";
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

