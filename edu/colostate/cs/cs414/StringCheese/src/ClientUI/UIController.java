package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class UIController implements ActionListener, MouseListener {

	private MainWindow window;
	private StartPanel startPanel;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private  MenuPanel menuPanel;
	private GamePanel gamePanel;
	private GameFacade gameFacade;

	public UIController(MainWindow window){
		this.window = window;
		startPanel = new  StartPanel(this);
		gameFacade = new GameFacade();
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
		else if (e.getActionCommand().equalsIgnoreCase("game")){
			game();
		}

	}

	private void game() {
		gamePanel = new GamePanel();
		gamePanel.setUIController(this);
		window.remove(menuPanel);
		window.add(gamePanel);
		window.revalidate();
		window.repaint();
	}

	private void register() {
		menuPanel = new MenuPanel(this);
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
		menuPanel = new MenuPanel(this);
		window.remove(loginPanel);
		window.add(menuPanel);
		window.revalidate();
		window.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component c = e.getComponent();
		GameTile tile;
		if(c instanceof GameTile){
			tile = (GameTile) c;
			HashSet<String> moves = gameFacade.getValidMoves(tile.getPosition());
			gamePanel.displayValidMoves(moves);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//do nothing for now
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//do nothing for now
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//do nothing for now
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//do nothing for now
	}
}
