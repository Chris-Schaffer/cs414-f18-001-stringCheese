package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
	UIController controller;
	public RegisterPanel(UIController controller){
		this.controller = controller;
		JLabel title = new JLabel("If You Don't Have An Account Register Here:",JLabel.CENTER);
		title.setPreferredSize(new Dimension(2000,200));
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,50));
		this.add(title,BorderLayout.PAGE_START);

		JLabel registerInfo = new JLabel("Enter Your email, nickname, and password below and click register",JLabel.CENTER);
		registerInfo.setPreferredSize(new Dimension(2000,200));
		registerInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
		this.add(registerInfo,BorderLayout.LINE_START);

		JTextField email = new JTextField("email:",20);
		email.setPreferredSize(new Dimension(2000,40));
		this.add(email,BorderLayout.CENTER);

		JTextField nickname = new JTextField("nickname:",20);
		nickname.setPreferredSize(new Dimension(2000,40));
		this.add(nickname,BorderLayout.CENTER);

		JPasswordField password = new JPasswordField("password:",20);
		password.setPreferredSize(new Dimension(2000,40));
		this.add(password,BorderLayout.CENTER);

		JButton register = new JButton("register");
		register.setPreferredSize(new Dimension(200,40));
		register.addActionListener(this.controller);
		this.add(register, BorderLayout.PAGE_END);

	}

}
