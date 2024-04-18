package Players;

import GameRules.Chow;
import GameRules.TouchDeal;
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
    private Chow chow;
    private TouchDeal touchDeal;
    private MahjongTile firstTile;
    private MahjongTile secondTile;
    private MahjongTile thirdTile;

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
        //创建一个Chow对象，用于判断是否可以吃牌
        chow = new Chow(firstTile, secondTile, thirdTile);
        return chow.canChow(this, new MahjongTile(suit, value));
    }

    public void chowTile(int chowIndex, MahjongTile tile) {
        Chow chow = touchDeal.getCurrentChow();
        if (chow != null && chowIndex >= 0 && chowIndex < 3) {
            // 检查是否可以吃牌
            if (chow.isValidChow(tile)) {
                // 吃牌成功，将顺子牌加入手牌
                hand.add(chow.getFirstTile());
                hand.add(chow.getSecondTile());
                hand.add(chow.getThirdTile());
                // 从手牌中移除被吃的牌
                hand.remove(tile);
                // 清空当前的顺子牌
                touchDeal.clearCurrentChow();
                System.out.println("吃牌成功！");
            } else {
                System.out.println("无法吃牌！");
            }
        } else {
            System.out.println("当前没有可以吃的牌！");
        }
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
