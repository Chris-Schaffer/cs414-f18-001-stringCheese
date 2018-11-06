package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;



public class GameTile extends JPanel {
    private int row;
    private int col;

    public GameTile(int row, int col){
        this.row = row;
        this.col = col;
    }

   public String getPosition(){
        String column;
        String stringRow;

        switch (col){
            case 0: column = "a";break;
            case 1: column = "b";break;
            case 2: column = "c";break;
            case 3: column = "d";break;
            case 4: column = "e";break;
            case 5: column = "f";break;
            case 6: column = "g";break;
            default: column = "";
        }
        stringRow = Integer.toString(7 - row);
        return column + stringRow;
   }
}
