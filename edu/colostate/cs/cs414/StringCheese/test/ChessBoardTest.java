package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.util.ArrayList;
import java.util.Arrays;

import static edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.ChessPiece.Color.Black;
import static edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.ChessPiece.Color.White;
import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest extends ChessSuite {

    ChessBoard board;
    ChessPiece piece;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
      //  piece = new King(board, ChessPiece.Color.White);
    }

    @AfterEach
    void tearDown() {

    }



    @org.junit.jupiter.api.Test
    void initialize() {
        board.initialize();
       // board.getPiece("d2");
        assertEquals(board.getPieceType("d2"),"whiteking");
        assertEquals(board.getPieceType("c1"),"whitepawn");
        assertEquals(board.getPieceType("c2"),"whitepawn");
        assertEquals(board.getPieceType("e1"),"whiterook");
        assertEquals(board.getPieceType("e2"),"whiterook");
        assertEquals(board.getPieceType("d1"),"whitebishop");
        assertEquals(board.getPieceType("d6"),"blackking");
        assertEquals(board.getPieceType("c6"),"blackrook");
        assertEquals(board.getPieceType("c7"),"blackrook");
        assertEquals(board.getPieceType("e6"),"blackpawn");
        assertEquals(board.getPieceType("e7"),"blackpawn");
        assertEquals(board.getPieceType("d7"),"blackbishop");

    }

    @org.junit.jupiter.api.Test
    void getPiece() {
        board.initialize();

        ChessPiece piece=board.getPiece("d2");
        assertTrue(piece instanceof King);
        piece=board.getPiece("c1");
        assertTrue(piece instanceof Pawn);
        piece=board.getPiece("d1");
        assertTrue(piece instanceof Bishop);
        piece=board.getPiece("e1");
        assertTrue(piece instanceof Rook);

    }


    @org.junit.jupiter.api.Test
    void placePiece() {
        board.placePiece(new King(board,White),"a1");
        board.placePiece(new King(board,Black),"d6");
        board.placePiece(new Rook(board,White),"b6");
        board.placePiece(new Bishop(board,Black),"a5");
        board.placePiece(new Pawn(board,White),"g5");
        ArrayList<String> position=new ArrayList<>(Arrays.asList("a1","d6","b6","a5","g5"));
        ChessPiece piece;
        for(String pos:position)
        {
            piece=board.getPiece(pos);
            assertTrue(piece!=null);
        }



    }

    @org.junit.jupiter.api.Test
    void move() {
        board.initialize();
        board.selectPiece("c1");
        board.move("c1","b1");
        board.selectPiece("e6");
        board.move("e6","f5");
        board.selectPiece("e1");
        board.move("e1","f1");
        board.selectPiece("e7");
        board.move("e7","f7");
        board.selectPiece("d2");
        board.move("d2","d1");
        ArrayList<String> position=new ArrayList<>(Arrays.asList("b1","f5","f1","f7","d1"));
        ChessPiece piece;
        for(String pos:position)
        {
            piece=board.getPiece(pos);
            assertNotNull(piece);
        }

    }


}