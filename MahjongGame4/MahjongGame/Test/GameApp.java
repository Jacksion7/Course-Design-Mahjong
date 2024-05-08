package Test;

import UI.MenuScreen;
import UI.PlayScreen;

public class GameApp {

    public static void main(String[] args) {
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.setVisible(true);
        PlayScreen frame = new PlayScreen();
        frame.setVisible(true);
    }
}
