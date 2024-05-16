package Item;

import GameRules.*;
import Mahjong.*;
import Players.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PlayerBase {
    protected MahjongDeck deck;
    protected List<MahjongTile> hand;
    protected Random random;
    protected TouchDeal touchDeal;
    protected Chow chow;
    protected Peng peng;
    protected Gang gang;
    protected Win win;

    protected Player[] players;

    protected Computers[] computers;
    protected MahjongTile discardedTile;
    protected int playerIndex;

    public PlayerBase() {
        hand = new ArrayList<>();
        random = new Random();
        deck = new MahjongDeck();
        touchDeal = new TouchDeal(discardedTile, players, computers);
        chow = new Chow(discardedTile, players, computers, playerIndex);
        peng = new Peng(discardedTile, players, computers);
        gang = new Gang(discardedTile, players, computers);
        win = new Win(players, computers);
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
        return switch (suit) {
            case "万" -> 0;
            case "条" -> 1;
            case "筒" -> 2;
            case "东" -> 3;
            case "西" -> 4;
            case "南" -> 5;
            case "北" -> 6;
            case "中" -> 7;
            case "发" -> 8;
            default -> 9;
        };
    }
}

