package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActiveGamesController implements ActionListener {

    private GameFacade gameFacade;

    public ActiveGamesController(GameFacade gameFacade){
        this.gameFacade = gameFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public ArrayList<String> populateActiveGames(){
         ArrayList<Game> games  =  gameFacade.listActiveGames();
         System.out.println("number of games: " + games.size());
         ArrayList<String> gameIdentifiers = new ArrayList<>();
        for(Game game: games){
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