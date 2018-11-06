package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{

	UIController controller;

	public MenuPanel(UIController controller){
		this.setLayout(new BorderLayout());
		this.controller = controller;

		JLabel title = new JLabel("Rollerball",JLabel.CENTER);
		title.setMaximumSize(new Dimension(2000,200));
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,100));
		this.add(title,BorderLayout.PAGE_START);

		JPanel menuArea = new JPanel(new GridLayout(4,1));
		menuArea.setSize(200,90);
		menuArea.setMaximumSize(new Dimension(200,300));

		JButton menu = new JButton("Menu");
		menu.setMaximumSize(new Dimension(200,40));
		menuArea.add(menu);

		JButton profile = new JButton("Profile");
		profile.setMaximumSize(new Dimension(200,30));
		menuArea.add(profile);

		JButton game = new JButton("Game");
		game.setMaximumSize(new Dimension(200,30));
		game.addActionListener(this.controller);
		menuArea.add(game);
		
		this.add(menuArea,BorderLayout.LINE_START);
	}

}
