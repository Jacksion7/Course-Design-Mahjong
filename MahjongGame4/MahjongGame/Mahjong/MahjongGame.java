package Mahjong;

import GameRules.*;
import Item.AbstractMahjongGame;


public class MahjongGame extends AbstractMahjongGame {
    TouchDeal touchDeal = new TouchDeal(discardedTile, players, computers);

    //这个方法只是对于玩家的一个回合的操作 ↓
    @Override
    protected void playerTurn() {
        System.out.println("你的回合：");
        updatePlayerHands();//表示手牌
//        if (win.isWin()) {
//            if (gang.isGang()) {
//                if (peng.isPeng()) {
//                    if (chow.isChow()) {
//                        System.out.println("你已吃牌，进行出牌");
//                        touchDeal.discardTile();//进行出牌操作
//                        //chow.isChow() = false;
//                    }
//                    System.out.println("你已碰牌，进行出牌");
//                    touchDeal.discardTile();//进行出牌操作
//                    //peng.isPeng() = false;
//                }
//                System.out.println("你已杠牌，进行出牌");
//                touchDeal.discardTile();//进行出牌操作
//                //gang.isGang() = false;
//            }
//            System.out.println("你已胡牌");
//        }
        System.out.println("玩家能不能吃牌");
        if (chow.isChow(discardedTile)) {
            System.out.println("你已吃牌，进行出牌");
            touchDeal.discardTile();//进行出牌操作
            //chow.isChow() = false;
        }
        System.out.println("你没有进行任何操作，进行出牌");
        MahjongTile tile = deck.drawTile();//摸牌
        System.out.println("摸到的牌: " + tile);
        players[0].drawTile(tile);//将牌放入手牌
        touchDeal.discardTile();//进行出牌操作
        discardedTile = touchDeal.getDiscardedTile();//获取出牌的牌
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
        //playerIndex = (playerIndex + 1) % 4;
    }




}
