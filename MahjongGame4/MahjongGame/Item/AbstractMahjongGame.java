package Item;

import GameRules.*;
import Mahjong.*;
import Players.*;

public abstract class AbstractMahjongGame implements GameReady {

    protected Player[] players;

    protected Computers[] computers;
    protected MahjongDeck deck;
    protected TouchDeal touchDeal;
    protected Win win;
    protected Peng peng;
    protected Gang gang;
    protected Chow chow;
    protected boolean gameOver = false;
    protected MahjongTile discardedTile;
    protected int playerIndex;

    //这个方法只是对于玩家的一个回合的操作 ↓
    protected abstract void updatePlayerHands();

    public AbstractMahjongGame() {
        PlayerManager playerManager = new PlayerManager();
        players = playerManager.getPlayers();
        computers = playerManager.getComputers();
        deck = new MahjongDeck();
        touchDeal = new TouchDeal(discardedTile, players, computers);
        chow = new Chow(discardedTile, players, computers, playerIndex);
        peng = new Peng(discardedTile, players, computers);
        gang = new Gang(discardedTile, players, computers);
        win = new Win(players, computers);
    }

    @Override
    public void playGame() {
        touchDeal.firstRoundHandTile();
        while (!isGameOver()) {
            playerTurn(discardedTile);
            playComputerTurn(discardedTile);
        }
    }

    @Override
    public boolean isGameOver() {
        for (int i = 1; i < 4; i++) {
            if (players[0].getHand().isEmpty()) {
                return gameOver = true;
            }
        }
        return gameOver = false;
    }

    protected void playerTurn(MahjongTile discardedTile) {
        System.out.println("你的回合：");
        updatePlayerHands();//表示手牌
        players[0].playerPlay(discardedTile);
        System.out.println();
    }

    protected void playComputerTurn(MahjongTile discardedTile) {
        for (int i = 0; i < 3; i++) {
            System.out.println("电脑 " + (i + 1) + " 的回合：");
            updatePlayerHands();
            computers[i].computerPlay(discardedTile, i + 1);
            System.out.println();
        }
    }
}