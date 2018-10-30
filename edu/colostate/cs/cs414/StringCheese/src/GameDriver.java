package edu.colostate.cs.cs414.StringCheese.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class GameDriver {


    public static void main(String[] args){
        ChessBoard board = new ChessBoard();
        board.initialize();

        System.out.println(board.toString());
        System.out.println("White Player goes first" +
                "\nEnter \"Select\" <position> to show the valid moves of a piece " +
                "\nExample: \"select A4\"");

        Scanner scan = new Scanner(System.in);
        String[] com = new String("select c2").split(" ");
        String[] command = scan.nextLine().split(" ");
        HashSet<String> moves = new HashSet<>();

while(!command[0].equals("QUIT")) {
    if (command[0].equalsIgnoreCase("select")) {
        moves = board.selectPiece(command[1]);

    }
    else if(command[0].equalsIgnoreCase("move")){
        try {
            board.move(command[1],command[2]);
            System.out.println(board);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
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
}
