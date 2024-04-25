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
    private MahjongDeck deck;
    private int playerIndex;

    public TouchDeal(Player[] players, Computers[] computers) {
        this.players = players;
        this.computers = computers;
        player = new Player(discardedTile);
        computer = new Computers(discardedTile);
        deck = new MahjongDeck();
        scanner = new Scanner(System.in);
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

        /*
        // 检查是否可以吃
        if (isNeighbor(playerIndex)) {
            Player previousPlayer = players[previousPlayerIndex];
            if (previousPlayer.chow.canChow(value, tileToDiscard, players[playerIndex])) {
                System.out.println("玩家 " + (previousPlayerIndex + 1) + " 打出的牌可以吃！");
                // 提示玩家进行吃操作
                System.out.println("您可以选择吃牌或继续出牌（输入 c 进行吃牌，输入其他任意字符继续出牌）：");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("c")) {
                    System.out.println("您选择了吃牌：" + discardedTile);

                    // 调用玩家的吃牌方法
                    players[playerIndex].chow.chowTile(discardedTile,  players[playerIndex]);
                    clearCurrentChow();
                    System.out.println("吃牌成功！");
                    return;
                } else {
                    System.out.println("您选择的吃牌方式无效！");
                }
            }
        }

         */

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
        System.out.println("Player 出了一张牌：" + discardedTile);
        System.out.println();
        //DiscardedTile(discardedTile);
        //MahjongTile tile = new MahjongTile(tileToDiscard, value);
        dealTile(discardedTile);
    }

    private boolean isNeighbor(int playerIndex, int previousPlayerIndex) {
        int numPlayers = players.length;
        int nextPlayerIndex = (previousPlayerIndex + 1) % numPlayers;
        return playerIndex == nextPlayerIndex;
    }
}
