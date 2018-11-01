package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import com.sun.tools.javac.Main;
import edu.colostate.cs.cs414.StringCheese.src.ChessBoard;
import edu.colostate.cs.cs414.StringCheese.src.IllegalPositionException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class GameClient {

    public void mainScreen(Scanner scan){
        int i = 0;
        while (i < 20){
            System.out.println();
            i++;
        }
        System.out.println("Main Menu: \nOptions: ");
        System.out.println("1: Create New Game");
        System.out.println("Enter a menu number");
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("1")){
            gameScreen(scan);
        }

    }

    public void loginScreen(Scanner scan){
        System.out.println("Please enter your email and password"+ "\n Enter Email: ");
        String email = scan.nextLine();
        System.out.println("Enter your Password: ");
        String password = scan.nextLine();

        if(email.equalsIgnoreCase("tstroup@rams.colostate.edu" ) && password.equalsIgnoreCase("password")){
            mainScreen(scan);
        }
    }

    public void gameScreen(Scanner scan){
        ChessBoard board = new ChessBoard();
        board.initialize();

        System.out.println(board.toString());
        System.out.println("White Player goes first" +
                "\nEnter \"Select\" <position> to show the valid moves of a piece " +
                "\nExample: \"select A4\"");

        String[] command = scan.nextLine().split(" ");
        HashSet<String> moves = new HashSet<>();
        while(!command[0].equals("QUIT")) {
            if (command[0].equalsIgnoreCase("select")) {
                moves = board.selectPiece(command[1]);

            }
            else if(command[0].equalsIgnoreCase("move")){
                    board.move(command[1],command[2]);
                    System.out.println(board);

            }

            if (moves.isEmpty()) {
                System.out.println("No valid moves for position: " + command[1]);
            } else {
                System.out.println(Arrays.toString(moves.toArray()));
            }

            System.out.println("Enter another command");
            command = scan.nextLine().split(" ");
        }
    }

    public static void main(String[] args){
        MainWindow window = new MainWindow();
        UIController controller = new UIController(window);

        /*
        Scanner scan = new Scanner(System.in);
        GameClient driver = new GameClient();
        driver.loginScreen(scan);*/
    }
}
