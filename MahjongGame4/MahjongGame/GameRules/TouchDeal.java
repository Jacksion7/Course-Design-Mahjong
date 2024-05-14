package GameRules;

import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;

import java.util.Scanner;


public class TouchDeal {
    private Player[] players;
    private Player player;
    private Computers[] computers;
    private Scanner scanner;
    private Chow chow;
    private MahjongDeck deck;
    private int playerIndex;
    private MahjongTile discardedTile;

    private Peng peng;

    public TouchDeal(Player[] players, Computers[] computers) {
        this.players = players;
        this.computers = computers;
        this.scanner = new Scanner(System.in);
        this.chow = new Chow(discardedTile, players, computers, playerIndex);
        this.peng = new Peng(discardedTile, players, computers);
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

        int value;
        try {
            value = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            System.out.println("牌值必须为整数，请重新输入！");
            discardTile();
            return;
        }

        String suit = parts[1];
        MahjongTile discardedTile = new MahjongTile(suit, value);

        if (!isTileInHand(discardedTile, players[0])) {
            System.out.println("您手上的牌中没有该牌，请重新输入！");
            discardTile();
            return;
        }

        players[0].dealPlayerTile(discardedTile);
        System.out.println("Player 出了一张牌：" + discardedTile);
        System.out.println();

//        pengTile(playerIndex);
        chowTile(discardedTile, playerIndex);

    }

    private boolean isTileInHand(MahjongTile tile, Player player) {
        for (MahjongTile tile2 : player.getHand()) {
            if (tile2.getValue() == tile.getValue() && tile2.getSuit().equals(tile.getSuit())) {
                return true;
            }
        }
        return false;
    }
    private void pengTile(int playerIndex){
        peng.pengTile(playerIndex);
    }

    private void chowTile(MahjongTile discardedTile, int playerIndex) {
        chow.chowTile(discardedTile, playerIndex);
    }

    /*
    public void playerTurn() {
        // 正常进行摸牌
        MahjongTile tileDrawn = deck.drawTile();
        System.out.println("You drew a tile: " + tileDrawn);
        player.drawTile(tileDrawn);

        // 看看玩家是否想要吃牌
        if (player.promptPlayerToChow()) {
            // 玩家选择吃牌
            if (chow.canChow(discardedTile, playerIndex, players, computers)) {
                chow.chowTile(discardedTile, playerIndex);
                return; // 吃牌后不进行出牌操作
            } else {
                System.out.println("You cannot chow this tile.");
            }
        }

        // 如果玩家没有吃牌，正常进行出牌操作
        discardTile();
    }

     */


}
