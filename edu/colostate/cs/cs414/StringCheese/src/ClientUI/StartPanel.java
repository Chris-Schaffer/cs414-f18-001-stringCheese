package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel{
    public StartPanel(){

        JLabel title = new JLabel("Rollerball",JLabel.CENTER);
        title.setPreferredSize(new Dimension(2000,400));
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,100));
        this.add(title,BorderLayout.PAGE_START);

        JButton login = new JButton("login");
        login.setPreferredSize(new Dimension(200,100));
        this.add(login, BorderLayout.PAGE_END);

        JButton register = new JButton("register");
        register.setPreferredSize(new Dimension(200,100));
        this.add(register, BorderLayout.PAGE_END);
    }
}
