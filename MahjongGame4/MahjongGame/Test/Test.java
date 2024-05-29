package Test;

import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;

public class Test {
    public static void main(String[] args) {
//        testMahjongTile();
//        testMahjongDeck();
    }

    public static void dealMahjongTile(){

    }
    public static void testMahjongTile() {
        MahjongTile tile = new MahjongTile(1, "万");
        System.out.println("Suit: " + tile.getSuit());
        System.out.println("Value: " + tile.getValue());
        System.out.println("Tile: " + tile);
        System.out.println();
    }

    public static void testMahjongDeck() {
        MahjongDeck deck = new MahjongDeck();
        System.out.println("Number of tiles in the deck: " + deck.getTilesLibrary().size());
        System.out.println("Tiles in the deck:");
        for (MahjongTile tile : deck.getTilesLibrary()) {
            System.out.println(tile);
        }
    }




}
