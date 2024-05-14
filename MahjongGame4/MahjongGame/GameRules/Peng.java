package GameRules;

import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;

import java.util.Objects;

public class Peng {
    private MahjongTile discardTile;
    private Player[] players;
    private Computers[] computers;

    public Peng(MahjongTile discardTile, Player[] players, Computers[] computers) {
        this.discardTile = discardTile;
        this.players = players;
        this.computers = computers;
    }

    // 检查是否能够进行碰牌
    public boolean canPeng(int currentPlayerIndex) {
        // 获取下家的索引
        int nextPlayerIndex = (currentPlayerIndex + 1) % 4;

        // 获取下家的手牌
        MahjongTile[] nextPlayerHand;
        if (nextPlayerIndex == 0) {
            nextPlayerHand = players[nextPlayerIndex].getHand().toArray(new MahjongTile[0]);
        } else {
            nextPlayerHand = computers[nextPlayerIndex - 1].getHand().toArray(new MahjongTile[0]);
        }

        // 检查下家的手牌中是否有两张与出的牌类型和值相同的牌
        int count = 0;
        for (MahjongTile tile : nextPlayerHand) {
            if (Objects.equals(tile.getSuit(), discardTile.getSuit()) && tile.getValue() == discardTile.getValue()) {
                count++;
            }
            if (count >= 2) {
                return true;
            }
        }

        return false;
    }

    // 进行碰牌操作
    public void pengTile(int currentPlayerIndex) {
        if (discardTile == null) {
            // 处理 discardTile 为 null 的情况
            System.out.println("无法进行碰牌操作，因为 discardTile 为 null。");
            return; // 返回或采取其他适当的操作
        }

        if (canPeng(currentPlayerIndex)) {
            // 获取下家的索引
            int nextPlayerIndex = (currentPlayerIndex + 1) % 4;

            // 获取下家的手牌
            MahjongTile[] nextPlayerHand;
            if (nextPlayerIndex == 0) {
                nextPlayerHand = players[nextPlayerIndex].getHand().toArray(new MahjongTile[0]);
                players[nextPlayerIndex].getHand().removeIf(tile -> Objects.equals(tile.getSuit(), discardTile.getSuit()) && tile.getValue() == discardTile.getValue());
            } else {
                nextPlayerHand = computers[nextPlayerIndex - 1].getHand().toArray(new MahjongTile[0]);
                computers[nextPlayerIndex - 1].getHand().removeIf(tile -> Objects.equals(tile.getSuit(), discardTile.getSuit()) && tile.getValue() == discardTile.getValue());
            }

            // 从下家的手牌中移除两张与出的牌类型和值相同的牌
            int count = 0;
            for (MahjongTile tile : nextPlayerHand) {
                if (Objects.equals(tile.getSuit(), discardTile.getSuit()) && tile.getValue() == discardTile.getValue()) {
                    count++;
                    if (count >= 2) {
                        break;
                    }
                }
            }
        }

    }
}

