package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    GridLayout layout = new GridLayout(3,4);
    ProfileController profileController;

    public ProfilePanel(ProfileController profileController){
        this.profileController = profileController;
    }
}
