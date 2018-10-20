import java.util.ArrayList;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

    public void initialize() {
        // This method initializes the board to the standard chess opening state with indexing as shown in the figure.
        // This method should use the constructors of the appropriate pieces, and call placePiece below to place the
        // newly constructed pieces in the right position.
        // a = 0, b = 1, ..., h = 7
        placePiece( new Rook(this, ChessPiece.Color.White), "a1");
        placePiece( new Knight(this, ChessPiece.Color.White), "b1");
        placePiece( new Bishop(this, ChessPiece.Color.White), "c1");
        placePiece( new Queen(this, ChessPiece.Color.White), "d1");
        placePiece( new King(this, ChessPiece.Color.White), "e1");
        placePiece( new Bishop(this, ChessPiece.Color.White), "f1");
        placePiece( new Knight(this, ChessPiece.Color.White), "g1");
        placePiece( new Rook(this, ChessPiece.Color.White), "h1");
        for(char i = 'a'; i < 'i'; i++){
            placePiece( new Pawn(this, ChessPiece.Color.White), Character.toString(i) + Integer.toString(2));
            placePiece( new Pawn(this, ChessPiece.Color.Black), Character.toString(i) + Integer.toString(7));
        }
        placePiece( new Rook(this, ChessPiece.Color.Black), "a8");
        placePiece( new Knight(this, ChessPiece.Color.Black), "b8");
        placePiece( new Bishop(this, ChessPiece.Color.Black), "c8");
        placePiece( new Queen(this, ChessPiece.Color.Black), "d8");
        placePiece( new King(this, ChessPiece.Color.Black), "e8");
        placePiece( new Bishop(this, ChessPiece.Color.Black), "f8");
        placePiece( new Knight(this, ChessPiece.Color.Black), "g8");
        placePiece( new Rook(this, ChessPiece.Color.Black), "h8");

    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        // This method returns the chess piece at a given position. The position is represented as a two-character
        // string (e.g., e8) as described above. The first letter is in lowercase (a..h) and the second letter is a
        // digit (1..8). If the position is illegal because the string contains illegal characters or represents a
        // position outside the board, the exception is thrown.
        checkVaildPosition(position);
        return board[getRow(position)][getCol(position)];
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
                piece.setPosition(newPosition);    //check out setPosition
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

    public void move(String fromPosition, String toPosition) throws IllegalMoveException, IllegalPositionException {
        // This method checks if moving the piece from the fromPosition to toPosition is a legal move. If the move is legal,
        // it executes the move changing the value of the board as needed. Otherwise, the stated exception is thrown.
        checkVaildPosition(fromPosition);
        checkVaildPosition(toPosition);
        //check if piece is null
        ChessPiece piece = getPiece(fromPosition);
        if (piece == null) return;

        ChessPiece capturedPiece = getPiece(toPosition);
        if (capturedPiece == null || capturedPiece.getColor() != piece.getColor()) {
            ArrayList<String> legalMoves = piece.legalMoves();
            if (legalMoves.contains(toPosition)) {
                board[getRow(toPosition)][getCol(toPosition)] = piece;
                board[getRow(fromPosition)][getCol(fromPosition)] = null;
                piece.setPosition(toPosition);
            }
        }
    }

    public String toString() {
        String s = "";
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                s += board[row][col] + " | ";
            }
            s += '\n';
        }
        return s;
        // call ChessPiece toString(), just for debugging
    }


    private int getRow(String position) {
        return position.charAt(0) - 'a';
    }

    private int getCol(String position){
        return Character.getNumericValue(position.charAt(1)) - 1;
    }

    //Checks that position is a location on the board
    private void checkVaildPosition(String position) throws IllegalPositionException{
        if(position.charAt(0) < 'a' || position.charAt(0) > 'h' ||
                position.charAt(1) < '1' || position.charAt(1) > '8')
            throw new IllegalPositionException();
    }


    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);
        try{
            //board.move("c2", "c4");
        }catch(IllegalMoveException e){
            System.out.println(e.getStackTrace());
        }catch(IllegalPositionException e){
            System.out.println(e.getStackTrace());
        }
        //System.out.println(board);
    }

}
