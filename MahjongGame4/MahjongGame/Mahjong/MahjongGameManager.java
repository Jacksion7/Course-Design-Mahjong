package Mahjong;

import Item.AbstractMahjongGame;
import Item.Game2;
import Players.Player;
import UI.GameScreen;
import UI.PlayerListener;
import ucd.comp2011j.engine.Game;

import java.util.List;

import static UI.Constant.SCREEN_HEIGHT;
import static UI.Constant.SCREEN_WIDTH;

public class MahjongGameManager extends AbstractMahjongGame implements Game ,Game2   {
    private MahjongGame mahjongGame;
    private int playerLives;
    private int playerScore;
    public static List<MahjongTile> Player_initial_dealTiles;// connect with Game Screen

    public static Player player;
    private GameScreen gameScreen;
    public static boolean ifDealTiles= false;// connect with Game Screen

    private PlayerListener listener;

    public MahjongGameManager(PlayerListener listener) {
        this.listener = listener;
        mahjongGame = new MahjongGame();
        gameScreen = new GameScreen();
    }

    protected void dealTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                if (i == 0) {
                    players[0].drawTile(tile);
                } else {
                    computers[i - 1].drawTile(tile);
                }
            }
        }
        
        ifDealTiles=true;
        Player_initial_dealTiles=players[0].hand;
        player=players[0];

    }

    public List<MahjongTile> getPlayer_initial_dealTiles() {
        return Player_initial_dealTiles;
    }

    public boolean isIfDealTiles() {
        return ifDealTiles;
    }

    public static Player getPlayer() {
        return player;
    }

    public void startGame() {
        System.out.println("游戏开始！");
        startGameScreen();
        //mahjongGame.playGame();
        // mahjongGame.dealTiles();
        dealTiles();
        //gameScreen.paintTiles(mahjongGame);
        System.out.println(isGameOver());
        while (!isGameOver()) {
            mahjongGame.testDrawAndDiscard();
            mahjongGame.playComputerTurn();
        }
        System.out.println("游戏结束！");
    }

    public void startGameScreen(){
        gameScreen.setVisible(true);
    }

    @Override
    public void playGame() {

    }


//    @Override
//    public void playGame() {
//        startGame();
//    }

//    public void playGame() {
//        mahjongGame.dealTiles();
//        while (!isGameOver()) {
//            mahjongGame.testDrawAndDiscard();
//            mahjongGame.playComputerTurn();
//        }
//    }



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
    protected void testDrawAndDiscard() {

    }

    @Override
    protected void updatePlayerHands() {

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
//        MahjongGame mahjongGame1 = new MahjongGame();
//         mahjongGame1.dealTiles();
//
        PlayerListener playerListener = new PlayerListener();
        MahjongGameManager gameManager = new MahjongGameManager(playerListener);
        gameManager.startGame(); // 调用抽象方法
    }
}