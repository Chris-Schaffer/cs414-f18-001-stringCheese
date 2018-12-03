package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    User user = new User("sunny", "sunny@yahoo.com");
    Game game = new Game(user.getName());
    int gameID = game.createGame("sunny");;
    String host = "sunny";
    String invitee = "sunny1";

    String startTime = LocalDate.now().toString();

    @BeforeAll
    static void setUp() throws Exception {

        ArrayList<Game> games = new ArrayList<>();
        User.registerUser("sunny","sunny@yahoo.com","123456");

    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    void testGameUser() {
        String expectedName = "sunny";
        String expectedEmail = "sunny@yahoo.com";

        assertEquals(true, expectedName == user.getName() && expectedEmail == user.getEmail());
    }

    @Test
    void testJoinGame() {
        user = new User("sunny1", "sunny1@yahoo.com");
        game = new Game(user.getName());
        User.registerUser("sunny1","sunny1@yahoo.com","1234567");
        boolean result = game.joinGame(gameID, user.getName());
        System.out.println("gameId:"+gameID);
        assertEquals(true, result);
    }

    @Test
    void testQuitGame() {
        boolean result = game.quitGame(user.getName());
        assertEquals(true, result);
    }

    @Test
    void testCreateGame() {
        System.out.println("gameM:"+game.getGameID()+" tG:"+gameID);
        assertEquals(game.getGameID(), gameID);
    }

    @Test
    void testGetStartTime() {

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();

        //System.out.println(strDate);

        String startTime = sdFormat.format(date).toString();
        String tTime = game.getStartTime().toString();
        System.out.println(startTime);
        assertEquals(startTime, game.getStartTime());
    }

    @Test
    void testGetEndTime() {

    }

    @Test
    void testSetEndTime() {
        //fail("Not yet implemented");
    }

    @Test
    void testGetGameID() {
    }

    @Test
    void testSetGameID() {
        testCreateGame();
        assertEquals(game.createGame("sunny")-1, gameID);
    }

    @Test
    void testGetHost() {
        assertEquals(host,user.getName());
    }

    @Test
    void testGetInvitee() {
        user = new User("sunny1", "sunny1@yahoo.com");
        game = new Game(user.getName());
        User.registerUser("sunny1","sunny1@yahoo.com","1234567");
        assertEquals(invitee,this.user.getName());
    }

    @Test
    void testGetResult() {
    }

    @Test
    void testGetBoard() {
    }

    @Test
    void testUpdateDBGameState() {

    }

    @Test
    void testGetUpdatedGameState() {

    }

    @Test
    void testCheckGameStateUpdated() {

    }

}
