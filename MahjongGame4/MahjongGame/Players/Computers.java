package Players;

import Item.PlayerBase;
import Mahjong.MahjongTile;


public class Computers extends PlayerBase {
    private MahjongTile discardedTile;

    public Computers(MahjongTile discardedTile, Player[] players, Computers[] computers, int playerIndex) {
        super();
        this.discardedTile = discardedTile;
        this.players = players;
        this.computers = computers;
        this.playerIndex = playerIndex;
    }

    public void computerPlay(int playerIndex) {
        // 电脑玩家的回合
//        if (win.isWin()) {
//            if (gang.isGang()) {
//                if (peng.isPeng()) {
//                    if (chow.isChow()) {
//                        System.out.println("已吃牌，进行出牌");
//                        computerPlayTile(playerIndex);
//                    }
//                    System.out.println("已碰牌，进行出牌");
//                    computerPlayTile(playerIndex);
//                }
//                System.out.println("已杠牌，进行出牌");
//                computerPlayTile(playerIndex);
//            }
//            System.out.println("已胡牌");
//        }
        System.out.println("电脑能不能吃牌？");
        if (chow.isChow(discardedTile)) {
            System.out.println("已吃牌，进行出牌");
            computerPlayTile(playerIndex);
        }
        System.out.println("你没有进行任何操作，进行出牌");
        computerTouchTile(playerIndex);
        computerPlayTile(playerIndex);
    }

    private void computerTouchTile(int playerIndex) {
        MahjongTile tileDrawn = deck.drawTile();
        System.out.println("Player " + playerIndex + "摸到了一张牌：" + tileDrawn);
        drawTile(tileDrawn);
    }

    private void computerPlayTile(int playerIndex) {
        MahjongTile tileToPlay = getHand().get(random.nextInt(getHand().size()));
        discardedTile = tileToPlay;
        System.out.println("出的牌：" + discardedTile);
        hand.remove(tileToPlay);
        System.out.println("电脑 " + playerIndex + " 出了一张牌：" + tileToPlay);
    }

}
