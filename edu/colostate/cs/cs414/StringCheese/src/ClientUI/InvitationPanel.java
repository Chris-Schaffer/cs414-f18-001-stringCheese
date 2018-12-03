package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class InvitationPanel extends MainPanel {
    private InvitationPanelController invitationPanelController;
    private JMenuBar menuBar;
    private JMenu selectRegisteredUsers;
    private JButton sendInvitations;
    private JMenuItem[] names;
    private UIController controller;

    public InvitationPanel(InvitationPanelController invitationPanelController,UIController controller){
        super(controller);
        this.controller = controller;
        this.invitationPanelController = invitationPanelController;
        //this.setLayout(new BorderLayout());


        /*
        JPanel menuArea = new JPanel(new GridLayout(4,1));
        menuArea.setSize(200,90);
        menuArea.setMaximumSize(new Dimension(200,300));

        JButton profile = new JButton("Profile");
        profile.setMaximumSize(new Dimension(200,40));
        profile.addActionListener(this.controller);
        menuArea.add(profile);

        JButton game = new JButton("Games");
        game.setMaximumSize(new Dimension(200,30));
        game.addActionListener(this.controller);
        menuArea.add(game);

        JButton invitation = new JButton("Send Invitation/Create Game");
        invitation.setMaximumSize(new Dimension(200,30));
        invitation.addActionListener(this.controller);
        menuArea.add(invitation);


        JButton logout = new JButton("Log Out");
        logout.setMaximumSize(new Dimension(200,30));
        logout.addActionListener(this.controller);
        menuArea.add(logout);

        this.add(menuArea);
        */

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

        sendInvitations = new JButton("Send Invitations");
        sendInvitations.setMaximumSize(new Dimension(200,30));
        sendInvitations.addActionListener(invitationPanelController);
        this.add(sendInvitations);
    }
}
