package Models.Players;

import Models.GameRules.Chow;
import Models.MahjongTile;

import java.util.Scanner;


public class Player extends PlayerBase {
    public Chow chow;
    private Scanner scanner;

    public Player(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        super(discardedTile, players, computers, playerIndex);
        this.scanner = new Scanner(System.in);
        chow = new Chow(discardedTile, players, computers, playerIndex);
    }

    public void dealPlayerTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
        System.out.println();
    }

    /*
    public boolean promptPlayerToChow() {
        System.out.println("Do you want to chow? (Y/N): ");
        String choice = scanner.nextLine();
        return choice.equalsIgnoreCase("Y");
    }

     */


}
