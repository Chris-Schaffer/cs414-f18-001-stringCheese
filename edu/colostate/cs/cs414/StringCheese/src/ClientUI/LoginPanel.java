package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginPanel extends JPanel{
	private UIController controller;
	private JTextField nickname;//email;
	private JPasswordField password;

	public LoginPanel(UIController controller){
		this.controller = controller;
		JLabel title = new JLabel("If You Have An Account Login Here:",JLabel.CENTER);
		title.setPreferredSize(new Dimension(2000,200));
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,50));
		this.add(title,BorderLayout.PAGE_START);

		JLabel loginInfo = new JLabel("Enter Your email and password below and click login",JLabel.CENTER);
		loginInfo.setPreferredSize(new Dimension(2000,200));
		loginInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
		this.add(loginInfo,BorderLayout.LINE_START);

		JTextField nickname = new JTextField("nickname:",20);
		nickname.setName("nickname");
		nickname.setPreferredSize(new Dimension(2000,40));
		this.add(nickname, BorderLayout.CENTER);
		this.nickname = nickname;
		//email.setName("email");
		//email.setPreferredSize(new Dimension(2000,40));
		//this.add(email,BorderLayout.CENTER);
		//this.email = email;

		JPasswordField password = new JPasswordField("password:",20);
		password.setName("password");
		password.setPreferredSize(new Dimension(2000,40));
		this.add(password,BorderLayout.CENTER);
		this.password = password;

		JButton login = new JButton("login");
		login.setPreferredSize(new Dimension(200,40));
		login.addActionListener(this.controller);
		this.add(login, BorderLayout.CENTER);


		JButton register = new JButton("register an account");
		register.setPreferredSize(new Dimension(200,40));
		register.addActionListener(this.controller);
		this.add(register, BorderLayout.PAGE_END);
	}

	/*
	public JTextField getEmail() {
		return email;
	}
	*/
	public JPasswordField getPassword(){
		return password;
	}

	public JTextField getNickname() {
		return nickname;
	}
}
