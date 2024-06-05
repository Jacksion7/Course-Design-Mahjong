package Mahjong;

import GameRules.Rules;
import Players.Player;
import Players.PlayerManager;
import UI.GameScreen;
import UI.PlayerListener;


import java.util.ArrayList;
import java.util.List;



public class MahjongGameManager {
    private MahjongDeck deck;
    private Player[] players;
    private Rules rules;
    private static int playerIndex;
    public static List<MahjongTile> Player_hand;// connect with Game Screen

    public static List<MahjongTile> computer1_hand;
    public static List<MahjongTile> computer2_hand;
    public static List<MahjongTile> computer3_hand;

    public static Player player;
    private GameScreen gameScreen;

    public static boolean ifDealTiles= false;// connect with Game Screen

    public static boolean ifDiscardTiles= false;//
    private PlayerListener listener;

    public  static List<MahjongTile>  usedTiles =new ArrayList<>();

    public MahjongGameManager(PlayerListener listener) {
        PlayerManager playerManager = new PlayerManager();
        players = playerManager.getPlayers();
        deck= new MahjongDeck();
        rules = new Rules(players);
        playerIndex = 0;
        this.listener = listener;
        gameScreen = new GameScreen();

    }

    public static int getRound() {
        return playerIndex + 1; // 假设回合数是当前玩家的索引加1
    }


    public void startGame() {
        System.out.println("游戏开始！");

        dealTiles();
        update_static_parameter();
        startGameScreen();
        firstRoundHandTile();
      // updateGameScreen();
        while (!isGameOver()) {
            for (Player player : players) {
                update_static_parameter();
                playerTurn(player);
            }
        }
        System.out.println("游戏结束！");
    }

    public void playerTurn(Player player) {

        System.out.println("Players.Player "+ (playerIndex + 1)  + " 的回合：");
        player.updateHand();
        player.playerPlay();
        if (playerIndex==1){
            setPlayer(player);// 当player index=1 更新Game Screen上玩家的手牌
        }
        playerIndex = rules.updatePlayerIndex();
        updateGameScreen();
        System.out.println();

    }



    public List<MahjongTile> getPlayer_hand() {
        return Player_hand;
    }

    public boolean isIfDealTiles() {
        return ifDealTiles;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        MahjongGameManager.player = player;
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

    public void startGameScreen(){
        gameScreen.setVisible(true);
    }

    public void updateGameScreen(){
        //gameScreen.removeAll();
        gameScreen.setVisible(false);
        gameScreen= new GameScreen();
        startGameScreen();
        gameScreen.repaint(); // 请求界面重新绘制
        System.out.println("Updating game screen...");
    }

    public void update_static_parameter(){
        ifDealTiles=true;

        Player_hand =sortTiles_pre(players[0].hand);
        //变量名字我先不改了，我就把后面的hand改了
        computer1_hand=sortTiles_pre(players[1].hand);
        computer2_hand=sortTiles_pre(players[2].hand);
        computer3_hand=sortTiles_pre(players[3].hand);

        displayHand_pre(Player_hand);
        displayHand_pre_computer(computer1_hand);
        displayHand_pre_computer(computer2_hand);
        displayHand_pre_computer(computer3_hand);
        players[0].hand= Player_hand;
        players[1].hand=computer1_hand;
        players[2].hand=computer2_hand;
        players[3].hand=computer3_hand;
        //这里也改了，将0改成了playerIndex
        player=players[0];
        // player=players[0];
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

    // 画出初始手牌
    protected void dealTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                players[i].dealPlayerTile(tile);
            }
        }
//        update_static_parameter();
    }




    public static void main(String[] args) {
        PlayerListener playerListener = new PlayerListener();
        MahjongGameManager gameManager = new MahjongGameManager(playerListener);
        gameManager.startGame(); // 调用抽象方法

    }
}