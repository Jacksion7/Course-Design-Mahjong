package Players;

import Item.PlayerBase;
import Mahjong.MahjongTile;


public class Computers extends PlayerBase {

    public Computers(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        super(discardedTile, players, computers, playerIndex);
    }

    public void computerPlay(int playerIndex) {
        MahjongTile tileDrawn = this.deck.drawTile();
        System.out.println("Player " + playerIndex + "摸到了一张牌：" + tileDrawn);
        this.drawTile(tileDrawn);
        MahjongTile tileToPlay = tileDrawn;
        if (this.chow.hasChowed(tileToPlay, playerIndex, this.players, this.computers)) {
            tileToPlay = (MahjongTile)this.getHand().get(this.random.nextInt(this.getHand().size()));
        } else {
            tileToPlay = this.chowTileOrPlay(tileToPlay, playerIndex);
        }

        System.out.println(this.chow.hasChowed(tileToPlay, playerIndex, this.players, this.computers));
        System.out.println("出的牌：" + tileToPlay);
        this.hand.remove(tileToPlay);
        System.out.println("电脑 " + playerIndex + " 出了一张牌：" + tileToPlay);
    }

    // 判断是否吃牌，如果可以吃牌，则返回要吃的牌；否则返回随机出牌
    private MahjongTile chowTileOrPlay(MahjongTile discardedTile, int playerIndex) {
        MahjongTile tileToPlay = null;
        if (chow.canChow(discardedTile, playerIndex, players, computers)) {
            // 如果可以吃牌，则进行吃牌操作
            chow.chowTile(discardedTile, playerIndex);
        } else {
            // 如果不可以吃牌，则随机出牌
            tileToPlay = getHand().get(random.nextInt(getHand().size()));
        }
        return tileToPlay;
    }
}
