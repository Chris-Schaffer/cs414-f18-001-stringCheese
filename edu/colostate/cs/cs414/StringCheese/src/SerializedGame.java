package edu.colostate.cs.cs414.StringCheese.src;

import java.io.*;
import java.sql.*;

public class SerializedGame {

    public void write(DBConnection conn, Game tmp) throws IOException, SQLException {
        Connection con = conn.open();
        try {
            byte[] data = null;
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(bt);
            obj.writeObject(tmp);
            obj.flush();
            obj.close();
            obj.reset();
            bt.close();
            data = bt.toByteArray();
            System.out.println("--------------" + data);

            if (!con.isClosed()) {
                PreparedStatement ptst = con.prepareStatement("SELECT * from gameserialized where game_id=?");// For meet local db instance
                // PreparedStatement ptst = con.prepareStatement("insert into gameSerialized values(?,?)");//for amazon aws
                ptst.setInt(1, tmp.getGameID());
                ResultSet rs = ptst.executeQuery();
                if (!rs.next()) {
                    ptst = con.prepareStatement("insert into gameserialized values(?,?)");
                    ptst.setInt(1, tmp.getGameID());
                    ptst.setBytes(2, data);
                    ptst.executeUpdate();
                    System.out.println("+++++inserted++++++");
                } else {
                    ptst = con.prepareStatement("update gameserialized SET game_object=? where game_id=?");
                    ptst.setBytes(1, data);
                    ptst.setInt(2, tmp.getGameID());
                    ptst.executeUpdate();
                    System.out.println("+++++update+++++");
                }
                rs.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Game read(Connection con, int id) {

        Game meet = null;
        try {
            if (!con.isClosed()) {
                PreparedStatement ptst = con.prepareStatement("select game_object from gameserialized where game_id=?");//For meet LOcal db Instance
                //  PreparedStatement ptst = con.prepareStatement("select game_object from gameSerialized where game_id=?");//for aws Web Service
                ptst.setInt(1, id);
                ResultSet rs = ptst.executeQuery();
                rs.next();
                byte[] t = rs.getBytes(1);
                System.out.println(t);
                if (t != null) {
                    ByteArrayInputStream targetStream = new ByteArrayInputStream(t);
                    ObjectInputStream in = new ObjectInputStream(targetStream);
                    meet = (Game) in.readObject();
                    // in.reset();
                    in.close();

                    targetStream.close();

                }
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return meet;
    }
}

