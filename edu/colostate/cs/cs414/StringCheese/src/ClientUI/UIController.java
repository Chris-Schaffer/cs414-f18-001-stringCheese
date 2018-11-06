package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
	private String selectedPosition;

	public UIController(MainWindow window){
		this.window = window;
		gameFacade = new GameFacade();

	}

	public void initializeScreen(){
		startPanel = new StartPanel(this);
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
		gamePanel.displayState();
		window.remove(menuPanel);
		window.add(gamePanel);
		window.revalidate();
		window.repaint();
	}

	//right now just switches panels, will eventually call facade for registration
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
	//right now just switches panels, will eventually call facade for login
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

			if(((LineBorder)tile.getBorder()).getLineColor() == Color.GREEN){
				gameFacade.move(selectedPosition, tile.getPosition());
				gamePanel.displayState();
			}
			else{
				selectedPosition = tile.getPosition();
				HashSet<String> moves = gameFacade.getValidMoves(selectedPosition);
				gamePanel.displayValidMoves(moves);
			}

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

	public String getType(String position) {
		return gameFacade.getType(position);
	}
}
