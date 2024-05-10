package Test;


import Mahjong.MahjongGameManager;
import UI.*;
import ucd.comp2011j.engine.GameManager;
import ucd.comp2011j.engine.ScoreKeeper;

import javax.swing.*;

public class ApplicationStart {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setSize(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Mahjong Game");
        mainWindow.setLocationRelativeTo(null);

        PlayerListener playerListener = new PlayerListener();
        mainWindow.addKeyListener(playerListener);
        MenuListener menuListener = new MenuListener();
        mainWindow.addKeyListener(menuListener);

        MahjongGameManager game = new MahjongGameManager(playerListener);
        GameScreen gameScreen = new GameScreen(game);
        MenuScreen menuScreen = new MenuScreen();
        ScoreKeeper scoreKeeper = new ScoreKeeper("scores.txt");

        GameManager mmm = new GameManager(game, mainWindow, menuListener, menuScreen, new AboutScreen(), new ScoreScreen(scoreKeeper), gameScreen, scoreKeeper);
        mainWindow.setVisible(true);
        mmm.run();
    }
}
