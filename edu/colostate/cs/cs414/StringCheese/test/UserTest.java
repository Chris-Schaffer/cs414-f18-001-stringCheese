package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.DBConnection;
import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User U;
  //  DBConnection connection= (DBConnection) DBConnection.open();

    @BeforeEach
    void setUp() {
        U=new User("meetkumar");
    }

    @AfterEach
    void tearDown() {
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
        //fixme i am not working
      //  assertTrue(!games.isEmpty());
       // U=new User("ms");


    }

    @Test
    void deactivate() {
        //fixme required testing
      /*  ArrayList<User> users=U.listRegisteredUsers();
        U=new User("zaira");
        ArrayList<String> uname=new ArrayList<>();
        for(User U:users)
        {
            uname.add(U.getName());
        }
        assertTrue(uname.contains(U.getName()));
        assertTrue(U.deactivate());*/

    }

    @Test
    void login() {
        String uname="meetkumar";
        String upass="meet@12345";
        assertTrue(User.login(uname,upass));

    }

    @Test
    void registerUser() {
        //fixme need to trst this
    //    assertTrue(User.registerUser("meetkumar","meet@yahoo.com","meet123"));



    }

    @Test
    void main() {
    }
}