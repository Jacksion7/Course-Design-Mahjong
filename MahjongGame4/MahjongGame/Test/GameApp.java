package Test;

import Viewers.MenuScreen;
import Viewers.PlayScreen;

public class GameApp {

    public static void main(String[] args) {
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.setVisible(true);
        PlayScreen frame = new PlayScreen();
        frame.setVisible(true);
    }
}
