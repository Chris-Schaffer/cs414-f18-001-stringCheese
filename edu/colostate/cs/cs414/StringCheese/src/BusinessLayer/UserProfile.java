package edu.colostate.cs.cs414.StringCheese.src.BusinessLayer;

import edu.colostate.cs.cs414.StringCheese.src.Foundation.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class UserProfile {
    private String userName;
    private int gamesPlayed, gamesWon, gamesLost;
    private double winPercentage;

    //stats
    public UserProfile(String userName){
        this.userName =userName;
        setTotalGamesPlayed();
        setTotalGamesWon();
        setTotalGamesLost();
        setWinPercentage();
    }

    /*
    Profile page -- default shows User logged in go to 3.
    1.from dropdown list -- shows listActiveUsers()
    2.choose user object out of list
    3.create a UserProfile object with that user's name
    4.display stats on that user
     */

    //totalGamesWon
    //totalGamesLost
    //totalGamesPlayed
    //totalGamesAbandoned fixme need to implement


    //Each Map<String,Object> is a row in table
    //Each <String,Object> is a coloumn in that row
    //String = Column Name, Object = Value
    //public List<Map<String,Object>> getHistory(){
        public ResultSet getHistory(){
        String query = "SELECT host as PlayerOne,invitee as PlayerTwo,start_time,end_time,result as Winner FROM game WHERE host='"+userName+"' OR invitee='"+userName+"' AND result is not null ORDER BY end_time DESC";
        ResultSet rs = queryDatabase(query);
        try {
            return rs;
            //return DBConnection.map(rs);
        } catch (Exception e) {
            return null;
        }
    }
    public int getGamesPlayed(){ return gamesPlayed; }
    public int getGamesWon(){ return gamesWon; }
    public int getGamesLost(){ return gamesLost;}
    public double getWinPercentage(){ return winPercentage; }

    private void setTotalGamesPlayed(){
        String query = "SELECT COUNT(*) as total FROM game WHERE host='"+userName+"' OR invitee='"+userName+"' AND result IS NOT NULL";
        gamesPlayed = getResult(query);
    }
    private void setTotalGamesWon(){
        String query = "SELECT COUNT(*) as total FROM game WHERE host='"+userName+"' OR invitee='"+userName+"' AND result='"+userName+"'";
        gamesWon = getResult(query);
    }
    private void setTotalGamesLost(){ gamesLost = gamesPlayed - gamesWon; }
    private void setWinPercentage(){ winPercentage = (double)gamesWon/ (double) gamesPlayed * 100; }
    private ResultSet queryDatabase(String query){
        ResultSet rs = null;
        try{
            Connection conn = DBConnection.open();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            DBConnection.close(conn);
        }catch(SQLException se){
            se.printStackTrace();
          //  System.exit(1);
        }
        return rs;
    }
    private int getResult(String query) {
        ResultSet rs = queryDatabase(query);
        try{
            rs.next();
            return rs.getInt(1);
        }catch(SQLException se){
            //se.printStackTrace();
        }
        return -1;
    }
}
