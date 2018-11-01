package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.ChessPiece;
import edu.colostate.cs.cs414.StringCheese.src.Pawn;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest extends ChessSuite {

    ChessBoard board = new ChessBoard();
    ChessPiece pawn = new Pawn(board, ChessPiece.Color.White);
    ChessPiece pawn2 = new Pawn(board, ChessPiece.Color.White);
    ChessPiece pawn3 = new Pawn(board, ChessPiece.Color.Black);


    @org.junit.jupiter.api.Test
    void legalMoves() {
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesC2() {
        board.placePiece(pawn, "c2");
        assertTrue(pawn.legalMoves().containsAll(Arrays.asList("b1", "b2", "b3")));
        assertTrue(pawn.legalMoves().size() == 3);
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesB5() {
        board.placePiece(pawn, "b5");
        assertTrue(pawn.legalMoves().containsAll(Arrays.asList("a6", "b6", "c6")));
        assertTrue(pawn.legalMoves().size() == 3);
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesE6() {
        board.placePiece(pawn, "e6");
        assertTrue(pawn.legalMoves().containsAll(Arrays.asList("f7", "f6", "f5")));
        assertTrue(pawn.legalMoves().size() == 3);
    }

    @org.junit.jupiter.api.Test
    void testSquaresWithThreeMovesF3() {
        board.placePiece(pawn, "f3");
        assertTrue(pawn.legalMoves().containsAll(Arrays.asList("e2", "f2", "g2")));
        assertTrue(pawn.legalMoves().size() == 3);
    }

    @org.junit.jupiter.api.Test
    void testCannotMoveOntoSameColor() {
        board.placePiece(pawn, "f3");
        board.placePiece(pawn2, "f2");
        assertFalse(pawn.legalMoves().contains("f2"));
    }

    @org.junit.jupiter.api.Test
    void testCanCaptureOppositeColor() {
        board.placePiece(pawn, "f3");
        board.placePiece(pawn3, "f2");
        assertTrue(pawn.legalMoves().contains("f2"));
    }

    @org.junit.jupiter.api.Test
    public void test() {
        Pawn pawn = new Pawn(board, ChessPiece.Color.White);
        pawn.setPosition("a1");

        String expected = "a1";

        assertEquals(pawn.getPosition(), expected);
    }

    @org.junit.jupiter.api.Test
    public void testIllegalMove()  {
        Pawn pawn = new Pawn(board, ChessPiece.Color.White);
        pawn.setPosition("a1");

        HashSet<String> expected = new HashSet<>();
        expected.add("a2");
        expected.add("b2");
        HashSet<String> legalMoves = pawn.legalMoves();

        assertEquals(legalMoves, expected);

    }

}