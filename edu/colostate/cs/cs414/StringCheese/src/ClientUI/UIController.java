package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import edu.colostate.cs.cs414.StringCheese.src.Game;
import edu.colostate.cs.cs414.StringCheese.src.GameFacade;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashSet;

public class UIController implements ActionListener, MouseListener {

	private MainWindow window;
	private StartPanel startPanel;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private  MenuPanel menuPanel;
	private GamePanel gamePanel;
	private ProfilePanel profilePanel;
	private ProfileController profileController;
	private GameFacade gameFacade;
	private String selectedPosition;
	private ActiveGamesController activeGamesController;
	private JoinGameController joinGameController;
	private InvitationPanel invitationPanel;
	private InvitationPanelController invitationPanelController;

	public UIController(MainWindow window){
		this.window = window;
		gameFacade = new GameFacade();
		this.activeGamesController = new ActiveGamesController(gameFacade);
		this.joinGameController = new JoinGameController(gameFacade);
		this.gamePanel = new GamePanel(this);
		joinGameController.setGamePanel(gamePanel);
		gamePanel.setUIController();
		gamePanel.setActiveGamesController(activeGamesController);
		profileController = new ProfileController(gameFacade);
		profilePanel= new ProfilePanel(profileController,this);
		invitationPanelController = new InvitationPanelController(gameFacade);
		invitationPanel = new InvitationPanel(invitationPanelController,this);
	}

	public void initializeScreen(){
		startPanel = new StartPanel(this);
		window.add(startPanel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("go to login")){
			goToLogin(startPanel);
		}
		else if(e.getActionCommand().equalsIgnoreCase("to login")){
			goToLogin(registerPanel);
		}
		else if(e.getActionCommand().equalsIgnoreCase("login")){
			login();
		}
		else if(e.getActionCommand().equalsIgnoreCase("go to register")){
			goToRegister(startPanel);
		}
		else if(e.getActionCommand().equalsIgnoreCase("register an account")){
			goToRegister(loginPanel);
		}
		else if(e.getActionCommand().equalsIgnoreCase("register")){
			register();
		}
		else if (e.getActionCommand().equalsIgnoreCase("games")){
			game();
		}
        else if (e.getActionCommand().equalsIgnoreCase("profile")){
            profile();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Send Invitation/Create Game")){
        	invitationPage();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Log Out")){
			logout();
		}

	}

	private void logout() {
		window.getContentPane().removeAll();
		window.add(loginPanel);
		window.revalidate();
		window.repaint();
	}

	private void invitationPage() {
		window.getContentPane().removeAll();
		window.add(invitationPanel);
		window.revalidate();
		window.repaint();
	}

	private void profile() {
	    window.getContentPane().removeAll();
	    window.add(profilePanel);
        window.revalidate();
        window.repaint();
    }

    private void game() {
		window.getContentPane().removeAll();
		gamePanel.repopulateActiveGames();
		//gamePanel.displayState();
		window.add(gamePanel);
		window.revalidate();
		window.repaint();
	}

	//if registration is successful it will display popup success message then go to login page
	private void register() {
		String email = registerPanel.getEmail().getText();
		String nickname = registerPanel.getNickname().getText();
		char[] password = registerPanel.getPassword().getPassword();

		String passablePassword = "";
		for(char c : password){
			passablePassword += c;
		}

		boolean loginSuccessful = gameFacade.register(nickname,email,passablePassword);


		if(loginSuccessful){
			registerPanel.showSuccessMsg();
			goToLogin(registerPanel);
			//goToMenu("register");
		}
		else {
			registerPanel.showFailureMsg();
		}

	}

	private void goToRegister(JPanel fromPanel) {
		window.remove(fromPanel);
		registerPanel = new RegisterPanel(this);
		window.add(registerPanel);
		window.revalidate();
		window.repaint();
	}


	private void goToLogin(JPanel fromPanel){
		//window.remove(startPanel);
		window.remove(fromPanel);
		loginPanel = new LoginPanel(this);
		window.add(loginPanel);
		window.revalidate();
		window.repaint();
	}

	//right now just switches panels, will eventually call facade for login
	private void login(){
		Component comps[] = loginPanel.getComponents();
		String nickname;
		char[] password;
		boolean logInSuccessful;

		nickname = loginPanel.getNickname().getText();
		password = loginPanel.getPassword().getPassword();
		String passablePassword = "";
		for(char c : password){
			passablePassword += c;
		}

		logInSuccessful = gameFacade.login(nickname,passablePassword);

		if(logInSuccessful){
			loginPanel.showSuccessMsg();
			goToMenu("login");
		}

		else {
			//add some sort off error text
			loginPanel.showFailureMsg();
		}

	}




	private void goToMenu(String panelType){
		gamePanel.addActiveGames();
		gamePanel.addJoinGame(joinGameController);
		invitationPanel.initializeMenu();
		menuPanel = new MenuPanel(this);
		if(panelType.equalsIgnoreCase("login")){
			window.remove(loginPanel);
		}
		else{
			window.remove(registerPanel);
		}
		window.add(menuPanel);
		window.revalidate();
		window.repaint();
	}





	@Override
	public void mouseClicked(MouseEvent e) {
		Component c = e.getComponent();
		GameTile tile;
		String specialMove = "";
		String position;
		if(c instanceof GameTile){
			tile = (GameTile) c;
			position = tile.getPosition();
			if((tile.getBackground()) == Color.GREEN){
				specialMove = gameFacade.move(selectedPosition, position);
				gamePanel.displayState();
			}
			else{
				selectedPosition = tile.getPosition();
				HashSet<String> moves = gameFacade.getValidMoves(selectedPosition);
				gamePanel.displayValidMoves(moves);
			}

			if(specialMove.equalsIgnoreCase("promotion")){
				String choice= "";
				//displayPopUp
				Object[] options = {"Bishop",
						"Rook"};
				int n = JOptionPane.showOptionDialog(window,
						"Which piece would you like to promote to?",
						"Promotion Choice",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);
				if(n == JOptionPane.YES_OPTION){
					choice = "Bishop";
				}
				else{
					choice = "Rook";
				}
				gameFacade.promote(position,choice);
				String str=gameFacade.getType(position);
				gamePanel.drawPiece(position,str);
				gameFacade.updateDBGameState();

			}
			else if(specialMove.equalsIgnoreCase("winner") || specialMove.equalsIgnoreCase("checkmate")){
				MainWindow.infoBox("Congratulations, You Won!\n" +
						"Head over to your profile page to see your latest results.\n" +
						"You can create a new game to the left or click the games button to play any remaining games.",specialMove);
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
