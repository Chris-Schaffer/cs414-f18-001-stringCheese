package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.Foundation.DBConnection;
import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.Game;
import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.SerializedGame;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class SerializedGameTest {

    Game g=new Game(3,"meet","chris",new Timestamp(System.currentTimeMillis()));
    DBConnection connection = new DBConnection();
    SerializedGame sg=new SerializedGame();
    @Test
    void write() throws SQLException {
        sg.write(connection,g);
    }

    @Test
    void read() {
        g=sg.read(connection,3);
        assertNotNull(g);
    }
}