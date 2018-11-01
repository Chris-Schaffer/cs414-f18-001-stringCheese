package edu.colostate.cs.cs414.StringCheese.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.ChessPiece;
import edu.colostate.cs.cs414.StringCheese.src.Pawn;

class PawnTest extends ChessSuite {

	ChessBoard board;
	
	@BeforeEach
	void setUp() {
		board = new ChessBoard();
	}

	@Test
	public void test() {
		Pawn pawn = new Pawn(board, ChessPiece.Color.White);
		pawn.setPosition("a1");

		String expected = "a1";

		assertEquals(pawn.getPosition(), expected);
	}

	@Test
	public void testIllegalMove()  {
		Pawn pawn = new Pawn(board, ChessPiece.Color.White);
		pawn.setPosition("a1");

		HashSet<String> expected = new HashSet<>();
		expected.add("a2");
		expected.add("b2");
		HashSet<String> legalMoves = pawn.legalMoves();

		assertEquals(legalMoves, expected);

	}

	/*
	 * @org.junit.jupiter.api.Test void legalMoves() { }
	 */

	@org.junit.jupiter.api.Test
	public String toString() {
		return null;
	}
}