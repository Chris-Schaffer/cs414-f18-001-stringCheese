package edu.colostate.cs.cs414.StringCheese.src.ClientUI;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel{
    UIController controller;

    public StartPanel(UIController controller){
        this.controller = controller;

        JLabel title = new JLabel("Rollerball",JLabel.CENTER);
        title.setPreferredSize(new Dimension(2000,400));
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,100));
        this.add(title,BorderLayout.PAGE_START);

        JButton login = new JButton("go to login");
        login.setPreferredSize(new Dimension(200,100));
        login.addActionListener(this.controller);
        this.add(login, BorderLayout.PAGE_END);

        JButton register = new JButton("go to register");
        register.setPreferredSize(new Dimension(200,100));
        register.addActionListener(this.controller);
        this.add(register, BorderLayout.PAGE_END);
    }
}
