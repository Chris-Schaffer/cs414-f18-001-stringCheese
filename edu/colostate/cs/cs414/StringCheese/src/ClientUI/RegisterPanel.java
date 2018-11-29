package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
	private UIController controller;
	private JTextField email;
	private JTextField nickname;
	private JPasswordField password;
	private JLabel successMsgLabel;
	private JLabel failureMsgLabel;

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
		email.setPreferredSize(new Dimension(200,40));
		this.add(email,BorderLayout.CENTER);
		this.email = email;

		JTextField nickname = new JTextField("nickname:",20);
		nickname.setPreferredSize(new Dimension(2000,40));
		this.add(nickname,BorderLayout.CENTER);
		this.nickname = nickname;

		JPasswordField password = new JPasswordField("password:",20);
		password.setPreferredSize(new Dimension(2000,40));
		this.add(password,BorderLayout.CENTER);
		this.password = password;

		JButton register = new JButton("register");
		register.setPreferredSize(new Dimension(200,40));
		register.addActionListener(this.controller);
		this.add(register, BorderLayout.PAGE_END);

		successMsgLabel = new JLabel("Registration Successful");
		successMsgLabel.setVisible(false);
		this.add(successMsgLabel, BorderLayout.CENTER);

		failureMsgLabel = new JLabel("Registration Failure");
		failureMsgLabel.setVisible(false);
		this.add(failureMsgLabel, BorderLayout.CENTER);

		JButton login = new JButton("to login");
		login.setPreferredSize(new Dimension(200,40));
		login.addActionListener(this.controller);
		this.add(login, BorderLayout.PAGE_END);


	}

	public JTextField getEmail(){
		return email;
	}

	public JTextField getNickname(){
		return nickname;
	}

	public JPasswordField getPassword(){
		return password;
	}

	public void showSuccessMsg(){
		failureMsgLabel.setVisible(false);
		successMsgLabel.setVisible(true);
		MainWindow.infoBox("Registration Successful\n" +
				"Logging in...", "");
	}

	public void showFailureMsg(){
		failureMsgLabel.setVisible(true);
		MainWindow.infoBox("Registration Failed\n" +
				"Email or Nickname may already be in use.\n" +
				"Nickname and Password must be at least 5 characters long.","");
	}

}
