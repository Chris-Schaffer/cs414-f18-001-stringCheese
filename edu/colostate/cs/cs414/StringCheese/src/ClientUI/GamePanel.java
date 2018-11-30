package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JPanel{

	private GameTile[][] gameTiles;
	private UIController controller;
	private ActiveGamesController activeGamesController;
	private GameTile selectedTile;
	JPanel gameBoard, games;
	JComboBox activeGames;

	public GamePanel(){
		gameTiles = new GameTile[7][7];

		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(7,7));
		gameBoard.setPreferredSize(new Dimension(600,600));
        colorBoard();
		this.add(gameBoard);

	}

	public void addActiveGames(){

		activeGames = new JComboBox(activeGamesController.populateActiveGames().toArray());
		activeGames.setVisible(true);
		activeGames.addActionListener(activeGamesController);
		this.add(activeGames);
	   /*
	     if(games != null && userName != null) {
            //Pair<OpponentName, StartTime>
            ArrayList<Pair<String, String>> gameList = new ArrayList<>();
            for (Game game : games) {
                if (userName.equalsIgnoreCase(game.getHost())) {
                    //
                    gameList.add(new Pair<>(game.getInvitee(), game.getStartTime()));
                } else {
                    gameList.add(new Pair<>(game.getHost(), game.getStartTime()));
                }
            }
            activeGames = new JComboBox(gameList.toArray());
            this.repaint();
        }
        else{
            System.out.println("Null values");
        }
        //activeGames.addActionListener(controller);
        */
    }

    private void colorBoard() {
        for(int row = 0; row < 7;row++){
            for(int col =0; col < 7;col++){
                gameTiles[row][col] = new GameTile(row,col);
                GameTile currentPanel = gameTiles[row][col];

                if(row >= 2 && col >=2 && row < 5 && col <5){

                }
                else if(col %2 == 0 && row %2 == 0){
                    currentPanel.setBackground(new Color(139,69,19));
                    currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else if(col %2 !=0 && row %2 != 0){
                    currentPanel.setBackground(new Color(139,69,19));
                    currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else{
                    currentPanel.setBackground(new Color(210,180,140));
                    currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                gameBoard.add(currentPanel);
            }
        }
    }
    public void displayState(){
		//repaintBorders();
		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				if(row >= 2 && col >=2 && row < 5 && col <5){

				}else{
					String position = gameTiles[row][col].getPosition();
					String type = controller.getType(position);
					drawPiece(position,type);
				}

			}
		}
	}

	private void drawPiece(String position, String type) {
		JPanel tile = gameTiles[getRow(position)][getCol(position)];
		JLabel imageLabel = new JLabel();
		ImageIcon piece = null;
		if(type.equalsIgnoreCase("blackpawn")){
			piece = new ImageIcon("UIresources/blackPawn.png");
		}
		else if(type.equalsIgnoreCase("whitepawn")){
			piece = new ImageIcon("UIresources/whitePawn.png");
		}
		else if(type.equalsIgnoreCase("blackking")){
			piece = new ImageIcon("UIresources/blackKing.png");
		}
		else if (type.equalsIgnoreCase("whiteking")){
			piece = new ImageIcon("UIresources/whiteKing.png");
		} else if (type.equalsIgnoreCase("blackrook")) {
			piece = new ImageIcon("UIresources/blackRook.png");
		}
		else if (type.equalsIgnoreCase("whiterook")) {
			piece = new ImageIcon("UIresources/whiteRook.png");
		}
		else if (type.equalsIgnoreCase("blackbishop")) {
			piece = new ImageIcon("UIresources/blackBishop.png");
		}
		else if (type.equalsIgnoreCase("whitebishop")) {
			piece = new ImageIcon("UIresources/whiteBishop.png");
		}
		else if(type.equalsIgnoreCase("blank")){
			tile.removeAll();
			tile.revalidate();
			tile.repaint();
		}

		if(piece == null){

		}
		else{
			tile.removeAll();
			Image pieceImage = piece.getImage(); // transform it
			pieceImage = pieceImage.getScaledInstance(85, 80,  Image.SCALE_SMOOTH);
			ImageIcon newPiece = new ImageIcon(pieceImage);
			imageLabel.setIcon(newPiece);
			repaintBorders();
			tile.add(imageLabel,BorderLayout.CENTER);
			tile.revalidate();
			tile.repaint();
		}


	}

	public void setUIController(UIController controller){
		this.controller = controller;

		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				gameTiles[row][col].addMouseListener(controller);
			}
		}
	}

	public void setActiveGamesController(ActiveGamesController controller){
		this.activeGamesController = controller;
	}

	public void displayValidMoves(HashSet<String> moves){
		repaintBorders();
		for (String move : moves) {
			GameTile tile = gameTiles[getRow(move)][getCol(move)];
			tile.setBackground(Color.GREEN);
			//tile.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			tile.revalidate();
			tile.repaint();
		}
	}

	private void repaintBorders(){
		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				if(row >= 2 && col >=2 && row < 5 && col <5){

				}else{
					if((col %2 == 0 && row %2 == 0) || (col %2 !=0 && row %2 != 0)){
						gameTiles[row][col].setBackground(new Color(139,69,19));
					}else{
						gameTiles[row][col].setBackground(new Color(210,180,140));
					}
					//gameTiles[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			}
		}
	}

	private int getRow(String position) {
		return 7 - Character.getNumericValue(position.charAt(1));
	}

	private int getCol(String position){
		return position.charAt(0) - 'a';

	}
}
