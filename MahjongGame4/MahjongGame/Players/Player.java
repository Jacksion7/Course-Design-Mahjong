package Players;

import Mahjong.MahjongDeck;
import Mahjong.MahjongTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
    public Random random;
    private List<MahjongTile> hand;
    private MahjongDeck deck;
    public Player() {
        hand = new ArrayList<>();
        random = new Random();
        deck = new MahjongDeck();
    }
    public List<MahjongTile> getHand() {
        return hand;
    }
    public void drawTile(MahjongTile tile) {
        hand.add(tile);
    }

    public void dealTile(MahjongTile tile) {
        if (hand.contains(tile)) {
            hand.remove(tile);
            System.out.println("Player 1" +" 出了一张牌：" + tile);
        } else {
            System.out.println("玩家手牌中没有这张牌: " + tile);
        }
    }

    public void displayHand() {
        sortHand();
        for (MahjongTile tile : hand) {
            System.out.print(tile + " ");
        }
    }

    public void computerPlay() {
        // 电脑玩家，判断手牌是否为空，若不为空则随机打出一张手牌
        if (!hand.isEmpty()) {
            //摸牌
            MahjongTile tile = deck.drawTile();
            System.out.println("Player 摸到了一张牌：" + tile);
            drawTile(tile);

            //出牌
            MahjongTile tileToPlay = hand.get(random.nextInt(hand.size()));
            System.out.println("Player 出了一张牌：" + tileToPlay);
            hand.remove(tileToPlay);
        } else {
            System.out.println("玩家手牌已空，无法出牌。");
        }
    }

    private void sortHand() {
        Collections.sort(hand, (t1, t2) -> {
            int suitOrder1 = getSuitOrder(t1.getSuit());
            int suitOrder2 = getSuitOrder(t2.getSuit());
            if (suitOrder1 != suitOrder2) {
                return suitOrder1 - suitOrder2;
            }
            return t1.getValue() - t2.getValue();
        });
    }

    private int getSuitOrder(String suit) {
        switch (suit) {
            case "万":
                return 0;
            case "条":
                return 1;
            case "筒":
                return 2;
            default:
                return 3;
        }
    }

}
