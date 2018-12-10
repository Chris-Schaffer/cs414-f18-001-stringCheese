package edu.colostate.cs.cs414.StringCheese.src.UI;


import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.GameFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//This class listens to the Join game button and attempts to join the game, if successful user is added to the gamm
//if not then there is a message that displays the game is not able to be joined
public class GameController implements ActionListener {

    private GameFacade gameFacade;
    private GamePanel gamePanel;

    public GameController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Join Game")){
            String joinGameId = gamePanel.getJoinGameId().getText();
            //if(joinGameId.isEmpty()){return;}
            boolean gameJoined = gameFacade.joinGame(Integer.parseInt(joinGameId));
            if(gameJoined){gamePanel.showSuccessMsgJoinGame();}
            else{gamePanel.showFailureMsgJoinGame();}
        }else if(e.getActionCommand().equalsIgnoreCase("Refresh")){
            gameFacade.loadGame();
            GamePanel gamePanel = (GamePanel) ((JButton)e.getSource()).getParent();
            gamePanel.displayState();
        }else if(e.getActionCommand().equalsIgnoreCase("Quit Game")){
            gameFacade.quitGame(gameFacade.getUserName());
            gamePanel.showSuccessMsgQuitGame();
        }
    }

    public void setGamePanel(GamePanel panel){
        this.gamePanel =panel;
    }


}
