package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.ChessPiece;
import edu.colostate.cs.cs414.StringCheese.src.Bishop;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest extends ChessSuite {
    ChessBoard board = new ChessBoard();
    //ChessPiece bishop = new Bishop(board, ChessPiece.Color.White);
    
    @org.junit.jupiter.api.Test
    void legalMoves() {
        Bishop bishop=new Bishop(board,ChessPiece.Color.White);
        bishop.setPosition("g4");
        board.placePiece(bishop, "g4");
        HashSet<String> move= new HashSet<>();
        move=bishop.legalMoves();
        String expected="f3";
        
        assertTrue(move.contains(expected));
        assertTrue(move.contains("e2"));
        assertTrue(move.contains("d1"));
        assertTrue(move.contains("c2"));
        assertTrue(move.contains("b3"));
        assertTrue(move.contains("a4"));
        System.out.println("bishop:"+bishop.getPosition());
        System.out.println("bishop:"+bishop.legalMoves());
        
        
    }
    
    @org.junit.jupiter.api.Test
    void testBackward() {
        Bishop bishop=new Bishop(board,ChessPiece.Color.White);
        bishop.setPosition("g4");
        board.placePiece(bishop, "g4");
        HashSet<String> move= new HashSet<>();
        move=bishop.legalMoves();
        
        System.out.println("bishop:"+bishop.getPosition());
        assertTrue(move.contains("f5"));
        System.out.println("bishop:"+bishop.legalMoves());
    }
}
