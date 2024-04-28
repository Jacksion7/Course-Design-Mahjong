package Mahjong;

import GameRules.TouchDeal;
import Players.Player;
import Players.Computers;
import GameRules.Chow;


public class MahjongGame {
    private PlayerManager playerManager;
    private MahjongDeck deck;
    private Player[] players;
    private int playerIndex;
    private Computers[] computers;
    private TouchDeal touchDeal;
    private Chow chow;
    private static boolean gameOver = false;
    private MahjongTile discardTile;


    public MahjongGame () {
        playerManager = new PlayerManager();
        deck = new MahjongDeck();
        players = playerManager.getPlayers();
        computers = playerManager.getComputers();
        chow = new Chow(discardTile, players, computers, playerIndex);
    }

    public void playGame() {
        playerManager.dealTiles();
        while (!isGameOver()) {
            testDrawAndDiscard();
            playComputerTurn();
        }
    }

    //测试出摸牌
    public void testDrawAndDiscard() {
        System.out.println("你的回合：");
        updatePlayerHands();
        MahjongTile tile = deck.drawTile();
        System.out.println("摸到的牌: " + tile);
        players[0].drawTile(tile);
        touchDeal = new TouchDeal(players, computers);
        touchDeal.discardTile();
    }

    public void updatePlayerHands() {
        System.out.println("Player's hand:");
        players[0].displayHand();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println("Computer " + (i + 1) + "'s hand:");
            computers[i].displayHand();
            System.out.println();
        }
        playerIndex = (playerIndex + 1) % 4;

    }

    public boolean isGameOver() {
        for (int i = 1; i < 4; i++) {
            if (players[0].getHand().isEmpty()) {
                return gameOver = true; // 当任意一位玩家的手牌为空时游戏结束
            }
        }
        return gameOver = false;
    }

    public void playComputerTurn() {
        // 进入电脑自动抽牌发牌的回合
        for (int i = 0; i < 3; i++) {
            //这里本来想写从玩家2开始（i=2）但不知道为什么超出列表了
            System.out.println("电脑 " + (i + 1) + " 的回合：");
            updatePlayerHands();
            computers[i].computerPlay(i + 1);
            System.out.println();
        }
    }



}
