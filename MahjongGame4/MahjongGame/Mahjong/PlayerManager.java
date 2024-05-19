package Mahjong;

import GameRules.TouchDeal;
import Players.Computers;
import Players.Player;

import java.util.Random;

public class PlayerManager {
    private Player[] players;
    private Computers[] computers;
    private MahjongTile discardedTile;
    private int playerIndex;

    public PlayerManager() {
        players = new Player[1];//创建一位玩家
        computers = new Computers[3];//创建三位电脑
        setPlayer();
        //playerIndex = dice();
    }

    public void setPlayer() {
        players[0] = new Player(discardedTile, players, computers, playerIndex);
        for (int i = 0; i < 3; i++) {
            computers[i] = new Computers(discardedTile, players, computers, playerIndex);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Computers[] getComputers() {
        return computers;
    }

    //创建骰子
    public int dice() {
        Random random = new Random();
        int randomNumber = random.nextInt(21) + 4;
        return randomNumber % 4;
    }

}
