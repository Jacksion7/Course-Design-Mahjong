package Players;

import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
    public Random random;
    private List<MahjongTile> hand;
    private MahjongDeck deck;
    public Player() {
        hand = new ArrayList<>();
        random = new Random();
        deck = new MahjongDeck();
    }
    public List<MahjongTile> getHand() {
        return hand;
    }
    public void drawTile(MahjongTile tile) {
        hand.add(tile);
    }

    public void dealTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
            System.out.println("Player 1" +" 出了一张牌：" + tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
    }

    public void displayHand() {
        sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile + " ");
        }
    }

    public void computerPlay() {
        // 电脑玩家的回合
        if (!hand.isEmpty()) {
            // 摸牌
            MahjongTile tileDrawn = deck.drawTile();
            System.out.println("Player 摸到了一张牌：" + tileDrawn);
            drawTile(tileDrawn);

            // 判断是否能够吃牌
            MahjongTile tileToPlay = null;
            for (MahjongTile tileInHand : hand) {
                if (canChow(tileInHand.getValue(), tileInHand.getSuit())) {
                    // 如果可以吃牌，则选择一张牌进行吃牌操作
                    tileToPlay = tileInHand;
                    break;
                }
            }

            if (tileToPlay != null) {
                // 如果存在可以吃的牌，则执行吃牌操作
                System.out.println("Player 选择了吃牌：" + tileToPlay);
                // 执行吃牌操作
                canChow(tileToPlay.getValue(), tileToPlay.getSuit());
                hand.remove(tileToPlay);
            } else {
                // 如果不能吃牌，则随机选择一张手牌进行出牌
                tileToPlay = hand.get(random.nextInt(hand.size()));
                System.out.println("Player 出了一张牌：" + tileToPlay);
                hand.remove(tileToPlay);
            }
        } else {
            System.out.println("玩家手牌已空，无法出牌。");
        }
    }


    private void sortHand() {
        Collections.sort(hand, (t1, t2) -> {
            int suitOrder1 = getSuitOrder(t1.getSuit());
            int suitOrder2 = getSuitOrder(t2.getSuit());
            if (suitOrder1 != suitOrder2) {
                return suitOrder1 - suitOrder2;
            }
            return t1.getValue() - t2.getValue();
        });
    }

    public boolean canChow(int value, String suit) {
        if (suit == null || suit.isEmpty()) {
            return false; // 如果牌面字符串为空，则无法吃牌
        }
        //System.out.println("Suit: " + suit);

        // 检查牌面字符串长度，以确保其足够长
        if (suit.length() < 2) {
            return false; // 牌面字符串长度不够，无法解析牌值
        }

        // 将牌面字符串转换为相应的数字
        int tileValue = Integer.parseInt(suit.substring(0, suit.length() - 1));

        // 判断是否可以吃
        if (value >= 2 && value <= 8) {
            // 如果牌值在2到8之间，则可以吃牌
            // 判断是否存在能够组成顺子的牌
            boolean containsValue1 = false;
            boolean containsValue2 = false;
            // 判断是否存在 value - 1 和 value - 2 的牌
            for (MahjongTile tile : hand) {
                if (tile.getValue() == value - 1 && tile.getSuit().equals(suit)) {
                    containsValue1 = true;
                } else if (tile.getValue() == value - 2 && tile.getSuit().equals(suit)) {
                    containsValue2 = true;
                }
            }
            return containsValue1 && containsValue2;
        }

        // 特殊处理1、9万和1、9条和1、9筒
        if (value == 1 || value == 9) {
            // 如果当前牌值为1或者9，则只需判断是否存在 value + 1 和 value + 2 的牌
            boolean containsValue2 = false;
            boolean containsValue3 = false;
            for (MahjongTile tile : hand) {
                if (tile.getValue() == value + 1 && tile.getSuit().equals(suit)) {
                    containsValue2 = true;
                } else if (tile.getValue() == value + 2 && tile.getSuit().equals(suit)) {
                    containsValue3 = true;
                }
            }
            return containsValue2 && containsValue3;
        }

        return false;
    }

    private int getSuitOrder(String suit) {
        switch (suit) {
            case "万":
                return 0;
            case "条":
                return 1;
            case "筒":
                return 2;
            case "东":
                return 3;
            case "西":
                return 4;
            case "南":
                return 5;
            case "北":
                return 6;
            case "中":
                return 7;
            case "发":
                return 8;
            default:
                return 9;
        }
    }

}
