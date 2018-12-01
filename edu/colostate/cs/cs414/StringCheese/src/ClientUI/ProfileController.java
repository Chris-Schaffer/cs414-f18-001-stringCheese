package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController implements ActionListener {

    private GameFacade gameFacade;
    private JComboBox<String> RegisteredUsers;

    public ProfileController(GameFacade gameFacade){
        this.gameFacade = gameFacade;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
