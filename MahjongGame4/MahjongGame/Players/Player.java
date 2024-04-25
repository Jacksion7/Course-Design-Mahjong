package Players;

import GameRules.Chow;
import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;

import java.util.ArrayList;
import java.util.Collections;
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
public class Player {
    private int playerIndex;
    public Random random;
    public List<MahjongTile> hand;
    private MahjongDeck deck;
    public Chow chow;

    public Player(MahjongTile discardedTile) {
        //this.playerIndex = playerIndex;
        hand = new ArrayList<>();
        random = new Random();
        //deck = new MahjongDeck();
        //chow = new Chow(discardedTile, players, this);
    }

    //用来设置玩家顺序
    /*
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
     */

    //用来获得玩家顺序
    public int getPlayerIndex() {
        return playerIndex;
    }

    //手牌
    public List<MahjongTile> getHand() {
        return hand;
    }

    public void setHand(List<MahjongTile> new_hand) {
        hand = new_hand;
    }
    //
    public void drawTile(MahjongTile tile) {
        hand.add(tile);
    }
    public void dealPlayerTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
        System.out.println();
    }

    //这个方法是用来展示手牌的
    public void displayHand() {
        sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile + " ");
        }
        System.out.println();
    }

    //这个方法是用来排序手牌的
    public void sortHand() {
        Collections.sort(hand, (t1, t2) -> {
            int suitOrder1 = getSuitOrder(t1.getSuit());
            int suitOrder2 = getSuitOrder(t2.getSuit());
            if (suitOrder1 != suitOrder2) {
                return suitOrder1 - suitOrder2;
            }
            return t1.getValue() - t2.getValue();
        });
    }

    //这是排序手牌的依据
    private int getSuitOrder(String suit) {
        switch (suit) {
            case "万":
                return 0;
            case "条":
                return 1;
            case "筒":
                return 2;
            case "东":
                return 3;
            case "西":
                return 4;
            case "南":
                return 5;
            case "北":
                return 6;
            case "中":
                return 7;
            case "发":
                return 8;
            default:
                return 9;
        }
    }

}
