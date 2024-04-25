package Players;

import Mahjong.MahjongTile;
import GameRules.Chow;
import Mahjong.MahjongDeck;
import GameRules.TouchDeal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Computers {
    private List<MahjongTile> hand;
    private Player player;
    private Chow chow;
    private MahjongDeck deck;
    private TouchDeal touchDeal;
    private Random random;
    private MahjongTile discardedTile;
    public Computers(MahjongTile discardedTile) {
        this.hand = new ArrayList<>();
        this.discardedTile = discardedTile;
        this.random = new Random();
        this.deck = new MahjongDeck();
    }
    public void drawTile(MahjongTile tile) {
        hand.add(tile);
    }
    public List<MahjongTile> getHand() {
        return hand;
    }
    public void displayHand() {
        sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile + " ");
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

    //这是排序手牌的依据
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
    public void computerPlay(int playerIndex) {

        // 电脑玩家的回合
        if (true) {

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
            System.out.println("Player " + playerIndex + "摸到了一张牌：" + tileDrawn);
            drawTile(tileDrawn);

            MahjongTile tileToPlay = getHand().get(random.nextInt(getHand().size()));
            hand.remove(tileToPlay);
            System.out.println("Player " + playerIndex + "出了一张牌：" + tileToPlay);

        } else {
            System.out.println("玩家手牌已空，无法出牌。");


        }
    }


}
