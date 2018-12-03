package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActiveGamesController implements ActionListener {

    private GameFacade gameFacade;
    private ArrayList<Game> activeGames;

    public ActiveGamesController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();
            String event = e.getActionCommand();

            if(event.equalsIgnoreCase("comboBoxChanged")){
                String selected = (String) comboBox.getSelectedItem();
                if(selected != null){
                    int gameID = Integer.parseInt(selected);

                    for(Game game: activeGames){
                        if(game == null){

                        }
                        else{
                            if(game.getGameID() == gameID){
                                gameFacade.setGame(game);
                                gameFacade.loadGame();
                                GamePanel gamePanel = (GamePanel) comboBox.getParent();
                                gamePanel.displayState();
                            }

                        }
                    }
                }
            }
    }

    public ArrayList<String> populateActiveGames(){
         activeGames  =  gameFacade.listActiveGames();
         ArrayList<String> gameIdentifiers = new ArrayList<>();
        for(Game game: activeGames){
            if(game == null){

            }
            else{
                String identifier = "" + game.getGameID();
                gameIdentifiers.add(identifier);
            }

        }
        return gameIdentifiers;
    }

}