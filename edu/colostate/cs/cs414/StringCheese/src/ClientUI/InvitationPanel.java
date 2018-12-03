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
    }

    public void initializeMenu(){
        JPanel sendPanel = new JPanel(new BorderLayout());
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

        menuBar.setVisible(true);

        sendInvitations = new JButton("Send Invitations");
        sendInvitations.setSize(new Dimension(30,10));
        sendInvitations.addActionListener(invitationPanelController);
        this.add(menuBar);
        this.add(sendInvitations);
        this.revalidate();
        this.repaint();

    }

    public void showSuccessMsg(){
        MainWindow.infoBox("Invitation Sent!\n", "");
    }

    public void showFailureMsg(){
        MainWindow.infoBox("Error Sending Invitation","");
    }
}
