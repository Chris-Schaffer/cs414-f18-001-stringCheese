package edu.colostate.cs.cs414.StringCheese.src.ClientUI;


import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//This class listens to the Join game button and attempts to join the game, if successful user is added to the gamm
//if not then there is a message that displays the game is not able to be joined
public class JoinGameController implements ActionListener {

    private GameFacade gameFacade;

    public JoinGameController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
