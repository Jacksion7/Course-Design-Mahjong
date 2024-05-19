package Players;

import GameRules.TouchDeal;
import Item.PlayerBase;
import Mahjong.MahjongTile;



public class Player extends PlayerBase {
    private TouchDeal touchDeal;
    public Player(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        super();
        this.discardedTile = discardedTile;
        this.players = players;
        this.computers = computers;
        this.playerIndex = playerIndex;
        touchDeal = new TouchDeal(discardedTile, players, computers);
    }

    //删除手牌方法
    public void dealPlayerTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
        System.out.println();
    }


    public void playerPlay(MahjongTile discardedTile) {
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
        System.out.println("判断能不能吃的牌：" + discardedTile);

        if (discardedTile != null && chow.isChow(discardedTile, playerIndex, players, computers)) {
            System.out.println("你已吃牌，进行出牌");
            touchDeal.discardTile(playerIndex);//进行出牌操作
            //chow.isChow() = false;
        }
        System.out.println("你没有进行任何操作，进行出牌");
        MahjongTile tile = deck.drawTile();//摸牌
        System.out.println("摸到的牌: " + tile);
        players[0].drawTile(tile);//将牌放入手牌
        touchDeal.discardTile(playerIndex);//进行出牌操作
        //discardedTile = touchDeal.getDiscardedTile();//获取出牌的牌
    }

}
