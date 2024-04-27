package Players;

import GameRules.Chow;
import Mahjong.MahjongTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*对于这个类，我们需要的是一个玩家类
 *玩家类应该包含以下属性：
 *玩家的手牌（hand）
 *手牌的更新方法（updateHand）
 *四个玩家的每回合操作（playIndex）
 *手牌的排序方法（sortHand, getSuitOrder）
 *

 */
public class Player extends PlayerBase {
    public Chow chow;

    public Player(MahjongTile discardedTile, Player[] players, Computers[] computers) {
        super(discardedTile, players, computers);
        chow = new Chow(discardedTile, players, computers);
    }

    public void dealPlayerTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
        System.out.println();
    }

}
