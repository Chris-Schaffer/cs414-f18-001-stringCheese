package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class GamePanel extends JPanel{

	GameTile[][] gameTiles;
	UIController controller;

	public GamePanel(){
		gameTiles = new GameTile[7][7];

		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(7,7));
		gameBoard.setPreferredSize(new Dimension(600,600));

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

		this.add(gameBoard);
	}

	public void displayState(){

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
		if(piece == null){

		}
		else{
			Image pieceImage = piece.getImage(); // transform it
			pieceImage = pieceImage.getScaledInstance(85, 80,  Image.SCALE_SMOOTH);
			ImageIcon newPiece = new ImageIcon(pieceImage);
			imageLabel.setIcon(newPiece);
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

	public void displayValidMoves(HashSet<String> moves){
		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				if(row >= 2 && col >=2 && row < 5 && col <5){

				}else{
					gameTiles[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			}
		}
		for (String move : moves) {
			GameTile tile = gameTiles[getRow(move)][getCol(move)];
			tile.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			tile.revalidate();
			tile.repaint();
		}
	}

	private int getRow(String position) {
		return 7 - Character.getNumericValue(position.charAt(1));
	}

	private int getCol(String position){
		return position.charAt(0) - 'a';

	}


}
