package Mahjong;

import Item.Game2;
import UI.PlayerListener;
import ucd.comp2011j.engine.Game;

import static UI.Constant.SCREEN_HEIGHT;
import static UI.Constant.SCREEN_WIDTH;

public class MahjongGameManager implements Game ,Game2{
    private MahjongGame mahjongGame;
    private int playerLives;
    private int playerScore;


    private PlayerListener listener;
    // zzq add a parameter to the  MahjongGameManager, for connect the UI screens together
    public MahjongGameManager(PlayerListener listener) {
        this.listener = listener;
        //mahjongGame = new MahjongGame();
    }


    public void startGame() {
        System.out.println("游戏开始！");
        mahjongGame.playGame();
        System.out.println("游戏结束！");
    }


    @Override
    public void playGame() {
        startGame();
    }

    // use the engine in thr libs, hence zzq need implement the  ucd.comp2011j.engine.Game;
    // instead of Item.Game;
    // it may cause some problem, we need a discussion
    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    @Override
    public int getLives() {
        return playerLives;
    }

    @Override
    public void updateGame() {

    }

    @Override
    public boolean isPaused() {
        return false;
    }

    @Override
    public void checkForPause() {

    }

    @Override
    public void startNewGame() {

    }

    @Override
    public boolean isLevelFinished() {
        return false;
    }

    @Override
    public boolean isPlayerAlive() {
        return false;
    }

    @Override
    public void resetDestroyedPlayer() {

    }

    @Override
    public void moveToNextLevel() {

    }

    @Override
    public boolean isGameOver() {
        return mahjongGame.isGameOver();
    }

    @Override
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    @Override
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }


    public static void main(String[] args) {
        MahjongGame mahjongGame1 = new MahjongGame();
        //mahjongGame1.dealTiles();
        mahjongGame1.playGame();

//        PlayerListener playerListener = new PlayerListener();
//        MahjongGameManager gameManager = new MahjongGameManager(playerListener);
//        gameManager.playGame(); // 调用抽象方法
    }
}