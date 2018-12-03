package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    User user;
    int gameID = 20;
    Game game;
    
    @BeforeEach
    void setUp() throws Exception {
        String host = "john";
        String invite = "chris";
        String startTime = LocalDate.now().toString();
        
        ArrayList<Game> games = new ArrayList<>();
        user = new User("joey123", "joey123@yahoo.com");
        game = new Game(user.getName());
    }
    
    @AfterEach
    void tearDown() throws Exception {
        
    }
    
    @Test
    void testGameUser() {
        String expectedName = "joey123";
        String expectedEmail = "joey123@yahoo.com";
        
        assertEquals(true, expectedName == user.getName() && expectedEmail == user.getEmail());
    }
    
    @Test
    void testGameIntStringStringStringStringString() {
    }
    
    @Test
    void testJoinGame() {
        boolean result = game.joinGame(gameID);
        assertEquals(true, result);
    }
    
    @Test
    void testQuitGame() {
        
    }
    
    @Test
    void testCreateGame() {
        
    }
    
    @Test
    void testGetStartTime() {
        
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
    }
    
    @Test
    void testGetHost() {
    }
    
    @Test
    void testGetInvitee() {
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
