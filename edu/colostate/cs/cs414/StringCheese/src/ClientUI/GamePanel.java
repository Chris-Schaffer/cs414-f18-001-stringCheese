package edu.colostate.cs.cs414.StringCheese.src.ClientUI;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JPanel{

	private GameTile[][] gameTiles;
	private UIController controller;
	private ActiveGamesController activeGamesController;
	private JoinGameController joinGameController;
	private JTextField joinGameId;
	private JButton joinGameButton;
	private GameTile selectedTile;
	JPanel gameBoard, games;
	JComboBox<String> activeGames;
	private static final Color lightBrown = new Color(210,180,140);
	private static final Color darkBrown = new Color(139,69,19);

	public GamePanel(UIController uiController){
		this.controller = uiController;
		//this.setLayout(new BorderLayout());

		JPanel menuArea = new JPanel(new GridLayout(4,1));
		menuArea.setSize(200,90);
		menuArea.setMaximumSize(new Dimension(200,300));

		JButton profile = new JButton("Profile");
		profile.setMaximumSize(new Dimension(200,40));
		profile.addActionListener(this.controller);
		menuArea.add(profile);

		JButton game = new JButton("Games");
		game.setMaximumSize(new Dimension(200,30));
		game.addActionListener(this.controller);
		menuArea.add(game);

		JButton invitation = new JButton("Send Invitation/Create Game");
		invitation.setMaximumSize(new Dimension(200,30));
		invitation.addActionListener(this.controller);
		menuArea.add(invitation);

		JButton logout = new JButton("Log Out");
		logout.setMaximumSize(new Dimension(200,30));
		logout.addActionListener(this.controller);
		menuArea.add(logout);

		this.add(menuArea,BorderLayout.LINE_START);
		gameTiles = new GameTile[7][7];
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(7,7));
		gameBoard.setPreferredSize(new Dimension(600,600));
        colorBoard();
		this.add(gameBoard);
	}

	public void addActiveGames(){
		activeGames = new JComboBox(activeGamesController.populateActiveGames().toArray());
		activeGames.setVisible(true);
		activeGames.addActionListener(activeGamesController);
		this.add(activeGames);
    }

    public void repopulateActiveGames(){
		activeGames.removeAllItems();
		ArrayList<String> games = activeGamesController.populateActiveGames();

		for(String game: games){
			activeGames.addItem(game);
		}
		activeGames.revalidate();
		activeGames.repaint();
	}

    public void addJoinGame(JoinGameController joinGameController){
		this.joinGameController = joinGameController;
		joinGameId = new JTextField("Game ID:",10);
		this.add(joinGameId,BorderLayout.PAGE_END);

		joinGameButton = new JButton("Join Game");
		joinGameButton.addActionListener(joinGameController);
		this.add(joinGameButton,BorderLayout.PAGE_END);
	}

    private void colorBoard() {
        for(int row = 0; row < 7;row++){
            for(int col =0; col < 7;col++){
                gameTiles[row][col] = new GameTile(row,col);
                GameTile currentPanel = gameTiles[row][col];

                if(row >= 2 && col >=2 && row < 5 && col <5){

                }
                else if(col %2 == 0 && row %2 == 0){
                    currentPanel.setBackground(darkBrown);
                    currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else if(col %2 !=0 && row %2 != 0){
                    currentPanel.setBackground(darkBrown);
                    currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else{
                    currentPanel.setBackground(lightBrown);
                    currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                gameBoard.add(currentPanel);
            }
        }
    }
    public void displayState(){
		//repaintBorders();
		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				if(row >= 2 && col >=2 && row < 5 && col <5){

				}else{
					String position = gameTiles[row][col].getPosition();
					String type = controller.getType(position);
					drawPiece(position,type);
				}

			}
		}
	}

	private void drawPiece(String position, String type) {
		JPanel tile = gameTiles[getRow(position)][getCol(position)];
		JLabel imageLabel = new JLabel();
		ImageIcon piece = null;
		if(type.equalsIgnoreCase("blackpawn")){
			piece = new ImageIcon("UIresources/blackPawn.png");
		}
		else if(type.equalsIgnoreCase("whitepawn")){
			piece = new ImageIcon("UIresources/whitePawn.png");
		}
		else if(type.equalsIgnoreCase("blackking")){
			piece = new ImageIcon("UIresources/blackKing.png");
		}
		else if (type.equalsIgnoreCase("whiteking")){
			piece = new ImageIcon("UIresources/whiteKing.png");
		} else if (type.equalsIgnoreCase("blackrook")) {
			piece = new ImageIcon("UIresources/blackRook.png");
		}
		else if (type.equalsIgnoreCase("whiterook")) {
			piece = new ImageIcon("UIresources/whiteRook.png");
		}
		else if (type.equalsIgnoreCase("blackbishop")) {
			piece = new ImageIcon("UIresources/blackBishop.png");
		}
		else if (type.equalsIgnoreCase("whitebishop")) {
			piece = new ImageIcon("UIresources/whiteBishop.png");
		}
		else if(type.equalsIgnoreCase("blank")){
			tile.removeAll();
			tile.revalidate();
			tile.repaint();
		}

		if(piece == null){

		}
		else{
			tile.removeAll();
			Image pieceImage = piece.getImage(); // transform it
			pieceImage = pieceImage.getScaledInstance(85, 80,  Image.SCALE_SMOOTH);
			ImageIcon newPiece = new ImageIcon(pieceImage);
			imageLabel.setIcon(newPiece);
			repaintBorders();
			tile.add(imageLabel,BorderLayout.CENTER);
			tile.revalidate();
			tile.repaint();
		}


	}

	public void setUIController(){

		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				gameTiles[row][col].addMouseListener(controller);
			}
		}
	}

	public void setActiveGamesController(ActiveGamesController controller){
		this.activeGamesController = controller;
	}

	public void displayValidMoves(HashSet<String> moves){
		repaintBorders();
		for (String move : moves) {
			GameTile tile = gameTiles[getRow(move)][getCol(move)];
			tile.setBackground(Color.GREEN);
			//tile.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			tile.revalidate();
			tile.repaint();
		}
	}

	private void repaintBorders(){
		for(int row = 0; row < 7;row++){
			for(int col =0; col < 7;col++){
				if(row >= 2 && col >=2 && row < 5 && col <5){

				}else{
					if((col %2 == 0 && row %2 == 0) || (col %2 !=0 && row %2 != 0)){
						gameTiles[row][col].setBackground(darkBrown);
					}else{
						gameTiles[row][col].setBackground(lightBrown);
					}
					//gameTiles[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			}
		}
	}

	public JTextField getJoinGameId(){
		return joinGameId;
	}

	private int getRow(String position) {
		return 7 - Character.getNumericValue(position.charAt(1));
	}

	private int getCol(String position){
		return position.charAt(0) - 'a';

	}

	public void showSuccessMsg(){
		MainWindow.infoBox("Joined Game!\n" +
				"Press the game button to the left to reload page.", "");
	}

	public void showFailureMsg(){
		MainWindow.infoBox("Failure.\n" +
				"It looks like someone else is playing this game.\n" +
				"To create a game choose create game to the left.\n","");
	}
}
