package Controllers;

public class MahjongGameManager {

    private MahjongGame mahjongGame;

    public MahjongGameManager() {
        mahjongGame = new MahjongGame();
    }

    public void startGame() {
        System.out.println("游戏开始！");
        mahjongGame.playGame();
        System.out.println("游戏结束！");
    }

    public static void main(String[] args) {
        MahjongGameManager gameManager = new MahjongGameManager();
        gameManager.startGame();
    }
}
