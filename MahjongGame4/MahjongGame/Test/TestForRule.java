package Test;

import GameRules.Rules;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Players.Player;
import Players.PlayerManager;
import UI.PlayerListener;

import java.util.ArrayList;
import java.util.List;

public class TestForRule {
    private MahjongDeck deck;
    private Player[] players;
    private Rules rules;

    public TestForRule(PlayerListener listener) {
        PlayerManager playerManager = new PlayerManager();
        players = playerManager.getPlayers();
        deck = new MahjongDeck();
        rules = new Rules(players);
    }

    // Method to deal tiles automatically from the deck
    protected void dealTiles() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                players[i].drawPlayerTile(tile);
            }
        }
    }

    // New method to set tiles manually for testing
    protected void setPlayerTiles(Player player, List<MahjongTile> tiles) {
        player.getHand().clear(); // Clear any existing tiles in the player's hand
        for (MahjongTile tile : tiles) {
            player.drawPlayerTile(tile);
        }
    }

    public void startGame() {
        // 自动发牌
        dealTiles();

        // 修改手牌以便测试规则
        manualDealTiles();

        // 打印修改后的手牌
        System.out.println("修改后的手牌：");
        displayAllHands();

        // 检验碰规则
        testPengRule();
    }

    // 打印所有玩家的手牌
    public void displayAllHands() {
        for (int i = 0; i < players.length - 1; i++) {
            System.out.println("玩家 " + (i + 1) + " 的手牌：");
            displayHand(players[i].getHand());
            System.out.println();
        }
    }

    // 打印单个玩家的手牌
    public void displayHand(List<MahjongTile> hand) {
        for (MahjongTile tile : hand) {
            System.out.print(tile + " ");
        }
        System.out.println();
    }

    // Method to manually set the initial tiles for each player
    public void manualDealTiles() {
        // 设置示例手牌用于测试
        List<MahjongTile> player1Tiles = new ArrayList<>();
        player1Tiles.add(new MahjongTile(1, "万"));
        player1Tiles.add(new MahjongTile(2, "万"));
        player1Tiles.add(new MahjongTile(3, "万"));
        player1Tiles.add(new MahjongTile(1, "条"));
        player1Tiles.add(new MahjongTile(1, "条"));
        player1Tiles.add(new MahjongTile(1, "条"));
        player1Tiles.add(new MahjongTile(4, "条"));
        player1Tiles.add(new MahjongTile(5, "条"));
        player1Tiles.add(new MahjongTile(6, "条"));
        player1Tiles.add(new MahjongTile(1, "筒"));
        player1Tiles.add(new MahjongTile(2, "筒"));
        player1Tiles.add(new MahjongTile(3, "筒"));
        player1Tiles.add(new MahjongTile(4, "筒"));

        List<MahjongTile> player2Tiles = new ArrayList<>();
        player2Tiles.add(new MahjongTile(1, "条"));
        player2Tiles.add(new MahjongTile(1, "条"));
        player2Tiles.add(new MahjongTile(3, "条"));
        player2Tiles.add(new MahjongTile(4, "条"));
        player2Tiles.add(new MahjongTile(5, "条"));
        player2Tiles.add(new MahjongTile(6, "条"));
        player2Tiles.add(new MahjongTile(7, "条"));
        player2Tiles.add(new MahjongTile(8, "条"));
        player2Tiles.add(new MahjongTile(9, "条"));
        player2Tiles.add(new MahjongTile(1, "筒"));
        player2Tiles.add(new MahjongTile(2, "筒"));
        player2Tiles.add(new MahjongTile(3, "筒"));
        player2Tiles.add(new MahjongTile(4, "筒"));

        List<MahjongTile> player3Tiles = new ArrayList<>();
        player3Tiles.add(new MahjongTile(1, "筒"));
        player3Tiles.add(new MahjongTile(1, "筒"));
        player3Tiles.add(new MahjongTile(3, "筒"));
        player3Tiles.add(new MahjongTile(4, "筒"));
        player3Tiles.add(new MahjongTile(5, "筒"));
        player3Tiles.add(new MahjongTile(6, "筒"));
        player3Tiles.add(new MahjongTile(7, "筒"));
        player3Tiles.add(new MahjongTile(8, "筒"));
        player3Tiles.add(new MahjongTile(9, "筒"));
        player3Tiles.add(new MahjongTile(1, "万"));
        player3Tiles.add(new MahjongTile(2, "万"));
        player3Tiles.add(new MahjongTile(3, "万"));
        player3Tiles.add(new MahjongTile(4, "万"));


        // Set each player's hand to these tiles
        setPlayerTiles(players[0], player1Tiles);
        setPlayerTiles(players[1], player2Tiles);
        setPlayerTiles(players[2], player3Tiles);

    }

    // Method to test Peng rule
    public void testPengRule() {
        System.out.println("测试碰牌规则：");

        // 模拟玩家1出牌
        rules.setPlayerIndex(0);  // 设置当前玩家索引为0，即玩家1
        MahjongTile discardTile = new MahjongTile(1, "条");
        rules.discardTile = discardTile;
        System.out.println("玩家1出了一张牌：" + discardTile);


        // 检查玩家2是否可以碰
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        if (rules.isPeng()) {
            System.out.println("玩家2可以碰牌：" + discardTile);
            rules.isPeng();  // 执行碰牌操作
        } else {
            System.out.println("玩家2不可以碰牌：" + discardTile);
        }

        // 打印碰牌后的手牌
        displayAllHands();

        // 模拟玩家2出牌
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        MahjongTile discardTile1 = new MahjongTile(1, "筒");
        rules.discardTile = discardTile1;
        System.out.println("玩家2出了一张牌：" + discardTile1);

        // 检查玩家3是否可以碰
        rules.setPlayerIndex(2);  // 设置当前玩家索引为2，即玩家3
        rules.isPeng();
        // 打印碰牌后的手牌
        displayAllHands();
    }

    public static void main(String[] args) {
        PlayerListener playerListener = new PlayerListener();
        TestForRule testForRule = new TestForRule(playerListener);
        testForRule.startGame();
    }
}