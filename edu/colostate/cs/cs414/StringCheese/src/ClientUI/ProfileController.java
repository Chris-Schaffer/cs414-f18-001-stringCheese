package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.GameFacade;
import edu.colostate.cs.cs414.StringCheese.src.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ProfileController implements ItemListener {

    private GameFacade gameFacade;
    private ArrayList<User> registeredUsers;

    public ProfileController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
        this.registeredUsers = new ArrayList<>();
    }

    public String getUserName(){
        return gameFacade.getUserName();
    }


    public String getWinPercentage(){
        return gameFacade.getWinPercentage();
    }

    public String getGamesLost(){
        return gameFacade.getGamesLost();
    }

    public String getGamesPlayed(){
        return gameFacade.getGamesPlayed();
    }

    public String getGamesWon(){
        return gameFacade.getGamesWon();
    }

    public String[] getRegisteredUsers() {
        registeredUsers =  gameFacade.listRegisteredUsers();
        String[] userNames = new String[registeredUsers.size()];

        for(int i =0; i < userNames.length;i++){
            userNames[i] = registeredUsers.get(i).getName();
        }

        return userNames;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String selected =(String) e.getItem();
        System.out.println(selected);
        for(User user: registeredUsers){
            if(user == null){

            }
            else{
                if(user.getName() == selected){
                    gameFacade.setUserProfile(selected);
                }

            }
        }
    }
}
