package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.DBConnection;
import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.SerializedGame;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SerializedGameTest {

    Game g=new Game(100,"meet","chris","2-dec","","UNFINISHED");

    SerializedGame sg=new SerializedGame();
    @Test
    void write() throws SQLException {
        g.createGame("chris");
      //  String str=g.move("c1","b1");
        sg.write(new DBConnection(),g);

    }

    @Test
    void read() {
        Game g=sg.read(new DBConnection(),100);
        assertTrue(g!=null);
    }
}