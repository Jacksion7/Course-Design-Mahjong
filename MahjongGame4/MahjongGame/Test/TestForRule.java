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
        player.getHand().clear();
        // Clear any existing tiles in the player's hand
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

        //检验杠规则
        testGangRule();

        //验证吃牌
        testChowRule();
    }

    // 打印所有玩家的手牌
    public void displayAllHands() {
        for (int i = 0; i < players.length - 1; i++) {
            System.out.println("玩家 " + (i + 1) + " 的手牌：");
            displayHand(players[i].getHand());
            displayRulesTiles(players[i].getRulesTiles());
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

    // 打印单个玩家的规则牌
    public void displayRulesTiles(List<MahjongTile> rulesTiles) {
        System.out.print("规则牌：");
        for (MahjongTile tile : rulesTiles) {
            System.out.print(tile + " ");
        }
        System.out.println();
    }

    // Method to manually set the initial tiles for each player
    public void manualDealTiles() {
        // 设置示例手牌用于测试
        List<MahjongTile> player1Tiles = new ArrayList<>();
        player1Tiles.add(new MahjongTile(1, "条"));
        player1Tiles.add(new MahjongTile(1, "条"));
        player1Tiles.add(new MahjongTile(1, "条"));
        player1Tiles.add(new MahjongTile(4, "条"));
        player1Tiles.add(new MahjongTile(4, "条"));
        player1Tiles.add(new MahjongTile(4, "条"));
        player1Tiles.add(new MahjongTile(1, "万"));
        player1Tiles.add(new MahjongTile(2, "万"));
        player1Tiles.add(new MahjongTile(3, "万"));
        player1Tiles.add(new MahjongTile(1, "筒"));
        player1Tiles.add(new MahjongTile(2, "筒"));
        player1Tiles.add(new MahjongTile(3, "筒"));
        player1Tiles.add(new MahjongTile(6, "条"));

        List<MahjongTile> player2Tiles = new ArrayList<>();
        player2Tiles.add(new MahjongTile(1, "条"));
        player2Tiles.add(new MahjongTile(1, "条"));
        player2Tiles.add(new MahjongTile(4, "条"));
        player2Tiles.add(new MahjongTile(4, "条"));
        player2Tiles.add(new MahjongTile(5, "条"));
        player2Tiles.add(new MahjongTile(7, "条"));
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
        player3Tiles.add(new MahjongTile(1, "筒"));
        player3Tiles.add(new MahjongTile(4, "筒"));
        player3Tiles.add(new MahjongTile(4, "筒"));
        player3Tiles.add(new MahjongTile(6, "筒"));
        player3Tiles.add(new MahjongTile(1, "万"));
        player3Tiles.add(new MahjongTile(2, "万"));
        player3Tiles.add(new MahjongTile(3, "万"));
        player3Tiles.add(new MahjongTile(1, "条"));
        player3Tiles.add(new MahjongTile(2, "条"));
        player3Tiles.add(new MahjongTile(3, "条"));
        player3Tiles.add(new MahjongTile(4, "条"));


        // Set each player's hand to these tiles
        setPlayerTiles(players[0], player1Tiles);
        setPlayerTiles(players[1], player2Tiles);
        setPlayerTiles(players[2], player3Tiles);
    }

    // Method to test Peng rule
    public void testPengRule() {
        System.out.println("----测试碰牌---- ");

        System.out.println("----玩家2是否能碰玩家1的牌----");
        // 模拟玩家1出牌，此时玩家2吃牌
        rules.setPlayerIndex(0);  // 设置当前玩家索引为0，即玩家1
        MahjongTile discardTile = new MahjongTile(1, "条");
        players[0].dealPlayerTile(discardTile);
        rules.discardTile = discardTile;
        System.out.println("玩家1出了一张牌：" + discardTile);

        // 检查玩家2是否可以碰
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        if (!rules.isPeng()) {
            System.out.println("玩家2不可以碰牌：" + discardTile);
            // 检查玩家3是否可以碰
            rules.setPlayerIndex(2);  // 设置当前玩家索引为2，即玩家3
            if (!rules.isPeng()) {
                System.out.println("玩家3不可以碰牌：" + discardTile);
            }
        }

        // 打印碰牌后的手牌
        displayAllHands();

        System.out.println("----玩家3是否能碰玩家1的牌----");
        // 模拟玩家1出牌，此时玩家3吃牌
        rules.setPlayerIndex(0);  // 设置当前玩家索引为0，即玩家1
        MahjongTile discardTile1 = new MahjongTile(4, "筒");
        players[0].dealPlayerTile(discardTile1);
        rules.discardTile = discardTile1;
        System.out.println("玩家1出了一张牌：" + discardTile1);

        // 检查玩家2是否可以碰
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        if (!rules.isPeng()) {
            System.out.println("玩家2不可以碰牌：" + discardTile1);
            // 检查玩家3是否可以碰
            rules.setPlayerIndex(2);  // 设置当前玩家索引为2，即玩家3
            if (!rules.isPeng()) {
                System.out.println("玩家3不可以碰牌：" + discardTile1);
            }
        }

        // 打印碰牌后的手牌
        displayAllHands();
    }

    // Method to test Gang rule
    public void testGangRule() {
        System.out.println("----测试杠牌规则---- ");

        System.out.println("----玩家3在已经抽到三张一样的牌的前提下能否杠牌----");
        // 模拟玩家2出牌
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        MahjongTile discardTile = new MahjongTile(1, "筒");
        rules.discardTile = discardTile;
        players[1].dealPlayerTile(discardTile);  // 从玩家1的手牌中移除出掉的牌
        System.out.println("玩家2出了一张牌：" + discardTile);

        // 检查玩家3是否可以杠
        rules.setPlayerIndex(2);  // 设置当前玩家索引为2，即玩家3
        if (rules.isGang()) {
            System.out.println("玩家3可以杠牌：" + discardTile);
        } else {
            System.out.println("玩家3不可以杠牌：" + discardTile);
        }

        // 打印杠牌后的手牌
        displayAllHands();


        System.out.println("----玩家2在完成碰牌的前提下能否通过摸牌来杠牌----");
        // 模拟玩家2摸牌
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        MahjongTile discardTile1 = new MahjongTile(1, "条");
        rules.discardTile = discardTile1;
        players[1].drawPlayerTile(discardTile1);  // 从玩家1的手牌中移除出掉的牌
        System.out.println("玩家2抽了一张牌：" + discardTile1);

        // 检查玩家2是否可以杠
        if (rules.isGang()) {
            System.out.println("玩家2可以杠牌：" + discardTile1);
        } else {
            System.out.println("玩家2不可以杠牌：" + discardTile1);
        }

        // 打印杠牌后的手牌
        displayAllHands();


        System.out.println("----玩家1在已经抽到三张一样的牌的前提下能否通过上家出牌来杠牌----");

        // 模拟玩家2出牌
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        MahjongTile discardTile3 = new MahjongTile(4, "条");
        rules.discardTile = discardTile3;
        players[1].dealPlayerTile(discardTile3);  // 从玩家的手牌中移除出掉的牌
        System.out.println("玩家2出了一张牌：" + discardTile3);

        // 检查玩家3,1是否可以杠
        rules.setPlayerIndex(2);
        if (rules.isGang()) {
            System.out.println("玩家3可以杠牌：" + discardTile3);
        }else {
            System.out.println("玩家3不可以杠牌：" + discardTile3);
            rules.setPlayerIndex(0);
            if (rules.isGang()) {
                System.out.println("玩家1可以杠牌：" + discardTile3);
            } else {
                System.out.println("玩家1不可以杠牌：" + discardTile3);
            }
        }

        // 打印杠牌后的手牌
        displayAllHands();

    }

    public void testChowRule(){
        System.out.println("----测试吃牌规则---- ");

        System.out.println("----玩家2能否吃牌（上家出的6条）----");
        // 模拟玩家1出牌
        rules.setPlayerIndex(0);  // 设置当前玩家索引为0，即玩家1
        MahjongTile discardTile = new MahjongTile(6, "条");
        rules.discardTile = discardTile;
        players[0].dealPlayerTile(discardTile);  // 从玩家1的手牌中移除出掉的牌
        System.out.println("玩家1出了一张牌：" + discardTile);

        // 检查玩家2是否可以吃
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        if (rules.isChow()) {
            System.out.println("玩家2可以吃牌：" + discardTile);
        } else {
            System.out.println("玩家2不可以吃牌：" + discardTile);
        }

        // 打印杠牌后的手牌
        displayAllHands();



        System.out.println("----玩家3能否吃牌（上家出的1万）----");
        // 模拟玩家2抽牌
        rules.setPlayerIndex(1);  // 设置当前玩家索引为1，即玩家2
        MahjongTile discardTile1 = new MahjongTile(1, "万");
        rules.discardTile = discardTile1;
        players[1].drawPlayerTile(discardTile1);
        System.out.println("玩家2抽了一张牌：" + discardTile1);

        // 检查玩家3是否可以吃
        rules.setPlayerIndex(2);  // 设置当前玩家索引为2，即玩家3
        if (rules.isChow()) {
            System.out.println("玩家3可以吃牌：" + discardTile1);
        } else {
            System.out.println("玩家3不可以吃牌：" + discardTile1);
        }

        // 打印杠牌后的手牌
        displayAllHands();
    }



    public static void main(String[] args) {
        PlayerListener playerListener = new PlayerListener();
        TestForRule testForRule = new TestForRule(playerListener);
        testForRule.startGame();
    }
}