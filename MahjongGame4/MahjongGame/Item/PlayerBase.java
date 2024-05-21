package Item;

import GameRules.Chow;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PlayerBase {
    protected MahjongDeck deck;
    public List<MahjongTile> hand;
    protected Random random;
    protected Chow chow;

    public Player[] players;

    protected Computers[] computers;

    public PlayerBase(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        hand = new ArrayList<>();
        random = new Random();
        deck = new MahjongDeck();
        chow = new Chow(discardedTile, players, computers, playerIndex);
        this.players = players;
        this.computers = computers;
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
            System.out.print(tile.toString() + " ");
        }
        System.out.println();
    }

    public void sortHand() {
        System.out.print(hand);
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

