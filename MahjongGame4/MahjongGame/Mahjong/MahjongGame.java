package Mahjong;

import GameRules.*;
import Item.AbstractMahjongGame;


public class MahjongGame extends AbstractMahjongGame {

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
        //playerIndex = (playerIndex + 1) % 4;
    }




}
