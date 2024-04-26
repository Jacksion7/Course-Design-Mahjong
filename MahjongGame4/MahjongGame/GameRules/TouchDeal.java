package GameRules;

import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;

import java.util.Scanner;
import java.util.List;
import java.util.Iterator;


public class TouchDeal {
    private Player[] players;
    private Player player;
    private Computers[] computers;
    private Computers computer;
    private Scanner scanner;
    private MahjongTile discardedTile;
    private Chow chow;
    private MahjongDeck deck;
    private int playerIndex;

    public TouchDeal(Player[] players, Computers[] computers) {
        this.players = players;
        this.computers = computers;
        this.deck = new MahjongDeck();
        this.scanner = new Scanner(System.in);
        this.chow = new Chow(discardedTile, players, computers);
    }

    public void DiscardedTile(MahjongTile tile) {
        this.discardedTile = tile;
    }

    public void dealTile(MahjongTile tile) {
        players[0].dealPlayerTile(tile);
    }


    public void discardTile() {
        System.out.println("请输入您要出的牌（值 牌面）：");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            System.out.println("输入格式不正确，请重新输入！");
            discardTile();
            return;
        }

        int value = Integer.parseInt(parts[0]);
        String suit = parts[1];

        MahjongTile discardedTile = new MahjongTile(suit, value);

        boolean found = false;
        for (MahjongTile tile2 : players[0].getHand()) {
            if (tile2.getValue() == value && tile2.getSuit().equals(suit)) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("您手上的牌中没有该牌，请重新输入！");
            discardTile();
            return;
        }

        // 检查玩家是否可以吃牌
        if (chow.canChow(discardedTile, playerIndex, players, computers)) {
            chow.chowTile(discardedTile, playerIndex);
        }

        System.out.println("Player 出了一张牌：" + discardedTile);
        System.out.println();
        dealTile(discardedTile);
    }
}
