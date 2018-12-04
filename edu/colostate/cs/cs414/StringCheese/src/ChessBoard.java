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
    private ArrayList<String> whitePromotion, blackPromotion;
    private ChessPiece.Color turn; //who's turn is it next
    private String whitePlayer;
    private static final long serialVersionUID = 919633265167419667L;
    //private boolean whiteTurn = true;

    public ChessBoard() {
        board = new ChessPiece[7][7];
        outerRing = new ArrayList<>(Arrays.asList("a1", "a2", "a3", "a4", "a5", "a6", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "g6", "g5", "g4", "g3", "g2", "g1", "f1", "e1", "d1", "c1", "b1"));
        innerRing = new ArrayList<>(Arrays.asList("b2", "b3", "b4", "b5", "b6", "c6", "d6", "e6", "f6", "f5", "f4", "f3", "f2", "e2", "d2", "c2"));
        selectedPiece = null;
        selectedPieceMoves = new HashSet<>();
        turn = ChessPiece.Color.White;
        whitePromotion=new ArrayList<>(Arrays.asList("e6","e7","d6"));
        blackPromotion=new ArrayList<>(Arrays.asList("c1","c2","d2"));
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
    //FIXME CURRENTLY HAS BUG WHEN SWITCHING PIECES MULTIPLE TIMES THE KING NO LONGER SHOWS ALL POSSIBLE MOVES
    public HashSet<String> selectPiece(String position){
        HashSet<String> moves = new HashSet<>();
        ChessPiece piece = getPiece(position);
        if(piece == null){ return moves; }
        if(piece.getColor() == turn){
            moves = piece.legalMoves();
            selectedPiece = piece;
           // moves = removeKingUnderAttack(moves);
            selectedPieceMoves = moves;
        }
        //if whites turn then call legalMoves() on all black pieces and see if they contain the square that white king is on
        return moves;
    }
    //checks that moving a piece does not put your own king into check
    //if your king is in check then will only return moves that remove it from check
    private HashSet<String> removeKingUnderAttack(HashSet<String> moves) {
        //king class does checks internally
        if(selectedPiece instanceof King){
            return moves;
        }
        HashSet<String> newSet = new HashSet<>();
        String currentPos = selectedPiece.getPosition();
        String kingPos = getSameColorKingPosition();
        ChessPiece king = getPiece(kingPos);
        ChessPiece removedPiece;
        //if king is not under attack don't bother checking if a move removes it from check
        if(!((King) king).isUnderAttack()){return moves;}
        //loop through legal moves and remove any that put the king into check
        for(String newPos: moves) {
            //fixme NEED TO CHANGE FROM SETPOSITION TO PLACEPIECE(). FIRST NEED TO SAVE the piece at the new Location
            //fixme THEN MOVE NEW PIECE TO THAT LOCATION, CHECK IF KING UNDER ATTACK THEN MOVE BACK
            //pseudo place piece then check if opposite color pieces can attack it
            removedPiece = getPiece(newPos);
            placePiece(selectedPiece,newPos);
            if (isMoveLegal(kingPos, newPos)) {
                newSet.add(newPos);
            }
            placePiece(removedPiece,newPos);
            placePiece(selectedPiece,currentPos);
        }
        //fixme NEED TO CHANGE FROM SETPOSITION TO PLACEPIECE(). FIRST NEED TO SAVE the piece at the new Location
        //fixme THEN MOVE NEW PIECE TO THAT LOCATION, CHECK IF KING UNDER ATTACK THEN MOVE BACK
        return newSet;
    }

    //checks that moving selectedPiece to newPosition does not put your own king into check
    private boolean isMoveLegal(String kingPosition, String newPosition){
        //fixme NEVER USING NEWPOSITION
        ChessPiece piece = selectedPiece;
        //String currentPos
        for(int row = 0; row < 7; ++row) {
            for (int col = 0; col < 7; ++col) {
                if (board[row][col] != null && board[row][col].getColor() != selectedPiece.getColor()) {
                    if (board[row][col].legalMoves().contains(kingPosition)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //find same color king position
    private String getSameColorKingPosition() {
        String kingPos = "";
        for(int row = 0; row < 7; ++row){
            for(int col = 0; col < 7; ++col){
                if( (board[row][col] instanceof King) && (board[row][col].getColor()==selectedPiece.getColor()) ){
                    kingPos = board[row][col].getPosition();
                    break;
                }
            }
        }
        return kingPos;
    }

    // This method tries to place the given piece at a given position, and returns true if successful, and false if
    // the position was illegal.
    // If successful, this method should call an appropriate method in the ChessPiece class (i.e., setPosition) to
    // set the piece's position.
    // This method is used for initialization as well as debugging a specific board setup
    //FIXME for king i can pseudo place piece with just setPosition() if i just remember the actual position
    public boolean placePiece(ChessPiece piece, String newPosition) {
        if(newPosition.length() != 2 || piece == null) return false;
        //piece is not currently on board i.e. initializing board
        if(piece.getPosition() == null) {
            piece.setPosition(newPosition);
            int row = getRow(newPosition);
            int col = getCol(newPosition);
            board[row][col] = piece;
            return true;
        }
        else{//pseudo move piece
            String oldPosition = piece.getPosition();
            piece.setPosition(newPosition);
            int row = getRow(newPosition);
            int col = getCol(newPosition);
            int oldRow = getRow(oldPosition);
            int oldCol = getCol(oldPosition);
            board[row][col] = piece;
        }
        return false;
    }

    //FIXME  needs isCheck() method
    //returns Promotion, Winner, or empty String indicating nothing special happened
    public String move(String fromPosition, String toPosition) {
        String message = "";
        if(selectedPiece.getPosition().equals(fromPosition) && selectedPieceMoves.contains(toPosition)) {
            ChessPiece piece = getPiece(fromPosition);
            ChessPiece.Color color = piece.getColor();
            piece.setPosition(toPosition);
            board[getRow(toPosition)][getCol(toPosition)] = piece;
            board[getRow(fromPosition)][getCol(fromPosition)] = null;
            if (turn == ChessPiece.Color.White) {
                turn = ChessPiece.Color.Black;
            } else {
                turn = ChessPiece.Color.White;
            }

            //if in a location that white can get a promotion
            if (whitePromotion.contains(toPosition) && color == ChessPiece.Color.White) {
                if (piece instanceof Pawn) {
                    if (toPosition.equals("e6") || toPosition.equals("e7")) {
                        message = "Promotion";
                    }
                } else if (piece instanceof King) {
                    if (toPosition.equals("d6")) {
                        message = "Winner";
                    }
                }
                //if in a location that black can get a promotion
            } else if (blackPromotion.contains(toPosition) && color == ChessPiece.Color.Black) {
                if (piece instanceof Pawn) {
                    if (toPosition.equals("c1") || toPosition.equals("c2")) {
                        message = "Promotion";
                    }
                } else if (piece instanceof King) {
                    if (toPosition.equals("d2")) {
                        message = "Winner";
                    }
                }
            }
        }
        return message;
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