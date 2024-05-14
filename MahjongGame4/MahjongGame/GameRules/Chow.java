package GameRules;

import Item.Tile;
import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;
import Item.PlayerBase;

import java.util.ArrayList;
import java.util.List;

public class Chow {
    private MahjongTile discardedTile;
    private Player[] players;
    private PlayerBase playerBase;
    private Computers[] computers;
    private MahjongTile secondTile;
    private MahjongTile thirdTile;
    private int playerIndex;
    public Chow(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        this.discardedTile = discardedTile;
        this.players = players;
        this.computers = computers;
        this.playerIndex = playerIndex;
    }

    /*
    这个方法是查找当前回合玩家的下家的?还是用来查找上家的？
    正常来说，应该是玩家出牌，然后下家判断上家发的牌能不能吃。
    所以应该是到下家摸牌之前，判断能不能进行吃牌操作。
     */

    public int getNextPlayer(int playerIndex) {
        // 这个方法返回当前玩家的下家索引。在麻将游戏中，玩家是按顺时针方向轮流出牌的，所以下家就是当前玩家索引加1后对4取模的结果。
        return (playerIndex + 1) % 4;
    }

    //这个方法应该是判断是否能吃牌的
    //是下家在摸牌之前判断能不能吃牌！！
    public boolean canChow(MahjongTile discardedTile, int playerIndex, Player[] players, Computers[] computers) {
        int value = discardedTile.getValue();
        String suit = discardedTile.getSuit();
        int nextPlayer = getNextPlayer(playerIndex);
        // 检测一下读取的牌是否正确
        System.out.println("This: " + value + suit);

        // 获取当前玩家或电脑的手牌
        List<MahjongTile> hand = (nextPlayer == 0) ? players[nextPlayer].getHand() : computers[nextPlayer - 1].getHand();

        //System.out.println("Hand: " + hand);

        // 检查是否为万、筒、条中的一种
        if (!suit.equals("万") && !suit.equals("筒") && !suit.equals("条")) {
            return false;
        }

        for (MahjongTile tile : hand) {
            if (tile.getSuit().equals(suit)) {
                // 如果值为1，查找是否有值为2和3的牌
                if (value == 1 && tile.getValue() == 2 && tileExists(3, suit, nextPlayer, players, computers)) {
                    secondTile = new MahjongTile(suit, 2);
                    thirdTile = new MahjongTile(suit, 3);
                    System.out.println("[从1查 " + value + secondTile + thirdTile + "]");
                    return true;
                }
                // 如果值为9，查找是否有值为8和7的牌
                else if (value == 9 && tile.getValue() == 8 && tileExists(7, suit, nextPlayer, players, computers)) {
                    secondTile = new MahjongTile(suit, 8);
                    thirdTile = new MahjongTile(suit, 7);
                    System.out.println("[从9查 " + value + secondTile + thirdTile + "]");
                    return true;
                }
                // 如果值为2，分两部分查，第一查（1,3），第二查（3,4）
                else if (value == 2) {
                    if (tile.getValue() == 1 && tileExists(3, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 1);
                        thirdTile = new MahjongTile(suit, 3);
                        System.out.println("[2查（1,3） " + value + secondTile + thirdTile + "]");
                        return true;
                    } else if (tile.getValue() == 3 && tileExists(4, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 3);
                        thirdTile = new MahjongTile(suit, 4);
                        System.out.println("[2查（3,4） " + value + secondTile + thirdTile + "]");
                        return true;
                    }
                    return false;
                }
                // 如果值为8，分两部分查，第一查（7,9），第二查（6,7）
                else if (value == 8) {
                    if (tile.getValue() == 7 && tileExists(9, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 7);
                        thirdTile = new MahjongTile(suit, 9);
                        System.out.println("[8查（7,9） " + value + secondTile + thirdTile + "]");
                        return true;
                    } else if (tile.getValue() == 6 && tileExists(7, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 6);
                        thirdTile = new MahjongTile(suit, 7);
                        System.out.println("[8查（6,7） " + value + secondTile + thirdTile + "]");
                        return true;
                    }
                    return false;
                }
                // 如果值为3~7，分三部分查，第一查（value-1和value-2），第二查（value+1和value+2），第三查（value-1和value+1）
                else if (value >= 3 && value <= 7) {
                    if (tile.getValue() == value - 1 && tileExists(value - 2, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, value - 1);
                        thirdTile = new MahjongTile(suit, value - 2);
                        System.out.println("[-1-2 " + value + secondTile + thirdTile + "]");
                        return true;
                    } else if (tile.getValue() == value + 1 && tileExists(value + 2, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, value + 1);
                        thirdTile = new MahjongTile(suit, value + 2);
                        System.out.println("[+1+2 " + value + secondTile + thirdTile + "]");
                        return true;
                    } else if (tile.getValue() == value - 1 && tileExists(value + 1, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, value - 1);
                        thirdTile = new MahjongTile(suit, value + 1);
                        System.out.println("[-1+1 " + value + secondTile + thirdTile + "]");
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    // 检查玩家手牌中是否存在指定牌值和类型的牌
    private boolean tileExists(int value, String suit, int nextPlayer, Player[] players, Computers[] computers) {
        List<MahjongTile> hand = (nextPlayer == 0) ? players[0].getHand() : computers[nextPlayer - 1].getHand();
        for (MahjongTile tile : hand) {
            if (tile.getValue() == value && tile.getSuit().equals(suit)) {
                return true;
            }
        }
        return false;
    }

    public void chowTile(MahjongTile discardedTile, int playerIndex) {
        // 检查是否可以吃牌
        if (canChow(discardedTile, playerIndex, players, computers)) {

            // 添加顺子牌到新的列表中
            List<MahjongTile> rulesTiles =  new ArrayList<>();
            rulesTiles.add(discardedTile);
            rulesTiles.add(secondTile);
            rulesTiles.add(thirdTile);
            playerBase.sortHand();

            // 从手牌中移除被吃的牌
            List<MahjongTile> hand = (playerIndex == 0) ? players[0].getHand() : computers[playerIndex - 1].getHand();
            hand.remove(secondTile);
            hand.remove(thirdTile);

            System.out.println(playerIndex + 1);
            // 输出吃牌信息
            System.out.println("玩家" + (playerIndex + 1) + "吃牌成功：" + rulesTiles);
        } else {
            System.out.println("玩家" + (playerIndex + 1) + "无法吃牌！");
        }
    }


    public boolean hasChowed(MahjongTile discardedTile, int playerIndex, Player[] players, Computers[] computers) {
        return canChow(discardedTile, playerIndex, players, computers);
    }


}
