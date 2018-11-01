package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

	JPanel[][] gameTiles;

	public GamePanel(){
		gameTiles = new JPanel[7][7];

		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(7,7));
		gameBoard.setPreferredSize(new Dimension(1000,1000));

		for(int i = 0; i < 7;i++){
			for(int j =0; j < 7;j++){
				gameTiles[i][j] = new JPanel();
				JPanel currentPanel = gameTiles[i][j];
				if(j %2 == 0 && i %2 == 0){
					currentPanel.setBackground(new Color(139,69,19));
				}
				else if(j %2 !=0 && i %2 != 0){
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
}
