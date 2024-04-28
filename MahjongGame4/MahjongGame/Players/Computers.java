package Players;

import GameRules.Chow;
import Mahjong.MahjongTile;


public class Computers extends PlayerBase {
    public Chow chow;
    private MahjongTile discardedTile;
    private Player[] players;
    private Computers[] computers;

    public Computers(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        super(discardedTile, players, computers, playerIndex);
        chow = new Chow(discardedTile, players, computers, playerIndex);
        this.players = players;
        this.computers = computers;
    }

    public void computerPlay(int playerIndex) {
        // 电脑玩家的回合
        if (true) {

            MahjongTile tileDrawn = deck.drawTile();
            System.out.println("Player " + playerIndex + "摸到了一张牌：" + tileDrawn);
            drawTile(tileDrawn);


            MahjongTile tileToPlay = getHand().get(random.nextInt(getHand().size()));

            if (chow.hasChowed(tileToPlay, playerIndex, players, computers)) {
                // 如果电脑玩家已经吃过牌，则直接出牌
                tileToPlay = getHand().get(random.nextInt(getHand().size()));
            } else {
                // 如果电脑玩家没有吃过牌，则进行吃牌判断
                tileToPlay = chowTileOrPlay(tileToPlay, playerIndex);
            }
            System.out.println(chow.hasChowed(tileToPlay, playerIndex, players, computers));
            System.out.println("出的牌：" + tileToPlay);
            hand.remove(tileToPlay);
            System.out.println("电脑 " + playerIndex + " 出了一张牌：" + tileToPlay);
            //chow.canChow(tileToPlay, playerIndex, players, computers);

        } else {
            System.out.println("电脑 " + playerIndex + " 手牌已空，无法出牌。");
        }
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
