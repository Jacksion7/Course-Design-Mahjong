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
    public int getPlayerIndex() {
        return this.playerIndex;
    }


    public TouchDeal(Player[] players) {
        this.deck = new MahjongDeck();
        this.players = players;
        scanner = new Scanner(System.in);

    }
    public void drawTile(MahjongTile tile) {
        playerIndex = getPlayerIndex();
        players[playerIndex].drawTile(tile);
    }

    public void dealTile(int playerIndex, MahjongTile tile) {
        players[playerIndex].dealTile(tile);
    }

    public void discardTile(int playerIndex) {
        System.out.println("请输入您要出的牌（值 牌面）：");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            System.out.println("输入格式不正确，请重新输入！");
            discardTile(playerIndex);
            return;
        }

        int value = Integer.parseInt(parts[0]);
        String tileToDiscard = parts[1];
        //System.out.println(parts[0] + " " + parts[1]);

        boolean found = false;
        for (MahjongTile tile : players[playerIndex].getHand()) {
            if (tile.getValue() == value && tile.getSuit().equals(tileToDiscard)) {
                found = true;
                break;
            }
        }
        if  (!found) {
            System.out.println("您手上的牌中没有该牌，请重新输入！");
            discardTile(playerIndex);
            return;
        }

        MahjongTile tile = new MahjongTile(tileToDiscard, value);
        dealTile(0, tile);

    }



}
