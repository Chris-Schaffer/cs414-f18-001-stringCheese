package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIController implements ActionListener{

	private MainWindow window;
	private StartPanel startPanel;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private  MenuPanel menuPanel;
	private GamePanel gamePanel;

	public UIController(MainWindow window){
		this.window = window;
		startPanel = new  StartPanel(this);
		window.add(startPanel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("go to login")){
			goToLogin();
		}
		else if(e.getActionCommand().equalsIgnoreCase("login")){
			login();
		}
		else if(e.getActionCommand().equalsIgnoreCase("go to register")){
			goToRegister();
		}
		else if(e.getActionCommand().equalsIgnoreCase("register")){
			register();
		}

	}

	private void register() {
		menuPanel = new MenuPanel();
		window.remove(registerPanel);
		window.add(menuPanel);
		window.revalidate();
		window.repaint();
	}

	private void goToRegister() {
		registerPanel = new RegisterPanel(this);
		window.remove(startPanel);
		window.add(registerPanel);
		window.revalidate();
		window.repaint();
	}


	private void goToLogin(){
		window.remove(startPanel);
		loginPanel = new LoginPanel(this);
		window.add(loginPanel);
		window.revalidate();
		window.repaint();
	}

	private void login(){
		menuPanel = new MenuPanel();
		window.remove(loginPanel);
		window.add(menuPanel);
		window.revalidate();
		window.repaint();
	}

}
