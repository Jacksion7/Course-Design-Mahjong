package GameRules;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Players.Player;
import java.util.Scanner;


public class TouchDeal {
    private MahjongDeck deck;
    private Player[] players;
    private Scanner scanner;
    private int playerIndex;
    private Chow currentChow;
    public int getPlayerIndex() {
        return this.playerIndex;
    }


    public TouchDeal(Player[] players) {
        this.deck = new MahjongDeck();
        this.players = players;
        scanner = new Scanner(System.in);
    }
    public void setCurrentChow(Chow chow) {
        this.currentChow = chow;
    }

    public Chow getCurrentChow() {
        return currentChow;
    }

    public void clearCurrentChow() {
        this.currentChow = null;
    }
    public void drawTile(MahjongTile tile) {
        playerIndex = getPlayerIndex();
        players[playerIndex].drawTile(tile);
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

        // 检查是否可以吃
        if (isNeighbor(playerIndex, previousPlayerIndex)) {
            Player previousPlayer = players[previousPlayerIndex];
            if (previousPlayer.canChow(value, tileToDiscard)) {
                System.out.println("玩家 " + (previousPlayerIndex + 1) + " 打出的牌可以吃！");
                // 提示玩家进行吃操作
            }
        }

        boolean found = false;
        for (MahjongTile tile : players[playerIndex].getHand()) {
            if (tile.getValue() == value && tile.getSuit().equals(tileToDiscard)) {
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
        if (currentChow != null && currentChow.canChow(players[playerIndex], tile)) {
            System.out.println("您可以进行吃操作。");
            // 进行吃操作
            players[playerIndex].drawTile(tile);
            players[playerIndex].drawTile(currentChow.getFirstTile());
            players[playerIndex].drawTile(currentChow.getSecondTile());
            clearCurrentChow();
            System.out.println("吃牌成功！");
        } else {
            // 如果不能吃，则进行普通的出牌操作
            dealTile(playerIndex, tile);
        }
    }

    private boolean isNeighbor(int playerIndex, int previousPlayerIndex) {
        return (playerIndex + 1) % players.length == previousPlayerIndex;
    }


}
