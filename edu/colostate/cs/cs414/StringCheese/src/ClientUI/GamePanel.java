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
				}
				else if(col %2 !=0 && row %2 != 0){
					currentPanel.setBackground(new Color(139,69,19));
				}
				else{
					currentPanel.setBackground(new Color(210,180,140));
				}
				gameBoard.add(currentPanel);
			}

		}

		this.add(gameBoard);
	}

	public void displayState(){
		JPanel p = gameTiles[0][3];
		JLabel label = new JLabel();
		ImageIcon king = new ImageIcon("UIresources/blackKing.png");
		Image image = king.getImage(); // transform it
		Image scaledKing = image.getScaledInstance(85, 80,  Image.SCALE_SMOOTH);
		ImageIcon newKing = new ImageIcon(scaledKing);
		label.setIcon(newKing);
		label.setSize(p.getSize());
		label.setVisible(true);
		p.add(BorderLayout.CENTER, label);

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
