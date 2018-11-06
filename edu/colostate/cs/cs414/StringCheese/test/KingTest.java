package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.ChessPiece;
import edu.colostate.cs.cs414.StringCheese.src.King;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest extends ChessSuite {

    ChessBoard board = new ChessBoard();
    ChessPiece king = new King(board, ChessPiece.Color.White);
    ChessPiece king2 = new King(board, ChessPiece.Color.White);
    ChessPiece king3 = new King(board, ChessPiece.Color.Black);


    @org.junit.jupiter.api.Test
    void legalMoves() {
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesB2() {
        board.placePiece(king, "b2");
        assertTrue(king.legalMoves().containsAll(Arrays.asList("a1","a2","a3","b1", "b3", "c1","c2")));
        assertTrue(king.legalMoves().size() == 7);
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesB6() {
        board.placePiece(king, "b6");
        assertTrue(king.legalMoves().containsAll(Arrays.asList("a5", "a6", "a7","b5","b7","c6","c7")));
        assertTrue(king.legalMoves().size() == 7);
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesF2() {
        board.placePiece(king, "f2");
        assertTrue(king.legalMoves().containsAll(Arrays.asList("e1","e2","f1", "f3", "g1","g2","g3")));
        assertTrue(king.legalMoves().size() == 7);
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesF6() {
        board.placePiece(king, "f6");
        assertTrue(king.legalMoves().containsAll(Arrays.asList("e6", "f7", "f5","f7","g5","g6","g7")));
        assertTrue(king.legalMoves().size() == 7);
    }

    @org.junit.jupiter.api.Test
    void testCannotMoveOntoSameColor() {
        board.placePiece(king, "f3");
        board.placePiece(king2, "f2");
        assertFalse(king.legalMoves().contains("f2"));
    }

    @org.junit.jupiter.api.Test
    void testCanCaptureOppositeColor() {
        board.placePiece(king, "f3");
        board.placePiece(king3, "f2");
        assertTrue(king.legalMoves().contains("f2"));
    }

    @org.junit.jupiter.api.Test
    public void test() {
        King king = new King(board, ChessPiece.Color.White);
        king.setPosition("a1");

        String expected = "a1";

        assertEquals(king.getPosition(), expected);
    }

    @org.junit.jupiter.api.Test
    public void testIllegalMove()  {
        King king = new King(board, ChessPiece.Color.White);
        king.setPosition("a1");

        HashSet<String> expected = new HashSet<>();
        expected.add("a2");
        expected.add("b1");
        expected.add("b2");
        HashSet<String> legalMoves = king.legalMoves();

        assertEquals(legalMoves, expected);

    }

}
