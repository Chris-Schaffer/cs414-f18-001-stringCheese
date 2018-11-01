package edu.colostate.cs.cs414.StringCheese.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.colostate.cs.cs414.StringCheese.src.*;


class ChessBoardTest extends ChessSuite {

    ChessBoard board;
    ChessPiece piece;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        piece = new King(board, ChessPiece.Color.White);
    }

    @AfterEach
    void tearDown() {

    }



    @org.junit.jupiter.api.Test
    void initialize() {
        // a = 0, b = 1, ..., h = 7
        // white king at e1, index [0][7]
        // black queen at d8, index [7][3]
        //Assertions.assertTrue(board.getPiece("h7").getColor() == ChessPiece.Color.White);
        //Assertions.assertTrue(board.getPiece("e1").toString().equals("\u2654"));
    }

    @org.junit.jupiter.api.Test
    void getPiece() {
    }

    @org.junit.jupiter.api.Test
    void placePiece() {
    }

    @org.junit.jupiter.api.Test
    void move() {
    }

    @Test
    void initialize1() {
    }

    @Test
    void getPiece1() {
    }

    @Test
    void placePiece1() {
    }

    @Test
    void move1() {
    }



    @Test
    void main() {
    }
}