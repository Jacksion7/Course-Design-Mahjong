package Mahjong;

import Players.Computers;
import Players.Player;

public class PlayerManager {

    private Player[] players;
    private Computers[] computers;
    private MahjongDeck deck;
    private MahjongTile discardTile;
    private int playerIndex;


    public PlayerManager() {
        deck = new MahjongDeck();
        players = new Player[1];
        computers = new Computers[3];
        players[0] = new Player(discardTile, players, computers, playerIndex);
        for (int i = 0; i < 3; i++) {
            computers[i] = new Computers(discardTile, players, computers, playerIndex);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Computers[] getComputers() {
        return computers;
    }

    public void dealTiles() {
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
}
