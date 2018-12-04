package edu.colostate.cs.cs414.StringCheese.src.ClientUI;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfilePanel extends MainPanel {
    GridLayout layout = new GridLayout(3,4);
    private ProfileController profileController;
   private UIController controller;
   private String userName;
   private JLabel profileName;
   private JPanel winData;
   private JComboBox<String> listActiveUsers;

    public ProfilePanel(ProfileController profileController,UIController controller){
        super(controller);
        this.profileController = profileController;
        this.controller = controller;

    }

    public void setUpProfile(){
        Component[] comps = this.getComponents();
        for(Component comp : comps){
            if(comp == profileName){
                this.remove(profileName);
            }
            else if(comp == listActiveUsers){
                this.remove(listActiveUsers);
            }
            else if(comp == winData){
                winData.removeAll();
                this.remove(winData);
            }
        }
        userName = profileController.getUserName();
        profileName = new JLabel("Profile: " + userName);
        profileName.setFont(new Font(Font.SANS_SERIF,Font.BOLD,75));
        this.add(profileName);

        winData = new JPanel(new GridLayout(5,1));
        winData.add(new JLabel("GameData: "));
        winData.add(new JLabel(profileController.getGamesPlayed()));
        winData.add(new JLabel(profileController.getGamesWon()));
        winData.add(new JLabel(profileController.getGamesLost()));
        winData.add(new JLabel(profileController.getWinPercentage()));
        this.add(winData);

        listActiveUsers = new JComboBox<>(profileController.getRegisteredUsers());
        listActiveUsers.addItemListener(profileController);
        this.add(listActiveUsers);
    }
}
