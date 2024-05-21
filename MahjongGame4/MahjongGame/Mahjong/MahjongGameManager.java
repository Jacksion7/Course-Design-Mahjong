package Mahjong;

import Item.AbstractMahjongGame;
import Mahjong.MahjongGame;

import Players.Player;
import UI.CardHoverEffect;
import UI.GameScreen;
import UI.PlayerListener;
import ucd.comp2011j.engine.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class MahjongGameManager extends AbstractMahjongGame   {
    private MahjongGame mahjongGame;

    private MahjongDeck deck;

    public List<MahjongTile> hand;
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
        this.listener = listener;
        mahjongGame = new MahjongGame();
        gameScreen = new GameScreen();
        this.deck= new MahjongDeck();
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

        Player_initial_dealTiles=sortTiles_pre(players[0].hand);
        computer1_hand=sortTiles_pre(computers[0].hand);
        computer2_hand=sortTiles_pre(computers[1].hand);
        computer3_hand=sortTiles_pre(computers[2].hand);

        displayHand_pre(Player_initial_dealTiles);
        displayHand_pre_computer(computer1_hand);
        displayHand_pre_computer(computer2_hand);
        displayHand_pre_computer(computer3_hand);
        players[0].hand=Player_initial_dealTiles;
        computers[0].hand=computer1_hand;
        computers[1].hand=computer2_hand;
        computers[2].hand=computer3_hand;
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
        //SwingUtilities.invokeLater(GameScreen::new);
        dealTiles();

        //displayHand();
        System.out.println(isGameOver());
        while (!isGameOver()) {
            playerTurn();
            playComputerTurn();
//            mahjongGame.playComputerTurn();
        }
        System.out.println("游戏结束！");
    }

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

    public List<MahjongTile> sortTiles_pre2(List<MahjongTile> hand){
        // 副本
        List<MahjongTile> new_hand = new ArrayList<>();
        String[] sequence = {"万","条","筒","东", "南", "西", "北", "中", "发", "白"};
        for (int j = 0; j < 10; j++) {
            for (MahjongTile tile : hand) {
                if (tile.getSuit().equals(sequence[j])){
                    new_hand.add(tile);
                }
            }
        }
        return new_hand;
    }

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

    public void displayHand() {
        //sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile.toString() + " ");
        }
        System.out.println();
    }

    public void sortHand() {
        Collections.sort(hand, (t1, t2) -> {
            int suitOrder1 = getSuitOrder(t1.getSuit());
            int suitOrder2 = getSuitOrder(t2.getSuit());
            if (suitOrder1 != suitOrder2) {
                return suitOrder1 - suitOrder2;
            }
            return t1.getValue() - t2.getValue();
        });
    }

    private int getSuitOrder(String suit) {
        return switch (suit) {
            case "万" -> 0;
            case "条" -> 1;
            case "筒" -> 2;
            case "东" -> 3;
            case "西" -> 4;
            case "南" -> 5;
            case "北" -> 6;
            case "中" -> 7;
            case "发" -> 8;
            default -> 9;
        };
    }






    protected void testDrawAndDiscard() {

    }

    @Override
    protected void playerTurn() {
        System.out.println("你的回合：");
        updatePlayerHands();//表示手牌
//        if (win.isWin()) {
//            if (gang.isGang()) {
//                if (peng.isPeng()) {
//                    if (chow.isChow()) {
//                        System.out.println("你已吃牌，进行出牌");
//                        touchDeal.discardTile();//进行出牌操作
//                        //chow.isChow() = false;
//                    }
//                    System.out.println("你已碰牌，进行出牌");
//                    touchDeal.discardTile();//进行出牌操作
//                    //peng.isPeng() = false;
//                }
//                System.out.println("你已杠牌，进行出牌");
//                touchDeal.discardTile();//进行出牌操作
//                //gang.isGang() = false;
//            }
//            System.out.println("你已胡牌");
//        }
        System.out.println("玩家能不能吃牌");
        if (chow.isChow(discardedTile)) {
            System.out.println("你已吃牌，进行出牌");
            touchDeal.discardTile();//进行出牌操作
            //chow.isChow() = false;
        }
        System.out.println("你没有进行任何操作，进行出牌");
        MahjongTile tile = deck.drawTile();//摸牌
        System.out.println("摸到的牌: " + tile);
        players[0].drawTile(tile);//将牌放入手牌
        touchDeal.discardTile();//进行出牌操作
        discardedTile = touchDeal.getDiscardedTile();//获取出牌的牌
    }

    @Override
    protected void updatePlayerHands() {

    }

    @Override
    public boolean isGameOver() {
        return mahjongGame.isGameOver();
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