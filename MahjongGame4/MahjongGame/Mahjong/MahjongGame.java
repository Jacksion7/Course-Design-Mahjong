package Mahjong;

import GameRules.TouchDeal;
import Players.Player;
import UI.Constant;
import UI.GameBackGround;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private static boolean gameOver = false;

    public MahjongGame () {
        scanner = new Scanner(System.in);
        player = new Player();
        deck = new MahjongDeck();
        playerIndex = 0;
        //先不加UI的测试
        //setUI();

        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }
        touchDeal = new TouchDeal(players);
    }

    public void updatePlayerIndices() {
        previousPlayerIndex = playerIndex;
        playerIndex = (playerIndex + 1) % 4; // 更新为下一个玩家的索引
    }
    // 在测试摸牌和出牌的方法中，调用此方法来更新当前玩家和前一个玩家的索引
    public void updateIndicesAndDiscard() {
        updatePlayerIndices();
        drawAndDiscard();
    }
    public void drawAndDiscard() {
        drawTile(); // 摸牌
        touchDeal.discardTile(playerIndex, previousPlayerIndex); // 出牌并传入索引
        updatePlayerHands(); // 更新玩家手牌
    }
    public void drawTile() {
        MahjongTile tile = deck.drawTile();
        System.out.println("摸到的牌: " + tile);
        touchDeal.drawTile(tile);
    }


    public void testDrawAndDiscard() {
        drawTile();
        touchDeal.discardTile(playerIndex,  previousPlayerIndex);
        updatePlayerHands();
    }

    public void dealTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                players[i].drawTile(tile);
            }
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("Player " + (i + 1) + "'s hand:");
            players[i].displayHand();
            System.out.println();
        }
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

    public void initGamg(){
        gameBackGround= new GameBackGround();


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
            System.out.println("电脑 " + i + " 的回合：");
            players[i].computerPlay();
            updatePlayerHands();
        }
    }

    public void setUI(){
        //visible of window
        setVisible(true);
        //size
        setSize(Constant.FRAM_WIDTH, Constant.FRAM_HEIGHT);
        //title
        setTitle(Constant.FRAM_TITLE);
        //location
        setLocation(Constant.FRAM_x, Constant.FRAM__y);
        //size of window can't change
        setResizable(false);



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);// finish program
            }
        });
        initGamg();
        new Run().start();

    }

    public static void main(String[] args) {
        MahjongGame game = new MahjongGame();
        game.dealTiles();
        while (!game.isGameOver()) {
            game.testDrawAndDiscard();
            game.playComputerTurn();
        }

    }




}
