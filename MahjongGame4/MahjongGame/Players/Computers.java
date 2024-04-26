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
    private Player[] players;
    private Computers[] computers;
    private Chow chow;
    private MahjongDeck deck;
    private TouchDeal touchDeal;
    private Random random;
    private MahjongTile discardedTile;

    public Computers(MahjongTile discardedTile, Player[] players, Computers[] computers) {
        this.players = players;
        this.computers = computers;
        this.hand = new ArrayList<>();
        this.discardedTile = discardedTile;
        this.random = new Random();
        this.deck = new MahjongDeck();
        this.chow = new Chow(discardedTile, players, computers);
    }
    public void drawTile(MahjongTile tile) {
        hand.add(tile);
    }
    public List<MahjongTile> getHand() {
        return hand;
    }
    public MahjongTile getDiscardedTile() {
        return discardedTile;
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
            //在这里写一个用来承接上家发的牌的代码
            MahjongTile discardedTile = getDiscardedTile();
            if (chow.canChow(discardedTile, playerIndex, players, computers)) {
                chow.chowTile(discardedTile, playerIndex);
            }
            System.out.println(chow.canChow(discardedTile, playerIndex, players, computers));

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
