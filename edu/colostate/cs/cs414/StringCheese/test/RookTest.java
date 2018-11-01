package edu.colostate.cs.cs414.StringCheese.test;

class RookTest extends ChessSuite {
    
    ChessBoard board;
    @BeforeEach
    void setUp() {
        board = new ChessBoard();
    }

    @org.junit.jupiter.api.Test
    void legalMoves() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("a4");

        String expected="e7";
        HashSet<String> move= new HashSet<>();
        move=rook.legalMoves();

        Iterator<String> it = move.iterator();
        assertTrue(move.contains(expected));

    }
    @org.junit.jupiter.api.Test
    void position() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("a1");

        String expected="a1";
        assertEquals(rook.getPosition(),expected);

    }
    @org.junit.jupiter.api.Test
    void checkCorner() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("a1");

        HashSet<String> move= new HashSet<>();
        move=rook.legalMoves();
        assertTrue(move.contains("a7"));

    }
    @org.junit.jupiter.api.Test
    void innerLoop() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("b2");

        HashSet<String> move= new HashSet<>();
        move=rook.legalMoves();
        assertTrue(move.contains("b6"));
        assertTrue(move.contains("c2"));


    }


}
