package Players;

import GameRules.Chow;
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
    public Chow chow;

    public Player(MahjongTile discardedTile, Player[] players) {
        hand = new ArrayList<>();
        random = new Random();
        deck = new MahjongDeck();
        chow = new Chow(discardedTile, players, this);
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
        System.out.println();
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
            /*
            // 判断是否能够吃牌
            MahjongTile tileToPlay = null;
            Player nextPlayer = chow.getNextPlayer(this);
            for (MahjongTile tileInHand : nextPlayer.getHand()) {
                if (chow.canChow(tileInHand.getValue(), tileInHand.getSuit(), nextPlayer)) {
                    // 如果可以吃牌，则选择一张牌进行吃牌操作
                    tileToPlay = tileInHand;
                    break;
                }
            }

            if (tileToPlay != null) {
                // 如果存在可以吃的牌，则执行吃牌操作
                System.out.println("Player 选择了吃牌：" + tileToPlay);
                // 执行吃牌操作
                chow.chowTile(tileToPlay, this);
                hand.remove(tileToPlay);
            } else {
                // 如果不能吃牌，则随机选择一张手牌进行出牌
                tileToPlay = hand.get(random.nextInt(hand.size()));
                System.out.println("Player 出了一张牌：" + tileToPlay);
                hand.remove(tileToPlay);
            }
            */
            // 摸牌
            MahjongTile tileDrawn = deck.drawTile();
            System.out.println("Player 摸到了一张牌：" + tileDrawn);
            drawTile(tileDrawn);

            MahjongTile tileToPlay = hand.get(random.nextInt(hand.size()));
            hand.remove(tileToPlay);
            System.out.println("Player 出了一张牌：" + tileToPlay);

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
