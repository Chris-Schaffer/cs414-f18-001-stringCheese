package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        board.placePiece(pawn, "c2");
        assertTrue(pawn.getPosition().equals("c2"));
    }


    @org.junit.jupiter.api.Test
    void testGetNextForwardInnerRing() {
        assertTrue(pawn.getNextForward("c2").contains("b2"));
        assertTrue(pawn.getNextForward("c2").size() == 1);

        assertTrue(pawn.getNextForward("b2").contains("b3"));
        assertTrue(pawn.getNextForward("b2").size() ==1);

        assertTrue(pawn.getNextForward("f3").contains("f2"));
        assertTrue(pawn.getNextForward("f3").size()==1);
    }


    @org.junit.jupiter.api.Test
    void testGetNextForwardOuterRing() {
        assertTrue(pawn.getNextForward("b1").contains("a1"));
        assertTrue(pawn.getNextForward("b1").size()==1);

        assertTrue(pawn.getNextForward("c1").contains("b1"));
        assertTrue(pawn.getNextForward("c2").size()==1);

        assertTrue(pawn.getNextForward("a7").contains("b7"));
        assertTrue(pawn.getNextForward("c2").size()==1);
    }


    @org.junit.jupiter.api.Test
    void testGetNextForwardOpposingColorValidMove() {
        board.placePiece(pawn, "c2");
        board.placePiece(rook, "b2");
        assertTrue(pawn.getNextForward("c2").size()==1);
        assertTrue(pawn.getNextForward("c2").contains("b2"));
    }


    @org.junit.jupiter.api.Test
    void testGetNextForwardSameColorInvalidMove() {
        board.placePiece(bishop, "b2");
        board.placePiece(rook, "c2");
        assertTrue(rook.getNextForward("c2").size()==0);
    }

    @org.junit.jupiter.api.Test
    void testGetPrevBackwardInnerRing() {
        assertTrue(pawn.getPrevBackward("b2").contains("c2"));
        assertTrue(pawn.getPrevBackward("b2").size()==1);

        assertTrue(pawn.getPrevBackward("f2").contains("f3"));
        assertTrue(pawn.getPrevBackward("f2").size()==1);

        assertTrue(pawn.getPrevBackward("d6").contains("c6"));
        assertTrue(pawn.getPrevBackward("d6").size()==1);
    }

    @org.junit.jupiter.api.Test
    void testGetPrevBackwardOuterRing() {
        assertTrue(pawn.getPrevBackward("a2").contains("a1"));
        assertTrue(pawn.getPrevBackward("a2").size()==1);

        assertTrue(pawn.getPrevBackward("a1").contains("b1"));
        assertTrue(pawn.getPrevBackward("a1").size()==1);

        assertTrue(pawn.getPrevBackward("f1").contains("g1"));
        assertTrue(pawn.getPrevBackward("f1").size()==1);
    }

    @org.junit.jupiter.api.Test
    void testGetPrevBackwardOpposingColorValidMove() {
        board.placePiece(pawn, "g2");
        board.placePiece(rook, "g1");
        assertTrue(rook.getPrevBackward("g1").size()==1);
        assertTrue(rook.getPrevBackward("g1").contains("g2"));
    }


    @org.junit.jupiter.api.Test
    void testGetPrevBackwardSameColorInvalidMove() {
        board.placePiece(bishop, "b5");
        board.placePiece(rook, "b6");
        assertTrue(rook.getPrevBackward("b6").size()==0);
    }



    @org.junit.jupiter.api.Test
    void testGetSidewaysOuterRing() {
        //FIXME this doesn't work
        assertTrue(rook.getSideways("b2").size()==2);
        assertTrue(rook.getSideways("b2").containsAll(Arrays.asList("b1", "b3")));

        assertTrue(rook.getSideways("a1").size()==0);

        assertTrue(rook.getSideways("g3").size()==1);
        assertTrue(rook.getSideways("g3").contains("f3"));

        assertTrue(rook.getSideways("d1").size()==1);
        assertTrue(rook.getSideways("d1").contains("d2"));

        assertTrue(rook.getSideways("d7").size()==1);
        assertTrue(rook.getSideways("d7").contains("d6"));
    }




}