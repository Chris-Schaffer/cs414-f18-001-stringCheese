package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvitationPanelController implements ActionListener {

    private GameFacade gameFacade;

    public InvitationPanelController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
