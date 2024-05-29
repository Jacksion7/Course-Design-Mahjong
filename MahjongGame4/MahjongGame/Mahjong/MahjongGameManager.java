package Mahjong;

import GameRules.Rules;
import Players.Player;
import Players.PlayerManager;
import UI.CardHoverEffect;
import UI.GameScreen;
import UI.PlayerListener;
import ucd.comp2011j.engine.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class MahjongGameManager {
    private MahjongDeck deck;
    private Player[] players;
    private Rules rules;
    private int playerIndex;

    //public List<MahjongTile> hand;
    private int playerLives;
    private int playerScore;
    public static List<MahjongTile> Player_initial_dealTiles;// connect with Game Screen

    public static List<MahjongTile> computer1_hand;
    public static List<MahjongTile> computer2_hand;
    public static List<MahjongTile> computer3_hand;

    public static Player player;
    private GameScreen gameScreen;

    public static boolean ifDealTiles= false;// connect with Game Screen

    private PlayerListener listener;

    public MahjongGameManager(PlayerListener listener) {
        PlayerManager playerManager = new PlayerManager();
        players = playerManager.getPlayers();
        deck= new MahjongDeck();
        rules = new Rules(players);
        playerIndex = 0;
        this.listener = listener;
        gameScreen = new GameScreen();
    }


// 测试用的
//    public MahjongGameManager() {
//        mahjongGame = new MahjongGame();
//        gameScreen = new GameScreen();
//        this.deck= new MahjongDeck();
//    }

    // 画出初始手牌
    protected void dealTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                players[i].dealPlayerTile(tile);
            }
        }

        
        ifDealTiles=true;

        Player_initial_dealTiles=sortTiles_pre(players[0].hand);
        //变量名字我先不改了，我就把后面的hand改了
        computer1_hand=sortTiles_pre(players[1].hand);
        computer2_hand=sortTiles_pre(players[2].hand);
        computer3_hand=sortTiles_pre(players[3].hand);

        displayHand_pre(Player_initial_dealTiles);
        displayHand_pre_computer(computer1_hand);
        displayHand_pre_computer(computer2_hand);
        displayHand_pre_computer(computer3_hand);
        players[0].hand=Player_initial_dealTiles;
        players[1].hand=computer1_hand;
        players[2].hand=computer2_hand;
        players[3].hand=computer3_hand;
        //这里也改了，将0改成了playerIndex
        player=players[playerIndex];
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
        //SwingUtilities.invokeLater(GameScreen::new);
        dealTiles();

        //displayHand();
        System.out.println(isGameOver());
        firstRoundHandTile();
        while (!isGameOver()) {
            for (Player player : players)
                playerTurn(player);
        }
        System.out.println("游戏结束！");
    }

    // 测试用的
//    public void startGame2() {
//        System.out.println("游戏开始！");
//
//        dealTiles();
//
//        //displayHand();
//        System.out.println(isGameOver());
//        while (!isGameOver()) {
//            playerTurn();
//            playComputerTurn();
////            mahjongGame.playComputerTurn();
//        }
//        System.out.println("游戏结束！");
//    }

    public List<MahjongTile> sortTiles_pre(List<MahjongTile> hand){
        List<MahjongTile> new_hand_1 = new ArrayList<>();
        Integer[] sequence_1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        // 遍历排序后的顺序
        for (int value : sequence_1) {
            // 遍历玩家手牌
            for (MahjongTile tile : hand) {
                // 如果牌的值与当前顺序值相同，将其添加到新手牌列表中
                if (tile.getValue() == value) {
                    new_hand_1.add(tile);
                }
            }
        }

        List<MahjongTile> new_hand_2 = new ArrayList<>();
        String[] sequence = {"万","条","筒","东", "南", "西", "北", "中", "发", "白"};
        for (int j = 0; j < 10; j++) {
            for (MahjongTile tile : new_hand_1) {
                if (tile.getSuit().equals(sequence[j])){
                    new_hand_2.add(tile);
                }
            }
        }


        return new_hand_2;
    }

//    public List<MahjongTile> sortTiles_pre2(List<MahjongTile> hand){
//        // 副本
//        List<MahjongTile> new_hand = new ArrayList<>();
//        String[] sequence = {"万","条","筒","东", "南", "西", "北", "中", "发", "白"};
//        for (int j = 0; j < 10; j++) {
//            for (MahjongTile tile : hand) {
//                if (tile.getSuit().equals(sequence[j])){
//                    new_hand.add(tile);
//                }
//            }
//        }
//        return new_hand;
//    }

    public void displayHand_pre(List<MahjongTile> hand) {
        System.out.println("player: ");
        //sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile.toString() + " ");
        }
        System.out.println();
    }

    public void displayHand_pre_computer(List<MahjongTile> hand) {
        System.out.println("computer: ");
        //sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile.toString() + " ");
        }
        System.out.println();
    }


    public void startGameScreen(){
        gameScreen.setVisible(true);
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

//    public void displayHand() {
//        //sortHand();
//        for (MahjongTile tile : hand) {
//            System.out.print(tile.toString() + " ");
//        }
//        System.out.println();
//    }

//    public void sortHand() {
//        Collections.sort(hand, (t1, t2) -> {
//            int suitOrder1 = getSuitOrder(t1.getSuit());
//            int suitOrder2 = getSuitOrder(t2.getSuit());
//            if (suitOrder1 != suitOrder2) {
//                return suitOrder1 - suitOrder2;
//            }
//            return t1.getValue() - t2.getValue();
//        });
//    }

//    private int getSuitOrder(String suit) {
//        return switch (suit) {
//            case "万" -> 0;
//            case "条" -> 1;
//            case "筒" -> 2;
//            case "东" -> 3;
//            case "西" -> 4;
//            case "南" -> 5;
//            case "北" -> 6;
//            case "中" -> 7;
//            case "发" -> 8;
//            default -> 9;
//        };
//    }

//    protected void testDrawAndDiscard() {
//
//    }


    public void playerTurn(Player player) {
        System.out.println("Players.Player "+ (playerIndex + 1)  + " 的回合：");
        player.updateHand();
        player.playerPlay();
        playerIndex = rules.updatePlayerIndex();
        System.out.println();

        //这部分放到PlayerPlay()方法中
//        discardedTile = touchDeal.getDiscardedTile();
//        System.out.println(discardedTile);
//
//        Player_initial_dealTiles=sortTiles_pre(players[0].hand);
//        displayHand_pre(Player_initial_dealTiles);
//        players[0].hand=Player_initial_dealTiles;
//        player=players[0];
//
//        computer1_hand=sortTiles_pre(computers[0].hand);
//        computer2_hand=sortTiles_pre(computers[1].hand);
//        computer3_hand=sortTiles_pre(computers[2].hand);
//
//        displayHand_pre_computer(computer1_hand);
//        displayHand_pre_computer(computer2_hand);
//        displayHand_pre_computer(computer3_hand);
//
//        computers[0].hand=computer1_hand;
//        computers[1].hand=computer2_hand;
//        computers[2].hand=computer3_hand;
//        System.out.println();

    }

    public void firstRoundHandTile() {
        for (Player player : players) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                player.drawPlayerTile(tile);
            }
            //player.displayHand();
        }
        //System.out.println("1234567890");
    }

//    protected void playComputerTurn() {
//        for (int i = 0; i < 3; i++) {
//            System.out.println("电脑 " + (i) + " 的回合：");
////            updatePlayerHands();
//            System.out.println("电脑能不能吃牌？");
//            if (chow.isChow(discardedTile)) {
//                System.out.println("已吃牌，进行出牌");
//                computers[i].computerPlayTile(i);
//            } else {
//                System.out.println("你没有进行任何操作，进行抽牌");
//                computers[i].computerTouchTile(i);
//                computers[i].computerPlayTile(i);
//            }
//
//            discardedTile = computers[i].getDiscardedTile();
//            System.out.println(discardedTile);
////            computers[i].computerPlay(i + 1);
////            System.out.println();
//
//            Player_initial_dealTiles=sortTiles_pre(players[0].hand);
//            displayHand_pre(Player_initial_dealTiles);
//
//            computer1_hand=sortTiles_pre(computers[0].hand);
//            computer2_hand=sortTiles_pre(computers[1].hand);
//            computer3_hand=sortTiles_pre(computers[2].hand);
//
//            displayHand_pre_computer(computer1_hand);
//            displayHand_pre_computer(computer2_hand);
//            displayHand_pre_computer(computer3_hand);
//
//            computers[0].hand=computer1_hand;
//            computers[1].hand=computer2_hand;
//            computers[2].hand=computer3_hand;
//            System.out.println();
//        }
//    }

    public boolean isGameOver() {
        for (int i = 1; i < 4; i++) {
            if (players[0].getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        PlayerListener playerListener = new PlayerListener();
        MahjongGameManager gameManager = new MahjongGameManager(playerListener);
        gameManager.startGame(); // 调用抽象方法

//        PlayerListener playerListener = new PlayerListener();
//        MahjongGameManager gameManager = new MahjongGameManager(playerListener);
//        gameManager.dealTiles();
//        gameManager.displayHand();
    }
}