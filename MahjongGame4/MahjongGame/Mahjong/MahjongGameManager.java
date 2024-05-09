package Mahjong;

import Item.Game;

public class MahjongGameManager implements Game {
    private MahjongGame mahjongGame;

    public MahjongGameManager() {
        mahjongGame = new MahjongGame();
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

    @Override
    public boolean isGameOver() {
        return mahjongGame.isGameOver();
    }

    public static void main(String[] args) {
        MahjongGameManager gameManager = new MahjongGameManager();
        gameManager.playGame(); // 调用抽象方法
    }
}