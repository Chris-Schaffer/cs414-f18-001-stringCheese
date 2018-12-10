package edu.colostate.cs.cs414.StringCheese.src.BusinessLayer;


import edu.colostate.cs.cs414.StringCheese.src.Foundation.DBConnection;

import java.io.Serializable;
import java.sql.*;
import java.util.HashSet;

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
public class Game implements Serializable {

    private Statement stmt;
    private Connection conn;
    private int gameID;
    private String result, endTime, host, invitee;
    private String name;
    private String whitePlayer;
    private ChessBoard board;
    private static final long serialVersionUID = -4618541295249374750L;
    private Timestamp startTime, lastUpdated;


    //used to initially create a game in GameFacade
    //then game object is changed to a game chosen from listActiveGames()
    public Game(String name){
        this.name = name;
        gameID = -1;
        board = new ChessBoard();
        board.initialize();
        this.lastUpdated = new Timestamp(System.currentTimeMillis());
    }
    public Game(int gameID, String host, String invitee, Timestamp startTime){
        this.gameID = gameID;
        this.host = host;
        this.invitee = invitee;
        this.startTime = startTime;
        this.endTime = null;
        this.result = null;
        board = new ChessBoard();
        board.initialize();
        board.setWhitePlayer(host);
        this.whitePlayer = host;
        this.lastUpdated = startTime;
    }

    public boolean joinGame(int gameID, String invitee){
        //check if game has invitee before joining
        if(isGameStarted(gameID)){
           System.out.println("This game has already started");
            return false;
        }
        boolean isUpdated =  updateDatabase("UPDATE game SET invitee='"+ invitee +"' WHERE game_id="+gameID);
        createSerializesGameObject(gameID, invitee);
        return isUpdated;
    }

    private void createSerializesGameObject(int gameID, String invitee) {
        String query = "select * from game where game_id=" + gameID;
        ResultSet rs = queryDatabase(query);
        String host;
        Timestamp startTime;
        try {
            while (rs.next()){
                startTime = rs.getTimestamp("start_time");
                host = rs.getString("host");
                Game game = new Game(gameID,host,invitee,startTime);
                SerializedGame sg = new SerializedGame();
                sg.write(new DBConnection(),game);
            }
        }catch (SQLException e){
        }
    }

    //check if game table has null in invitee column
    private boolean isGameStarted(int gameID) {
        ResultSet rs = queryDatabase("SELECT invitee FROM game WHERE game_id="+gameID);
        try{
            if(rs.next()){
                String invitee = rs.getString("invitee");
                rs.close();
                return invitee != null;
            }else{
                System.out.println("Something went wrong, resultSet is empty");
                return false;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
        return true;
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
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opponent;
    }

    //create new game with playerOne as host
    public int createGame(String playerOne){
        String query = "INSERT INTO game (host, start_time) " +
                "VALUES ('"+playerOne+"', CURRENT_TIMESTAMP() )";
        if(updateDatabase(query)) {
            setGameID(playerOne);
        }else{
            System.out.println("Something went wrong in creating game");
            return -1;
        }
        return gameID;

    }

    //createGame() helper function
    private void setGameID(String playerOne) {
        //get gameID of newest game started by playerOne - called from createGame()
        String query = "SELECT game_id FROM game WHERE host='"+playerOne+"' ORDER BY start_time desc limit 1";
        ResultSet rs = queryDatabase(query);
        try {
            if (rs != null && rs.next()) {
                gameID = rs.getInt("game_id");
                rs.close();
            }else{
                //result set was null or empty
                System.out.println("Query failed in setGameID");
            }
        }catch(SQLException se){
            System.out.println("Error in query");
            se.printStackTrace();
        }
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
        }catch(SQLException se){
            se.printStackTrace();
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
        }catch(SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return rs;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int  getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getHost() { return host; }

    public String getInvitee() {
        return invitee;
    }

    public String getResult() {
        return result;
    }

    public ChessBoard getBoard(){return board;}

    public HashSet<String> getValidMoves(String position, String name) {
        if(board.getPiece(position) == null){ return new HashSet<>(); }

        ChessPiece.Color myColor;
        if(name.equals(host)) { myColor= ChessPiece.Color.White; }
        else { myColor= ChessPiece.Color.Black; }

        if(myColor.equals(board.getPiece(position).getColor())){
            return board.selectPiece(position);
        }

        return new HashSet<String>();
    }
    public String getType(String position) { return board.getPieceType(position); }
    public String move(String from, String to) {
        String message = board.move(from,to);
        updateDBGameState();
        //game is over
        if(message.equalsIgnoreCase("Checkmate") || message.equalsIgnoreCase("Winner")){
            //if white won then host is winner
            if(board.getPiece(to).getColor() == ChessPiece.Color.White){ result = host; }
            else{ result = invitee; }
            endGame();
        }
        return message;
    }

    private boolean endGame(){
        String query = "UPDATE game SET result='"+result+"', end_time= CURRENT_TIMESTAMP() WHERE game_id="+gameID;
        return updateDatabase(query);
    }

    public void updateDBGameState() {
        SerializedGame sg = new SerializedGame();
        sg.write(new DBConnection(),this);
    }

    //returns newer version of serialized game object to replaced this game object
    public Game getUpdatedGameState(){
        SerializedGame sg= new SerializedGame();
        return sg.read(new DBConnection(),gameID);
    }

    //checks DB to see if last time serialized object was updated is after this object was created
    //returns true if DB has newer version of game object
    public boolean checkGameStateUpdated(){
        String query = "SELECT last_updated FROM gameserialized WHERE game_id="+gameID;
        ResultSet rs = queryDatabase(query);
        Timestamp time = null;
        try{
            rs.next();
            time = rs.getTimestamp(1);
            rs.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return !time.equals(lastUpdated);
    }

    public void promote(String position, String choice) {
       ChessPiece.Color color = board.getPiece(position).getColor();
        if(choice.equalsIgnoreCase("rook")){
            board.placePiece(new Rook(board,color),position);
        }
        else{
            board.placePiece(new Bishop(board,color),position);
        }
    }
}
