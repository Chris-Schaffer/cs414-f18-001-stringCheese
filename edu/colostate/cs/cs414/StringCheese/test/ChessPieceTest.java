package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChessPieceTest {

    ChessBoard board = new ChessBoard();
    ChessPiece pawn = new Pawn(board, ChessPiece.Color.White);
    ChessPiece king = new King(board, ChessPiece.Color.White);
    ChessPiece rook = new Rook(board, ChessPiece.Color.Black);
    ChessPiece bishop = new Bishop(board, ChessPiece.Color.Black);


    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void testPlacePiece(){
        board.placePiece(pawn, "C2");
        assertTrue(pawn.getPosition().equals("C2"));
    }


    @org.junit.jupiter.api.Test
    void testGetNextForward() {
        board.placePiece(pawn, "C2");
        assertTrue(pawn.legalMoves().contains(Arrays.asList("B1", "B2", "B3")));
        assertTrue(pawn.legalMoves().size() == 3);
    }

    @org.junit.jupiter.api.Test
    void testLegalMoves() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}