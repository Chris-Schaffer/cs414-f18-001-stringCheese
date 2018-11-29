package edu.colostate.cs.cs414.StringCheese.src;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
NOTE: flow of creating a game
GameFacade
 -> Game game = new Game(user);
 -> ArrayList<Game> games = game.listActiveGames();//seems like listActiveGames should be in User class
 -> Display on UI - ArrayList<Pair<OpponentName,StartTime>>
 -> Pair<String,String> pair = new Pair<>();
 -> UI -> pair = User chosen pair
 -> game = game associated with pair
 */
public class Game implements java.io.Serializable {

    private Statement stmt;
    private Connection conn;
    private int gameID;
    private String host, invitee, result, startTime, endTime;
    private User user1, user2;
    private String name;
    private ChessBoard board;


    //used to initially create a game in GameFacade
    //then game object is changed to a game chosen from listActiveGames()
    public Game(User user){
        this.user1 = user;
        name = user.getName();
        gameID = -1;
        board = new ChessBoard();
        board.initialize();
    }
    public Game(int gameID, String host, String invitee, String startTime,
                      String endTime, String result){
        this.gameID = gameID;
        this.host = host;
        this.invitee = invitee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.result = result;
    }


    //FIXME NEED TO TEST
    //NOTE: flow of joinGame()
    //GameFacade
    //ArrayList<Game> games = new ArrayList<>();
    //Game game = new Game(user);
    //game.listActiveGames();
    //game.joinGame(gameID);
    public boolean joinGame(int gameID){
        //check if game has invitee before joining
        if(isGameStarted(gameID)){
            System.out.println("This game has already started");
            return false;
        }
        int userID;
        ResultSet rs1 = queryDatabase("SELECT user_id FROM user WHERE name='"+this.name+"'");
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

    //abandon game
    public boolean quitGame(String username){
        String opponent=getOpponent(username);
        String query = "UPDATE game SET abandon=TRUE, end_time=CURRENT_TIMESTAMP(), result='"
                +opponent+"' WHERE game_id='"+gameID+"'";
        return updateDatabase(query);
    }
    //quitGame() helper
    private String getOpponent(String username){
        String query = "SELECT host, invitee FROM game WHERE game_id='"+gameID+"'";
        ResultSet rs = queryDatabase(query);
        String opponent="";
        try {
            if(rs.next()){
                String host = rs.getString("host");
                String invitee = rs.getString("invitee");
                if(host.equals(username)){
                    opponent = invitee;
                }else{
                    opponent = host;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return opponent;
    }

    //create new game with playerOne as host
    public int createGame(String playerOne){
        String query = "INSERT INTO GAME (host, start_time) " +
                "VALUES ('"+playerOne+"', CURRENT_TIMESTAMP() )";
        if(updateDatabase(query)) {
            setGameID(playerOne);
        }else{
            System.out.println("Something went wrong in creating game");
            System.exit(1);
        }
        return gameID;

    }
    //createGame() helper function
    private void setGameID(String playerOne) {
        //get gameID of newest game started by playerOne - called from createGame()
        String query = "SELECT TOP 1 * FROM game WHERE host='"+playerOne+"' ORDER BY start_time desc";
        ResultSet rs = queryDatabase(query);
        try {
            if (rs != null && rs.next()) {
                gameID = rs.getInt("game_id");
            }else{
                //result set was null or empty
                System.out.println("Query failed in setGameID");
                System.exit(1);
            }
        }catch(SQLException se){
            System.out.println("Error in query");
            System.exit(1);
        }
    }

    //FIXME NEED TO TEST
    //check if game table has null in invitee column
    private boolean isGameStarted(int gameID) {
        ResultSet rs = queryDatabase("SELECT invitee FROM game WHERE game_id="+gameID);
        try{
            if(rs.next()){
                //-1 represents null
                int isNULL = rs.getInt("invitee");
                return isNULL==(-1);
            }else{
                System.out.println("Something went wrong, resultSet is empty");
                System.exit(1);
            }
        }catch(SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return true;
    }
    //query DB with a query that does not require a
    //returned result. method returns true if the
    //query made changes to 1 or more records in the table
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

    //takes a SQL statement, queries DB and returns resultset or null
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getGameID() {
        //FIXME only works if createGame was called otherwise returns -1.
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getHost() {
        return host;
    }

    public String getInvitee() {
        return invitee;
    }

    public String getResult() {
        return result;
    }
}
