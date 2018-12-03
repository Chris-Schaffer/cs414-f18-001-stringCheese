package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends MainPanel {

	UIController controller;

	public MenuPanel(UIController controller){
        super(controller);
	    this.setLayout(new BorderLayout());
		this.controller = controller;

		JLabel title = new JLabel("Rollerball",JLabel.CENTER);
		title.setMaximumSize(new Dimension(2000,200));
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,100));
		this.add(title,BorderLayout.PAGE_START);

	}

}
