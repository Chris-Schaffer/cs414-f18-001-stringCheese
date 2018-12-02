package edu.colostate.cs.cs414.StringCheese.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ChessBoard implements Serializable {
    private ChessPiece[][] board;
    public ArrayList<String> innerRing, outerRing;
    private ChessPiece selectedPiece;
    private HashSet<String> selectedPieceMoves;
    private ArrayList<String> promotion=new ArrayList<>(Arrays.asList("c1","c2","e6","e7","d6","d2"));
    private ChessPiece.Color turn = ChessPiece.Color.White; //who's turn is it next
    private String whitePlayer;
    //private boolean whiteTurn = true;

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

    public ChessPiece getPiece(String position) {
        checkValidPosition(position);
        return board[getRow(position)][getCol(position)];
    }
    //This method is used to select a piece on the board and returns valid moves for that piece
    // The position is represented as a two-character string (e.g., e8). The first letter is in lowercase (a..h) and the second letter is a
    // digit (1..8). If the position is illegal because the string contains illegal characters or represents a
    // position outside the board, the exception is caught and an empty HashSet is returned.
    public HashSet<String> selectPiece(String position){
        HashSet<String> moves = new HashSet<>();
            ChessPiece piece = getPiece(position);
            if(piece == null){ return moves; }
            moves = piece.legalMoves();
            selectedPiece = piece;
            selectedPieceMoves = moves;
            //if whites turn then call legalMoves() on all black pieces and see if they contain the square that white king is on
            //method(moves)
            return moves;
    }

    // This method tries to place the given piece at a given position, and returns true if successful, and false if
    // the position was illegal.
    // If successful, this method should call an appropriate method in the ChessPiece class (i.e., setPosition) to
    // set the piece's position.
    // This method is used for initialization as well as debugging a specific board setup
    public boolean placePiece(ChessPiece piece, String newPosition) {
        if(newPosition.length() != 2) return false;
            //piece is not currently on board i.e. initializing board
            if(piece.getPosition() == null){
                piece.setPosition(newPosition);    
                int row = getRow(newPosition);
                int col = getCol(newPosition);
                board[row][col] = piece;
                return true;
            }
            return false;
    }

    public String move(String fromPosition, String toPosition) {//throws IllegalPositionException {
        if(selectedPiece.getPosition().equals(fromPosition)){
	 if(promotion.contains(toPosition))
            {
                ChessPiece piece=getPiece(fromPosition);
                if(piece instanceof Pawn)
                {
                    if(!toPosition.equals("d6") && !toPosition.equals("d2"))
                    {
                        return "promotion";
                    }

                }
                else if(piece instanceof King)
                {
                    if(toPosition.equals("d6") || toPosition.equals("d2"))
                    {
                        return "Winner";
                    }
                    return new String();

                }
            }
		
		
            if(selectedPieceMoves.contains(toPosition)){
                ChessPiece piece = getPiece(fromPosition);
                piece.setPosition(toPosition);
                board[getRow(toPosition)][getCol(toPosition)] = piece;
                board[getRow(fromPosition)][getCol(fromPosition)] = null;
                if(turn == ChessPiece.Color.White){
                    turn = ChessPiece.Color.Black;
                }else{
                    turn= ChessPiece.Color.White;
                }
            }
		
        }
	    return new String();
    }

    public ArrayList<String> getInnerRing() { return innerRing; }

    public ArrayList<String> getOuterRing() { return outerRing; }

    private int getRow(String position) {
        return board[0].length - Character.getNumericValue(position.charAt(1));
    }

    private int getCol(String position){ return position.charAt(0) - 'a'; }
    //Checks that position is a location on the board
    private void checkValidPosition(String position) {
        if(position.length() != 2 ||
                position.charAt(0) < 'a' || position.charAt(0) > 'g' ||
                position.charAt(1) < '1' || position.charAt(1) > '7'){

            System.out.println("Position " + position + " is invalid in checkValidPosition()");
            System.exit(1);
        }
    }

    public String toString() {
        String s = "";
        int row = 0;
        int col = 0;
        for( row = 0; row < 7; row++){
            s += Integer.toString(7 - row) + " ";
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
        s += "  A   B   C   D   E   F   G  \n";
        return s;
        // call ChessPiece toString(), just for debugging
    }

    public void setWhitePlayer(String hostname){
        whitePlayer = hostname;
    }
    public String getWhitePlayer(){
        return whitePlayer;
    }

    public String getPieceType(String position) {
        ChessPiece piece = getPiece(position);
        if(piece == null){
            return "blank";
        }
        else if( piece instanceof King){
            if(piece.getColor().equals(ChessPiece.Color.Black)){
                return "blackking";
            }
                return "whiteking";
        }
        else if (piece instanceof Bishop){
            if(piece.getColor().equals(ChessPiece.Color.Black)){
                return "blackbishop";
            }
            return "whitebishop";
        }
        else if (piece instanceof Rook){
            if(piece.getColor().equals(ChessPiece.Color.Black)){
                return "blackrook";
            }
            return "whiterook";
        }
        else if(piece instanceof Pawn){
            if(piece.getColor().equals(ChessPiece.Color.Black)){
                return "blackpawn";
            }
            return "whitepawn";
        }
        else {
            return "error";
        }
    }
}

