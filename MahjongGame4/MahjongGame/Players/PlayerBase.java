package Players;

import GameRules.Chow;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PlayerBase {
    protected MahjongDeck deck;
    protected List<MahjongTile> hand;
    protected Random random;
    protected Chow chow;

    public PlayerBase(MahjongTile discardedTile, Player[] players, Computers[] computers) {
        hand = new ArrayList<>();
        random = new Random();
        deck = new MahjongDeck();
        chow = new Chow(discardedTile, players, computers);
    }

    public List<MahjongTile> getHand() {
        return hand;
    }

    public void drawTile(MahjongTile tile) {
        hand.add(tile);
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

