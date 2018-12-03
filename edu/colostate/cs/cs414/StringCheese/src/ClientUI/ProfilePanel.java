package edu.colostate.cs.cs414.StringCheese.src.ClientUI;


import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends MainPanel {
    GridLayout layout = new GridLayout(3,4);
    ProfileController profileController;
    UIController controller;

    public ProfilePanel(ProfileController profileController,UIController controller){
        super(controller);
        this.setLayout(new BorderLayout());
        this.profileController = profileController;
        this.controller = controller;
        /*
        JPanel menuArea = new JPanel(new GridLayout(4,1));
        menuArea.setSize(200,90);
        menuArea.setMaximumSize(new Dimension(200,300));

        JButton profile = new JButton("Profile");
        profile.setMaximumSize(new Dimension(200,40));
        profile.addActionListener(controller);
        menuArea.add(profile);

        JButton game = new JButton("Games");
        game.setMaximumSize(new Dimension(200,30));
        game.addActionListener(controller);
        menuArea.add(game);

        JButton invitation = new JButton("Send Invitation/Create Game");
        invitation.setMaximumSize(new Dimension(200,30));
        invitation.addActionListener(controller);
        menuArea.add(invitation);


        JButton logout = new JButton("Log Out");
        logout.setMaximumSize(new Dimension(200,30));
        logout.addActionListener(this.controller);
        menuArea.add(logout);

        this.add(menuArea,BorderLayout.LINE_START);
        */

    }
}
