package Mahjong;
import Item.AbstractMahjongGame;
import Mahjong.MahjongGame;

import Players.Player;
import UI.GameScreen;
import UI.PlayerListener;
import ucd.comp2011j.engine.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MahjongGameTest extends AbstractMahjongGame {
    // 这是一个用来测试麻将规则能否顺利运行的类
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

    public static boolean ifDealTiles = false;// connect with Game Screen

    private PlayerListener listener;


    @Override
    public void playerTurn() {
        playerIndex = 0;
        System.out.println("进行吃判断：");

        System.out.println("玩家能不能吃牌");
        if (chow.isChow(discardedTile)) {
            System.out.println("你已吃牌，进行出牌");
            touchDeal.discardTile();//进行出牌操作
            //chow.isChow() = false;
        } else {
            System.out.println("你没有进行任何操作，进行抽牌");
            MahjongTile tile = deck.drawTile();//摸牌
            System.out.println("摸到的牌: " + tile);
            players[0].drawTile(tile);//将牌放入手牌
            touchDeal.discardTile();//进行出牌操作
        }

        discardedTile = touchDeal.getDiscardedTile();
        System.out.println(discardedTile);

        Player_initial_dealTiles=sortTiles_pre(players[0].hand);
        displayHand_pre(Player_initial_dealTiles);
        players[0].hand=Player_initial_dealTiles;
        player=players[0];

        computer1_hand=sortTiles_pre(computers[0].hand);
        displayHand_pre_computer(computer1_hand);
        computers[0].hand=computer1_hand;
        System.out.println();

    }

    @Override
    protected void playComputerTurn() {
        for (int i = 1; i < 2; i++) {
            System.out.println("电脑 " + (i) + " 的回合：");
//            updatePlayerHands();
            System.out.println("电脑能不能吃牌？");
            if (chow.isChow(discardedTile)) {
                System.out.println("电脑已吃牌，进行出牌");
                computers[i].computerPlayTile(i);
            } else {
                System.out.println("电脑没有进行任何操作，进行抽牌");
                computers[i].computerTouchTile(i);
                computers[i].computerPlayTile(i);
            }

            discardedTile = computers[i].getDiscardedTile();
            System.out.println(discardedTile);
//            computers[i].computerPlay(i + 1);
//            System.out.println();

            Player_initial_dealTiles=sortTiles_pre(players[0].hand);
            displayHand_pre(Player_initial_dealTiles);

            computer1_hand=sortTiles_pre(computers[0].hand);

            displayHand_pre_computer(computer1_hand);

            computers[0].hand=computer1_hand;
            System.out.println();
        }
    }

    @Override
    protected void updatePlayerHands() {
    }

    // 测试用的
    public MahjongGameTest() {
        mahjongGame = new MahjongGame();
        gameScreen = new GameScreen();
        this.deck = new MahjongDeck();
    }

    public void startGame2() {
        System.out.println("游戏开始！");
        dealTiles();
        playerTurn();
        playComputerTurn();
    }

    // 画出初始手牌
    protected void dealTiles() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                if (i == 0) {
                    players[0].drawTile(tile);
                } else {
                    computers[i - 1].drawTile(tile);
                }
            }
        }

        Player_initial_dealTiles=sortTiles_pre(players[0].hand);
        computer1_hand=sortTiles_pre(computers[0].hand);

        displayHand_pre(Player_initial_dealTiles);
        displayHand_pre_computer(computer1_hand);

        players[0].hand=Player_initial_dealTiles;
        computers[0].hand=computer1_hand;

        player=players[0];
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

    public static void main(String[] args) {
        MahjongGameTest gameManager = new MahjongGameTest();
        gameManager.startGame2(); // 调用抽象方法
    }
}
