package edu.colostate.cs.cs414.StringCheese.src.UI;

public class GameClient {


    public static void main(String[] args){
       MainWindow window = new MainWindow();
        UIController controller = new UIController(window);
        controller.initializeScreen();
        window.revalidate();
        window.repaint();

    }
}
