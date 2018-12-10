package edu.colostate.cs.cs414.StringCheese.src.UI;

import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.GameFacade;
import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class InvitationPanelController implements ActionListener, ItemListener {

    private GameFacade gameFacade;
    private ArrayList<String> addedUsernameInvites;
    private ArrayList<User> registeredUsers;
    private ArrayList<User> invitees;
    private InvitationPanel invitationPanel;

    public InvitationPanelController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
        this.addedUsernameInvites = new ArrayList<>();
        this.registeredUsers = new ArrayList<>();
        this.invitees = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("Send Invitations")) {
            if (addedUsernameInvites.size() <= 0) {
                //make a popup that says you must select at least one
            } else {
                for (String name : addedUsernameInvites) {
                    for (User user : registeredUsers) {
                        if (user.getName().equalsIgnoreCase(name)) {
                            invitees.add(user);
                        }
                    }
                }

                boolean sent = gameFacade.sendInvitation(invitees);
                if(sent){
                    showSuccessMsg();
                }else{
                    showFailureMsg();
                }
            }


        }
    }

    public ArrayList<String> getRegisteredUsers() {
        registeredUsers =  gameFacade.listRegisteredUsers();
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
          //  System.out.println(item.getName());
            //System.out.println(addedUsernameInvites.size());
        }
        else if(e.getStateChange() == ItemEvent.DESELECTED){
            addedUsernameInvites.remove(item.getName());
           // System.out.println(item.getName());
            //System.out.println(addedUsernameInvites.size());
        }
    }

    public void showSuccessMsg(){
        MainWindow.infoBox("A game was created and invitations were emailed to the user(s).\n" +
                "When the invitation is accepted the game will be displayed on the game page.", "");
    }

    public void showFailureMsg(){
        MainWindow.infoBox("Error Sending Invitation.\n" +
                "No game was created. Check your network connection and try again.","");
    }
}
