package GameRules;

import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;

import java.util.Scanner;


public class TouchDeal {
    private Player[] players;
    //private Player player;
    private Computers[] computers;
    private Scanner scanner;
    private Chow chow;
    private MahjongDeck deck;
    private int playerIndex;
    private MahjongTile discardedTile;

    public TouchDeal(MahjongTile discardedTile, Player[] players, Computers[] computers) {
        this.players = players;
        this.computers = computers;
        this.discardedTile = discardedTile;
        this.scanner = new Scanner(System.in);
        deck = new MahjongDeck();
    }

    //出牌方法
    public MahjongTile discardTile() {
        if (playerIndex == 0) { //玩家出牌
            System.out.println("请输入您要出的牌（值 牌面）：");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                System.out.println("输入格式不正确，请重新输入！");
                discardTile();
            }

            int value = 0;
            try {
                value = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println("牌值必须为整数，请重新输入！");
                discardTile();
            }

            String suit = parts[1];
            MahjongTile discardTile = new MahjongTile(suit, value);

            if (!isTileInHand(discardTile, players[0])) {
                System.out.println("您手上的牌中没有该牌，请重新输入！");
                discardTile();
            }

            players[0].dealPlayerTile(discardTile);
            System.out.println("Player 出了一张牌：" + discardTile);
            System.out.println();

            discardedTile = discardTile;
        } else { //电脑出牌

        }

        System.out.println(discardedTile + "111111111111");

        return discardedTile;
    }

    private boolean isTileInHand(MahjongTile tile, Player player) {
        for (MahjongTile tile2 : player.getHand()) {
            if (tile2.getValue() == tile.getValue() && tile2.getSuit().equals(tile.getSuit())) {
                return true;
            }
        }
        return false;
    }

    //吃牌方法写在PlayerBase中，这里就不做调用了

//    private void touchTile(MahjongTile tile) {
//        player.drawTile(tile);
//    }

    public void firstRoundHandTile() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                MahjongTile tile = deck.drawTile();
                if (i == 0) {
                    players[0].drawTile(tile);
                } else {
                    computers[i - 1].drawTile(tile);
                }
            }
        }
    }

    public MahjongTile getDiscardedTile() {
        return discardedTile;
    }
}
