package edu.colostate.cs.cs414.StringCheese.src;

import java.util.ArrayList;
import java.util.HashSet;

public class GameFacade {

    private ChessBoard board;   //should access board in the game object, not create a new one
    private User user;
    private Game game;
    private Invitation invitation;

    public GameFacade(){
        //remove these as board is set upon login()
        //currently needed when skipping login i.e. debugging
        board = new ChessBoard();
        board.initialize();
    }

    public boolean login(String name, String password){
        if(User.login(name,password)){
            user = new User(name);
            game = new Game(user);
            board = game.getBoard();
            return true;
        }
        return false;
    }
    public boolean register(String name, String email, String password){
        return User.registerUser(name,email,password);
    }
    //must be logged in to deactivate
    public boolean deactivate(String name, String password){
        return user.deactivate();
    }
    public ArrayList<User> listRegisteredUsers(){
        return user.listRegisteredUsers();
    }
    public ArrayList<Game> listActiveGames(){
        return user.listActiveGames();
    }
    public boolean joinGame(int gameID){
        return game.joinGame(gameID);
    }
    //needs username to set result to opponent name
    public boolean quitGame(String username){
        return game.quitGame(user.getName());
    }

    public boolean sendInvitation(ArrayList<User> users){
        //first creates a game with host as user then creates invitation
        //then sends invitation
        invitation = new Invitation(user.getName(),createGame(user.getName()));
        return invitation.sendInvitation(users);
    }
    //called in sendInvitation
    private int createGame(String playerOne){
        return game.createGame(playerOne);
    }

    public HashSet<String> getValidMoves(String position) {
        return game.getValidMoves(position, user.getName());
    }

    public String getType(String position) {
       return game.getType(position);
    }

    public void move(String from, String to) {
        game.move(from,to);
        //game.updateDBGameState();
    }


    public User getUser(){
        return user;
    }
    //fixme test
    /*
    at the end of a move the method updateDBGameState() is called
    this serializes the game object and adds it along w/ current timestamp to DB
    if checkGameStateUpdated() then replace the current game object with the newer version
     */
    public boolean callMethodEveryNSeconds(){
        if(checkGameStateUpdated()){
            game = game.getUpdatedGameState();
            return true;
        }
        return false;
        //repaint board
    }



    //fixme need to test
    //returns true if DB has newer version of gamestate
    public boolean checkGameStateUpdated(){
        return game.checkGameStateUpdated();

    }
}
