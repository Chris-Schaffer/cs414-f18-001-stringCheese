package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.ChessPiece;
import edu.colostate.cs.cs414.StringCheese.src.DBConnection;
import edu.colostate.cs.cs414.StringCheese.src.Game;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {



    static Game g=new Game("meetkumar");
    static int id=g.createGame("meetkumar");

     Game game=new Game(id,"meetkumar","chris",new Timestamp(System.currentTimeMillis()));;
    @AfterAll
    static void tearDown() throws SQLException {
        Connection con=DBConnection.open();
        PreparedStatement ptst=con.prepareStatement("DELETE from gameserialized where game_id=?");
        ptst.setInt(1,id);
        ptst.executeUpdate();
        PreparedStatement ptst1=con.prepareStatement("DELETE from game where game_id=?");
        ptst1.setInt(1,id);
        ptst1.executeUpdate();

    }

    @Test
    void joinGame() {
        assertTrue(game.joinGame(id,"chris"));
    }

    @Test
    void quitGame() {
        assertTrue(game.quitGame("meetkumar"));
    }

    @Test
    void createGame() {
        assertTrue(game.createGame("meetkuamr")>0);
    }

    @Test
    void getStartTime() {
        assertTrue(game.getStartTime()!=null);
    }

    @Test
    void getEndTime() {
        assertTrue(game.getEndTime()==null);
    }

    @Test
    void setEndTime() {
        game.setEndTime("dec-09");
        assertTrue(game.getEndTime()!=null);
    }

    @Test
    void getGameID() {
        assertTrue(game.getGameID()>0);
    }

    @Test
    void setGameID() {
        game.setGameID(id);
        assertTrue(game.getGameID()==id);
    }

    @Test
    void getHost() {
        assertTrue(game.getHost().equalsIgnoreCase("meetkumar"));
    }

    @Test
    void getInvitee() {
        assertTrue(game.getInvitee().equalsIgnoreCase("chris"));
    }

    @Test
    void getResult() {
        assertTrue(game.getResult()==null);
    }

    @Test
    void getBoard() {
        ChessBoard board=game.getBoard();
        assertTrue(board!=null);
    }

    @Test
    void getValidMoves() {

        HashSet<String> legalmoves=game.getValidMoves("c1","meetkumar");
        assertTrue(!legalmoves.isEmpty());
    }

    @Test
    void getType() {
        String piece=game.getType("c2");
        assertTrue(piece!=null);
    }


    @Test
    void updateDBGameState() {

        game.updateDBGameState();
    }

    @Test
    void getUpdatedGameState() {
        Game g=game.getUpdatedGameState();
        assertTrue(g!=null);
    }

    @Test
    void checkGameStateUpdated() {
        assertTrue(game.checkGameStateUpdated());
    }

    @Test
    void promote() {
    }
}