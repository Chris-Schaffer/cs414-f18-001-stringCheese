package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel{
	UIController controller;
	public LoginPanel(UIController controller){
		this.controller = controller;
		JLabel title = new JLabel("If You Have An Account Login Here:",JLabel.CENTER);
		title.setPreferredSize(new Dimension(2000,400));
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,50));
		this.add(title,BorderLayout.PAGE_START);

		JLabel loginInfo = new JLabel("Enter Your email and password below and click login",JLabel.CENTER);
		loginInfo.setPreferredSize(new Dimension(2000,200));
		loginInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
		this.add(loginInfo,BorderLayout.LINE_START);

		JTextField email = new JTextField("email:",20);
		email.setPreferredSize(new Dimension(2000,40));
		this.add(email,BorderLayout.CENTER);

		JPasswordField password = new JPasswordField("password:",20);
		password.setPreferredSize(new Dimension(2000,40));
		this.add(password,BorderLayout.CENTER);

		JButton login = new JButton("login");
		login.setPreferredSize(new Dimension(200,40));
		login.addActionListener(this.controller);
		this.add(login, BorderLayout.PAGE_END);
	}
}
