package Item;

import GameRules.Chow;
import GameRules.TouchDeal;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;
import Mahjong.PlayerManager;
import Players.Computers;
import Players.Player;

public abstract class AbstractMahjongGame implements Game2 {
    public Player[] players;
    protected Computers[] computers;
    protected MahjongDeck deck;
    protected TouchDeal touchDeal;
    protected Chow chow;
    protected boolean gameOver = false;
    protected MahjongTile discardTile;
    protected int playerIndex;

    public AbstractMahjongGame() {
        PlayerManager playerManager = new PlayerManager();
        players = playerManager.getPlayers();
        computers = playerManager.getComputers();
        deck = new MahjongDeck();
        chow = new Chow(discardTile, players, computers, playerIndex);
    }

//    @Override
//    public void playGame() {
//        dealTiles();
//
//        while (!isGameOver()) {
//            testDrawAndDiscard();
//            playComputerTurn();
//        }
//    }

    //protected abstract void dealTiles();

    protected abstract void testDrawAndDiscard();

    protected abstract void updatePlayerHands();

    @Override
    public boolean isGameOver() {
        for (int i = 1; i < 4; i++) {
            if (players[0].getHand().isEmpty()) {
                return gameOver = true;
            }
        }
        return gameOver = false;
    }

    public void playComputerTurn() {
        for (int i = 0; i < 3; i++) {
            System.out.println("电脑 " + (i + 1) + " 的回合：");
            updatePlayerHands();
            computers[i].computerPlay(i + 1);
            System.out.println();
        }
    }
}