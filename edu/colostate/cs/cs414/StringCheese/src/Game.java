package edu.colostate.cs.cs414.StringCheese.src;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Game {

    private Statement stmt;
    private Connection conn;
    private int gameID;
    private String startTime;
    private String endTime;

    public Game(){
        //
    }
    //FIXME NEED TO TEST
    public ArrayList<Pair<String,String>> listActiveGames(String name){
        //FIXME not sure what type to return.
        // what do you want to display to user when displaying games
        // Pair<OpponentName, StartTime>
        Pair<String,String> names;
        ArrayList<Pair<String,String>> list = new ArrayList<>();
        String host, invitee,startTime;
        ResultSet rs = queryDatabase("SELECT host, invitee, start_time FROM game WHERE host='"+name+"' OR invitee='"+name+"'");
        if(rs != null){
            try {
                while (rs.next()) {
                    host = rs.getString("host");
                    invitee = rs.getString("invitee");
                    startTime = rs.getString("start_time");
                    if(host.equals(name)){
                        names = new Pair<>(invitee,startTime);
                    }else{
                        names = new Pair<>(host,startTime);
                    }
                    list.add(names);
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return list;
    }

    //FIXME NEED TO TEST
    public boolean joinGame(int gameID, String name){
        //check if game has invitee before joining
        if(gameStarted(gameID)){
            System.out.println("This game has already started");
            return false;
        }
        int userID = 0;
        ResultSet rs1 = queryDatabase("SELECT user_id FROM user WHERE name='"+name+"'");
        try {
            if(rs1 !=null && rs1.next()) {
                userID = rs1.getInt("user_id");
                return updateDatabase("UPDATE game SET invitee="+userID+" WHERE game_id="+gameID);
            }else {
                System.out.println("Query went wrong");
                System.exit(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }


    //FIXME NEED TO TEST
    //check if game has invitee
    private boolean gameStarted(int gameID) {
        ResultSet rs = queryDatabase("SELECT invitee FROM game WHERE game_id="+gameID);
        try{
            if(rs.next()){
                int isNull = rs.getInt("invitee");
                return isNull==0;
            }else{
                System.out.println("Something went wrong");
                System.exit(1);
            }
        }catch(SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return true;
    }

    private boolean updateDatabase(String query){
        int numRecordsAffected=0;
        try{
            conn = DBConnection.open();
            stmt = conn.createStatement();
            numRecordsAffected = stmt.executeUpdate(query);
            DBConnection.close(conn);
        }catch(SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return numRecordsAffected>=1;
    }

    private ResultSet queryDatabase(String query){
        ResultSet rs = null;
        try{
            conn = DBConnection.open();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            DBConnection.close(conn);
        }catch(SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return rs;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
