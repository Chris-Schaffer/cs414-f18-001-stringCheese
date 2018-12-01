package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.GameFacade;
import edu.colostate.cs.cs414.StringCheese.src.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class InvitationPanelController implements ActionListener, ItemListener {

    private GameFacade gameFacade;
    private ArrayList<String> addedUsernameInvites;

    public InvitationPanelController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
        this.addedUsernameInvites = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public ArrayList<String> getRegisteredUsers() {
       ArrayList<User> registeredUsers =  gameFacade.listRegisteredUsers();
       ArrayList<String> userNames = new ArrayList<>();

       for(User user : registeredUsers){
           userNames.add(user.getName());
       }

       return userNames;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
        if(e.getStateChange() == ItemEvent.SELECTED){
            addedUsernameInvites.add(item.getName());
            System.out.println(item.getName());
            System.out.println(addedUsernameInvites.size());
        }
        else if(e.getStateChange() == ItemEvent.DESELECTED){
            addedUsernameInvites.remove(item.getName());
            System.out.println(item.getName());
            System.out.println(addedUsernameInvites.size());
        }
    }
}
