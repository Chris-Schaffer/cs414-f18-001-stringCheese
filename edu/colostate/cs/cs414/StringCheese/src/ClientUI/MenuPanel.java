package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{

	UIController controller;

	public MenuPanel(UIController controller){
		this.setLayout(new BorderLayout());
		this.controller = controller;

		JLabel title = new JLabel("Rollerball",JLabel.CENTER);
		title.setPreferredSize(new Dimension(2000,200));
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,100));
		this.add(title,BorderLayout.PAGE_START);

		JPanel menuArea = new JPanel();
		menuArea.setPreferredSize(new Dimension(200,700));

		JButton menu = new JButton("Menu");
		menu.setPreferredSize(new Dimension(200,100));
		menuArea.add(menu,BorderLayout.CENTER);

		JButton profile = new JButton("Profile");
		profile.setPreferredSize(new Dimension(200,100));
		menuArea.add(profile,BorderLayout.CENTER);

		JButton game = new JButton("Game");
		game.setPreferredSize(new Dimension(200,100));
		game.addActionListener(this.controller);
		menuArea.add(game,BorderLayout.CENTER);


		this.add(menuArea,BorderLayout.LINE_START);
	}

}
