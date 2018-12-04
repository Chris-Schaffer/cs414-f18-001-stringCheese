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

        //placePiece( new Pawn(this, ChessPiece.Color.Black),"e1");
        //placePiece( new Pawn(this, ChessPiece.Color.White),"c7");
    }

    //This method is used to select a piece on the board and returns valid moves for that piece
    // The position is represented as a two-character string (e.g., e8). The first letter is in lowercase (a..h) and the second letter is a
    // digit (1..8).
    //FIXME CURRENTLY HAS BUG WHEN SWITCHING PIECES MULTIPLE TIMES THE KING NO LONGER SHOWS ALL POSSIBLE MOVES
    public HashSet<String> selectPiece(String position){
        HashSet<String> moves = new HashSet<>();
        ChessPiece piece = getPiece(position);
        if(piece == null){ return moves; }
        if(piece.getColor() == turn){
            moves = piece.legalMoves();
            selectedPiece = piece;
            //if selected piece instanceof King you need to check that moving the king
            //does not put the king into check
            if(kingInCheck() || selectedPiece instanceof King) {
                moves = removeKingInCheck(moves, selectedPiece);
            }
            selectedPieceMoves = moves;
        }
        //if whites turn then call legalMoves() on all black pieces and see if they contain the square that white king is on
        return moves;
    }

    // This method tries to place the given piece at a given position, and returns true if successful, and false if
    // the position was illegal.
    // If successful, this method should call an appropriate method in the ChessPiece class (i.e., setPosition) to
    // set the piece's position.
    // This method is used for initialization as well as debugging a specific board setup
    public boolean placePiece(ChessPiece piece, String newPosition) {
        if(newPosition.length() != 2) return false;
        int row = getRow(newPosition);
        int col = getCol(newPosition);
        //pseudo move piece
        if(piece == null){
            board[row][col] = null;
        }
        //pseudo move piece
        else{
            piece.setPosition(newPosition);
            board[row][col] = piece;
        }
        return true;
    }

    //checks that moving a piece does not put your own king into check
    //if your king is in check then will only return moves that remove it from check
    private HashSet<String> removeKingInCheck(HashSet<String> moves, ChessPiece piece) {
        HashSet<String> newSet = new HashSet<>();
        String currentPos = piece.getPosition();
        ChessPiece removedPiece;

        if(piece instanceof King){
            for(String newPos: moves) {
                removedPiece = getPiece(newPos);
                placePiece(piece,newPos);
                placePiece(null,currentPos);
                if( !(((King)piece).isInCheck()) ) { newSet.add(newPos); }
                //move pieces back to original positions
                placePiece(removedPiece,newPos);
                placePiece(piece,currentPos);
            }
            return newSet;
        }

        String kingPos;
        if(piece.getColor() == selectedPiece.getColor()) {
            kingPos = getSameColorKingPosition();
        }else{
            kingPos = getOppositeColorKingPosition();
        }
        //loop through legal moves and remove any that put the king into check
        for(String newPos: moves) {
            //pseudo place piece then check if opposite color pieces can attack it
            removedPiece = getPiece(newPos);
            placePiece(piece,newPos);
            placePiece(null,currentPos);
            if (isMoveLegal(kingPos, piece.getColor())) {
                newSet.add(newPos);
            }
            //move pieces back to original positions
            placePiece(removedPiece,newPos);
            placePiece(piece,currentPos);
        }
        return newSet;
    }

    //returns true if your king is in check
    private boolean kingInCheck() {
        String kingPos = getSameColorKingPosition();
        ChessPiece king = getPiece(kingPos);
        return  ( (King) king).isInCheck();
    }

    //checks that moving selectedPiece to newPosition did not put your own king into check
    private boolean isMoveLegal(String kingPosition, ChessPiece.Color myColor){
        for(int row = 0; row < 7; ++row) {
            for (int col = 0; col < 7; ++col) {
                if (board[row][col] != null && board[row][col].getColor() != myColor) {
                    if (board[row][col].legalMoves().contains(kingPosition)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //find king position of same color as selectedPiece
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
    //find king position of opposite color as selectedPiece
    private String getOppositeColorKingPosition(){
        String kingPos = "";
        for(int row = 0; row < 7; ++row){
            for(int col = 0; col < 7; ++col){
                if( (board[row][col] instanceof King) && (board[row][col].getColor()!=selectedPiece.getColor()) ){
                    kingPos = board[row][col].getPosition();
                    break;
                }
            }
        }
        return kingPos;
    }

    //returns Promotion, Winner, or empty String indicating nothing special happened
    public String move(String fromPosition, String toPosition) {
        if(selectedPiece.getPosition().equals(fromPosition) && selectedPieceMoves.contains(toPosition)) {
            selectedPiece.setPosition(toPosition);
            board[getRow(toPosition)][getCol(toPosition)] = selectedPiece;
            board[getRow(fromPosition)][getCol(fromPosition)] = null;
            changeTurn();
            return getMessage();
        }
        return "";
    }

    //checks if there should be a special message returned after a move is made
    private String getMessage() {
        //"Checkmate" means you won by checkmate
        if(isCheckmate()){return "Checkmate";}
        //if in a location that white can get a promotion
        if (whitePromotion.contains(selectedPiece.getPosition()) && selectedPiece.getColor() == ChessPiece.Color.White) {
            if (selectedPiece instanceof Pawn) {
                if (selectedPiece.getPosition().equals("e6") || selectedPiece.getPosition().equals("e7")) {
                    return "Promotion";
                }
            } else if (selectedPiece instanceof King) {
                if (selectedPiece.getPosition().equals("d6")) {
                    return "Winner";
                }
            }
            //if in a location that black can get a promotion
        } else if (blackPromotion.contains(selectedPiece.getPosition()) && selectedPiece.getColor() == ChessPiece.Color.Black) {
            if (selectedPiece instanceof Pawn) {
                if (selectedPiece.getPosition().equals("c1") || selectedPiece.getPosition().equals("c2")) {
                     return "Promotion";
                }
            } else if (selectedPiece instanceof King) {
                if (selectedPiece.getPosition().equals("d2")) {
                    return "Winner";
                }
            }
        }
        return "";
    }

    private boolean isCheckmate() {
        //get opposite color king
        King king = (King) getPiece(getOppositeColorKingPosition());
        return ( king.isInCheck() && hasNoValidMoves() );
    }
    // returns false if any piece of the opposite color has at least one legal move
    // returns true otherwise
    private boolean hasNoValidMoves(){
        for(int row = 0; row < 7; ++row) {
            for (int col = 0; col < 7; ++col) {
                if (board[row][col] != null && board[row][col].getColor() != selectedPiece.getColor()) {
                    //sees if moving any piece of opposite color can get them out of check
                    if (removeKingInCheck(board[row][col].legalMoves(), board[row][col]).size()>0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void changeTurn() {
        if (turn == ChessPiece.Color.White) {
            turn = ChessPiece.Color.Black;
        } else {
            turn = ChessPiece.Color.White;
        }
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

    public ChessPiece getPiece(String position) {
        checkValidPosition(position);
        return board[getRow(position)][getCol(position)];
    }

    public String getPieceType(String position) {
        ChessPiece piece = getPiece(position);
        if(piece == null){
            return "blank";
        }
        else if(piece instanceof King){
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