package Players;

import GameRules.Chow;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;


public class Computers extends PlayerBase {
    public Chow chow;

    public Computers(MahjongTile discardedTile, Player[] players, Computers[] computers) {
        super(discardedTile, players, computers);
        chow = new Chow(discardedTile, players, computers);
        //this.deck = new MahjongDeck();
    }

    public void computerPlay(int playerIndex) {

        // 电脑玩家的回合
        if (true) {

            MahjongTile tileDrawn = deck.drawTile();
            System.out.println("Player " + playerIndex + "摸到了一张牌：" + tileDrawn);
            drawTile(tileDrawn);

            MahjongTile tileToPlay = getHand().get(random.nextInt(getHand().size()));
            hand.remove(tileToPlay);
            System.out.println("Player " + playerIndex + "出了一张牌：" + tileToPlay);

            chow.chowTile(tileToPlay, playerIndex);


        } else {
            System.out.println("玩家手牌已空，无法出牌。");


        }
    }

}
