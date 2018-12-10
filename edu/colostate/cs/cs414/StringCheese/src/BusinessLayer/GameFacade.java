package edu.colostate.cs.cs414.StringCheese.src.BusinessLayer;

import java.util.ArrayList;
import java.util.HashSet;

public class GameFacade {

    private User user;
    private Game game;
    private Invitation invitation;
    private UserProfile userProfile;

    public GameFacade(){

    }

    public boolean login(String name, String password){
        if(User.login(name,password)){
            user = new User(name);
            game = new Game(user.getName());
            userProfile = new UserProfile(user.getName());
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
        return game.joinGame(gameID, user.getName());
    }
    //needs username to set result to opponent name
    public boolean quitGame(String username){
        return game.quitGame(user.getName());
    }

    public boolean sendInvitation(ArrayList<User> users){
        //first creates a game with host as user then creates invitation
        //then sends invitation
        int id=createGame(user.getName());
        if(id<0) { return false; }
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

    public String move(String from, String to) {
        return game.move(from,to);
        //game.updateDBGameState();
    }
    public void updateDBGameState()
    {
        game.updateDBGameState();
    }


    public User getUser(){
        return user;
    }

    /*
    at the end of a move the method updateDBGameState() is called
    this serializes the game object and adds it along w/ current timestamp to DB
    if checkGameStateUpdated() then replace the current game object with the newer version
     */
    public boolean loadGame(){
        if(checkGameStateUpdated()){
            game = game.getUpdatedGameState();
            return true;
        }
        return false;
    }

    public void setGame(Game game){
        this.game = game;
    }

    //returns true if DB has newer version of game state
    private boolean checkGameStateUpdated(){
        return game.checkGameStateUpdated();
    }

    public void promote(String position, String choice) {
        game.promote(position,choice);
    }

    public String getUserName() {
        return user.getName();
    }

    public String getGamesPlayed() {
        int played = userProfile.getGamesPlayed();
        return "Played: " + played + "\n";
    }

    public String getGamesWon() {
        int won = userProfile.getGamesWon();
        return "won: " + won + "\n";
    }

    public String getGamesLost() {
        int lost = userProfile.getGamesLost();
        return "lost: " + lost + "\n";
    }

    public String getWinPercentage() {
        double percentage = userProfile.getWinPercentage();
        return "Win Percentage: %" + percentage + "\n";
    }

    public void resetUserProfile() {
        this.userProfile = new UserProfile(user.getName());
    }

    public void setUserProfile(String userName) {
        userProfile = new UserProfile(userName);
    }
}
