package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.ChessPiece;
import edu.colostate.cs.cs414.StringCheese.src.Rook;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class RookTest extends ChessSuite {
    
    ChessBoard board;
    @BeforeEach
    void setUp() {
        board = new ChessBoard();
    }

     @org.junit.jupiter.api.Test
    void legalMoves() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("b2");

        String expected="b1";
        HashSet<String> move= new HashSet<>();
        move=rook.legalMoves();


        assertTrue(move.contains(expected));
        assertTrue(move.contains("a2"));
        assertTrue(move.contains("b6"));
        assertTrue(move.contains("b5"));
        assertTrue(move.contains("c2"));
        rook.setPosition("b1");

        move=rook.legalMoves();


        assertTrue(move.contains("c1"));
        assertTrue(move.contains("a2"));
        assertTrue(move.contains("b6"));
        assertTrue(move.contains("b5"));
        assertTrue(move.contains("a1"));
        assertTrue(move.contains("b7"));
        assertTrue(move.contains("a7"));
        //Other position
        rook.setPosition("f7");

        move=rook.legalMoves();


        assertTrue(move.contains("e7"));
        assertTrue(move.contains("g7"));
        assertTrue(move.contains("g1"));
        assertTrue(move.contains("g5"));
        assertTrue(move.contains("f1"));
        assertTrue(move.contains("f5"));
        assertTrue(move.contains("f4"));
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
        assertTrue(move.contains("a2"));
        assertTrue(move.contains("b1"));
        rook.setPosition("f6");
        move=rook.legalMoves();
        assertTrue(move.contains("g6"));
        assertTrue(move.contains("f7"));
        assertTrue(move.contains("e6"));
        assertTrue(move.contains("f5"));
        rook.setPosition("b4");
        move=rook.legalMoves();
        assertTrue(move.contains("b3"));
        assertTrue(move.contains("b6"));
        assertTrue(move.contains("a4"));
       // assertTrue(move.contains("f5"));


    }
    @org.junit.jupiter.api.Test
    void sideways() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("b2");

        HashSet<String> move= new HashSet<>();
        move=rook.legalMoves();
        assertTrue(move.contains("a2"));
        assertTrue(move.contains("b1"));

    }
    @org.junit.jupiter.api.Test
    void backWays() {
        Rook rook=new Rook(board,ChessPiece.Color.White);
        rook.setPosition("b1");

        HashSet<String> move= new HashSet<>();
        move=rook.legalMoves();
        assertTrue(move.contains("c1"));

    }

}
