package Mahjong;

import GameRules.TouchDeal;
import Item.AbstractMahjongGame;
import Players.Player;
import UI.PlayerListener;
import ucd.comp2011j.engine.Game;

import java.util.List;


public class MahjongGame extends AbstractMahjongGame  {

//
//    public List<MahjongTile> Player_initial_dealTiles;
//
//    @Override
//    protected void dealTiles() {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 13; j++) {
//                MahjongTile tile = deck.drawTile();
//                if (i == 0) {
//                    players[0].drawTile(tile);
//                } else {
//                    computers[i - 1].drawTile(tile);
//                }
//            }
//        }
//        Player_initial_dealTiles=players[0].hand;
//    }
//
//    public List<MahjongTile> getPlayer_initial_dealTiles() {
//        return Player_initial_dealTiles;
//    }

    @Override
    protected void testDrawAndDiscard() {
        System.out.println("你的回合：");
        updatePlayerHands();
        MahjongTile tile = deck.drawTile();
        System.out.println("摸到的牌: " + tile);
        players[0].drawTile(tile);
        touchDeal = new TouchDeal(players, computers);
        touchDeal.discardTile();
    }

    @Override
    protected void updatePlayerHands() {
        System.out.println("Player's hand:");
        players[0].displayHand();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println("Computer " + (i + 1) + "'s hand:");
            computers[i].displayHand();
            System.out.println();
        }
        playerIndex = (playerIndex + 1) % 4;
    }


    @Override
    public void playGame() {

    }
}
