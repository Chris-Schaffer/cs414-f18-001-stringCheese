package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

public class InvitationPanel extends JPanel {
    private InvitationPanelController invitationPanelController;
    JMenuBar menuBar;
    JMenu selectRegisteredUsers;
    JButton sendInvitations;
    JMenuItem[] names;

    public InvitationPanel(InvitationPanelController invitationPanelController){
        this.invitationPanelController = invitationPanelController;
    }

    public void initializeMenu(){
        menuBar = new JMenuBar();
        selectRegisteredUsers = new JMenu("Select Users to Invite");
        ArrayList<String> registeredUsers = invitationPanelController.getRegisteredUsers();
        names = new JMenuItem[registeredUsers.size()];

        for(int i = 0; i < registeredUsers.size();i++){
            names[i] = new JCheckBoxMenuItem(registeredUsers.get(i));
            names[i].setName(registeredUsers.get(i));
            names[i].addItemListener(invitationPanelController);
            selectRegisteredUsers.add(names[i]);
        }
        menuBar.add(selectRegisteredUsers);
        this.add(menuBar);
        menuBar.setVisible(true);

    }
}
