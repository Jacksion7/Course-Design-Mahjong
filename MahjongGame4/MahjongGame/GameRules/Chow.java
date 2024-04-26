package GameRules;

import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Chow {
    private MahjongTile discardedTile;
    private Player[] players;
    private Computers[] computers;
    private MahjongTile secondTile;
    private MahjongTile thirdTile;
    public Chow(MahjongTile discardedTile, Player[] players, Computers[] computers) {
        this.discardedTile = discardedTile;
        this.players = players;
        this.computers = computers;
    }

    /*
    这个方法是查找当前回合玩家的下家的?还是用来查找上家的？
    正常来说，应该是玩家出牌，然后下家判断上家发的牌能不能吃。
    所以应该是到下家摸牌之前，判断能不能进行吃牌操作。

     */
    public int getNextPlayer(int playerIndex) {
        return (playerIndex + 1) % 4;
    }

    //这个方法应该是判断是否能吃牌的
    //是下家在摸牌之前判断能不能吃牌！！
    public boolean canChow(MahjongTile discardedTile, int playerIndex, Player[] players, Computers[] computers) {
        int value = discardedTile.getValue();
        String suit = discardedTile.getSuit();

        System.out.println("value suit: " + value + suit);

        int nextPlayer = getNextPlayer(playerIndex);
        System.out.println("nextPlayer: " + nextPlayer);

        // 获取当前玩家或电脑的手牌
        List<MahjongTile> hand = (nextPlayer == 0) ? players[nextPlayer].getHand() : computers[nextPlayer - 1].getHand();
        System.out.println("Hand: " + hand);

        for (MahjongTile tile : hand) {
            if (tile.getSuit().equals(suit)) {
                // 如果值为1，查找是否有值为2和3的牌
                if (value == 1 && tile.getValue() == 2 && tileExists(3, suit, nextPlayer, players, computers)) {
                    secondTile = new MahjongTile(suit, 2);
                    thirdTile = new MahjongTile(suit, 3);
                    return true;
                }
                // 如果值为9，查找是否有值为8和7的牌
                else if (value == 9 && tile.getValue() == 8 && tileExists(7, suit, nextPlayer, players, computers)) {
                    secondTile = new MahjongTile(suit, 8);
                    thirdTile = new MahjongTile(suit, 7);
                    return true;
                }
                // 如果值为2，分两部分查，第一查（1,3），第二查（3,4）
                else if (value == 2) {
                    if (tile.getValue() == 1 && tileExists(3, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 1);
                        thirdTile = new MahjongTile(suit, 3);
                    } else if (tile.getValue() == 3 && tileExists(4, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 3);
                        thirdTile = new MahjongTile(suit, 4);
                    }
                    return true;
                }
                // 如果值为8，分两部分查，第一查（7,9），第二查（6,7）
                else if (value == 8) {
                    if (tile.getValue() == 7 && tileExists(9, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 7);
                        thirdTile = new MahjongTile(suit, 9);
                    } else if (tile.getValue() == 6 && tileExists(7, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, 6);
                        thirdTile = new MahjongTile(suit, 7);
                    }
                    return true;
                }
                // 如果值为3~7，分三部分查，第一查（value-1和value-2），第二查（value+1和value+2），第三查（value-1和value+1）
                else if (value >= 3 && value <= 7) {
                    if (tile.getValue() == value - 1 && tileExists(value - 2, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, value - 1);
                        thirdTile = new MahjongTile(suit, value - 2);
                    } else if (tile.getValue() == value + 1 && tileExists(value + 2, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, value + 1);
                        thirdTile = new MahjongTile(suit, value + 2);
                    } else if (tile.getValue() == value - 1 && tileExists(value + 1, suit, nextPlayer, players, computers)) {
                        secondTile = new MahjongTile(suit, value - 1);
                        thirdTile = new MahjongTile(suit, value + 1);
                    }
                    return true;
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
            // 从手牌中移除被吃的牌
            List<MahjongTile> hand = (playerIndex == 0) ? players[0].getHand() : computers[playerIndex - 1].getHand();
            hand.remove(secondTile);
            hand.remove(thirdTile);

            // 输出吃牌信息
            System.out.println("玩家吃牌成功：" + rulesTiles);
        } else {
            System.out.println("当前玩家无法吃牌！");
        }
    }

}
