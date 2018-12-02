package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;


public class MainWindow extends JFrame {

    public MainWindow(){
        super("RollerBall");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,800);
        setVisible(true);
    }


    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
