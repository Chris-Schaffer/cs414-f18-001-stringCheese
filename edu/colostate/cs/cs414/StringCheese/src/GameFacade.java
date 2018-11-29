package edu.colostate.cs.cs414.StringCheese.src;

import java.util.ArrayList;
import java.util.HashSet;

public class GameFacade {

    private ChessBoard board;
    private User user;
    private Game game;
    private Invitation invitation;


    public GameFacade(){
        board = new ChessBoard();
        board.initialize();
    }

    public boolean login(String name, String password){
        if(User.login(name,password)){
            user = new User(name);
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
    public int createGame(String playerOne){
        return game.createGame(playerOne);
    }

    public boolean sendInvitation(ArrayList<User> users){
        //first creates a game with host as user then creates invitation
        //then sends invitation
        invitation = new Invitation(user.getName(),createGame(user.getName()));
        return invitation.sendInvitation(users);
    }

    public HashSet<String> getValidMoves(String position) {
        return board.selectPiece(position);
    }

    public String getType(String position) {
       return board.getPieceType(position);
    }

    public void move(String from, String to) {
        board.move(from,to);
    }

    public User getUser(){
        return user;
    }
}
