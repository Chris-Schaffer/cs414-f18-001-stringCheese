package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.Game;
import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User U;
  //  DBConnection connection= (DBConnection) DBConnection.open();

    @BeforeEach
    void setUp() {
        U=new User("meetkumar");
    }

    @Test
    void getName() {
        setUp();
        assertEquals("meetkumar",U.getName());
    }

    @Test
    void getEmail() {
        U=new User("meetkumar","meet@google.com");
        assertEquals("meet@google.com",U.getEmail());
    }

    @Test
    void listRegisteredUsers() {
        ArrayList<User> users=U.listRegisteredUsers();
        assertTrue(!users.isEmpty());
    }
    @Test
    void listActiveGames() {
        U=new User("chris3");
        ArrayList<Game> games=U.listActiveGames();
      //  assertTrue(!games.isEmpty());
       // U=new User("ms");


    }

    @Test
    void deactivate() throws SQLException, IOException {
        ArrayList<User> users=U.listRegisteredUsers();
        U=new User("zaira");
        try {
            User.registerUser("zaira", "zaira@google.com", "zaira123");
        }catch (Exception e){}
        assertTrue(U.deactivate());

    }

    @Test
    void login() {
        String uname="chris";
        String upass="123456";
        assertTrue(User.login(uname,upass));

    }

    @Test
    void registerUser() {
        User user = new User("meetsavaliya2");
        try{
            user.deactivate();
        }catch(Exception e){}
        assertTrue(User.registerUser("meetsavaliya2","meet2@ymail.com","meet123"));
        U.deactivate();
    }

}