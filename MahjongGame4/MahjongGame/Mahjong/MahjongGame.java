package Mahjong;

import GameRules.TouchDeal;
import Players.Player;
import GameRules.Chow;
import UI.GameBackGround;

import java.awt.*;
import java.util.Scanner;


public class MahjongGame extends Frame {
    private MahjongDeck deck;
    private Player[] players;
    private TouchDeal touchDeal;
    private int playerIndex;
    private int previousPlayerIndex;
    private GameBackGround gameBackGround;
    private Scanner scanner;
    private Player player;
    private Chow chow;
    private static boolean gameOver = false;

    public MahjongGame () {
        scanner = new Scanner(System.in);
        deck = new MahjongDeck();
        playerIndex = 0;
        //先不加UI的测试
        //setUI();

        players = new Player[4];
        MahjongTile discardTile = new MahjongTile("初始值", 0);
        for (int i = 0; i < 4; i++) {
            players[i] = new Player(discardTile, players);
        }
        touchDeal = new TouchDeal(players);
        chow = new Chow(discardTile, players, player);
    }

    public void drawTile() {
        MahjongTile tile = deck.drawTile();
        System.out.println("摸到的牌: " + tile);
        players[playerIndex].drawTile(tile);
    }


    public void testDrawAndDiscard() {
        System.out.println("你的回合：");
        updatePlayerHands();
        drawTile();
        touchDeal.discardTile(playerIndex, previousPlayerIndex);
    }

    public void dealTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                players[i].drawTile(tile);
            }
        }
        /*for (int i = 0; i < 4; i++) {
            System.out.println("Player " + (i + 1) + "'s hand:");
            players[i].displayHand();
            System.out.println();
        }*/
    }

    public void updatePlayerHands() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Player " + (i + 1) + "'s hand:");
            players[i].displayHand();
            System.out.println();
        }
    }

    public void update(Graphics g){
        gameBackGround.draw(g);
    }

    class Run extends Thread{
        public void run(){
            while (true){// continue repaint
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 1; i < 4; i++) {
            if (players[i].getHand().size() == 0) {
                return gameOver = true; // 当任意一位玩家的手牌为空时游戏结束
            }
        }
        return gameOver = false;
    }

    public void playComputerTurn() {
        // 进入电脑自动抽牌发牌的回合
        for (int i = 1; i < 4; i++) {
            //这里本来想写从玩家2开始（i=2）但不知道为什么超出列表了
            System.out.println("电脑 " + (i+1) + " 的回合：");
            updatePlayerHands();
            players[i].computerPlay();
            System.out.println();
        }
    }

    public void playGame() {
        System.out.println("游戏开始！");
        dealTiles();
        while (!isGameOver()) {
            testDrawAndDiscard();
            playComputerTurn();
        }
    }
    public static void main(String[] args) {
        MahjongGame game = new MahjongGame();
        game.playGame();
        }


}
