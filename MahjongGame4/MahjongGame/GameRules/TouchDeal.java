package GameRules;

import Mahjong.MahjongTile;
import Players.Player;
import java.util.Scanner;

public class TouchDeal {
    private Player[] players;
    private Scanner scanner;
    private int playerIndex;
    private Chow chow;
    private Chow currentChow;
    private MahjongTile lastDiscardedTile;

    public int getPlayerIndex() {
        return this.playerIndex;
    }

    public TouchDeal(Player[] players) {
        this.players = players;
        scanner = new Scanner(System.in);
    }
    public void setLastDiscardedTile(MahjongTile tile) {
        this.lastDiscardedTile = tile;
    }

    public void clearCurrentChow() {
        this.currentChow = null;
    }

    public void dealTile(int playerIndex, MahjongTile tile) {
        players[playerIndex].dealTile(tile);
    }

    public void discardTile(int playerIndex, int previousPlayerIndex) {
        System.out.println("请输入您要出的牌（值 牌面）：");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            System.out.println("输入格式不正确，请重新输入！");
            discardTile(playerIndex, previousPlayerIndex);
            return;
        }

        int value = Integer.parseInt(parts[0]);
        String tileToDiscard = parts[1];

        MahjongTile discardedTile = new MahjongTile(tileToDiscard, value);
        setLastDiscardedTile(discardedTile);

        // 检查是否可以吃
        if (isNeighbor(playerIndex, previousPlayerIndex)) {
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

        boolean found = false;

        for (MahjongTile tile2 : players[playerIndex].getHand()) {
            if (tile2.getValue() == value && tile2.getSuit().equals(tileToDiscard)) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("您手上的牌中没有该牌，请重新输入！");
            discardTile(playerIndex, previousPlayerIndex);
            return;
        }
        MahjongTile tile = new MahjongTile(tileToDiscard, value);
        dealTile(playerIndex, tile);
    }

    private boolean isNeighbor(int playerIndex, int previousPlayerIndex) {
        int numPlayers = players.length;
        int nextPlayerIndex = (previousPlayerIndex + 1) % numPlayers;
        return playerIndex == nextPlayerIndex;
    }
}
