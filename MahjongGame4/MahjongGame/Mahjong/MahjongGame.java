package Mahjong;

import GameRules.*;
import GameRules.TouchDeal;
import Players.Player;

import UI.GameBackGround;
import Players.Computers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MahjongGame extends Frame {
    private MahjongDeck deck;
    private Player player;
    private Player[] players;
    private int playerIndex;
    private Computers computer;
    private Computers[] computers;
    private GameBackGround gameBackGround;
    private Scanner scanner;
    private TouchDeal touchDeal;
    private Chow chow;
    private Peng peng;
    private Gang gang;
    private Win win;
    private static boolean gameOver = false;

    //创建一个用来存放玩家们出牌的列表
    private ArrayList<MahjongTile> tileList = new ArrayList<>(4);


    public MahjongGame () {
        //scanner = new Scanner(System.in);
        deck = new MahjongDeck();

        //总共四个玩家（一位玩家和三位电脑玩家），顺序是0,1,2,3
        playerIndex = 0;

        //先不加UI的测试
        //setUI();


        //初始一张临时麻将牌（我也不确定干啥用的）
        MahjongTile discardTile = new MahjongTile("初始值", 0);

        //创建一位玩家
        players = new Player[1];
        players[0] = new Player(discardTile);

        //创建三个电脑
        computers = new Computers[3];
        for (int i = 0; i < 3; i++) {
            computers[i] = new Computers(discardTile);
        }

        //初始化各个规则功能模块
        touchDeal = new TouchDeal(players, computers);
        chow = new Chow(discardTile, players, computers);
        peng = new Peng(discardTile, players, computers);
        gang = new Gang(discardTile, players, computers);
        win = new Win(players, computers);

    }
    public int getPlayerIndex() {
        return playerIndex = (playerIndex + 1) % 4;
    }

    //测试出摸牌
    public void testDrawAndDiscard() {
        System.out.println("你的回合：");
        updatePlayerHands();
        MahjongTile tile = deck.drawTile();
        System.out.println("摸到的牌: " + tile);
        players[0].drawTile(tile);
        touchDeal.discardTile();
    }

    //这应该最开始的初始化牌面的方法
    //模拟的是游戏开始时的摸牌阶段
    public void dealTiles() {
        //这是玩家摸牌阶段
        for (int j = 0; j < 13; j++) {
            MahjongTile tile = deck.drawTile();
            players[0].drawTile(tile);
        }
        //这是电脑摸牌阶段
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                computers[i].drawTile(tile);
            }
        }

        //System.out.println(players[0].getHand());
        for (int i = 0; i < 3; i++) {
            //System.out.println(computers[i].getHand());
        }

    }

    public void updatePlayerHands() {
        System.out.println("Player 's hand:");
        players[0].displayHand();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println("Player " + (i + 1) + "'s hand:");
            computers[i].displayHand();
            System.out.println();
        }
        playerIndex = (playerIndex + 1) % 4;
    }

/*
    public static void main(String[] args) {
        MahjongGame game = new MahjongGame();
        game.dealTiles();
        game.updatePlayerHands();

    }

 */



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
