package Players;

import Item.PlayerBase;
import Mahjong.MahjongTile;



public class Player extends PlayerBase {
    //private Scanner scanner;

    public Player(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        super();
        this.discardedTile = discardedTile;
        this.players = players;
        this.computers = computers;
        this.playerIndex = playerIndex;
    }

    //删除手牌方法
    public void dealPlayerTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
            System.out.println("从手牌中移除: " + tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
        System.out.println();
    }



}
