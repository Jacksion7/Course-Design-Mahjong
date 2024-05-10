package GameRules;


import Mahjong.MahjongTile;
import Players.Computers;
import Players.Player;
import java.util.Objects;

public class Gang {
    private MahjongTile discardTile;
    private Player[] players;
    private Computers[] computers;

    public Gang(MahjongTile discardTile, Player[] players, Computers[] computers) {
        this.discardTile = discardTile;
        this.players = players;
        this.computers = computers;
    }

    // 检查是否能够进行明杠或暗杠
    public boolean canGang(int currentPlayerIndex) {
        // 获取当前玩家的手牌
        MahjongTile[] currentPlayerHand;
        if (currentPlayerIndex == 0) {
            currentPlayerHand = players[currentPlayerIndex].getHand().toArray(new MahjongTile[0]);
        } else {
            currentPlayerHand = computers[currentPlayerIndex - 1].getHand().toArray(new MahjongTile[0]);
        }

        // 统计手牌中相同牌的数量
        int count = 0;
        for (MahjongTile tile : currentPlayerHand) {
            if (tile.equals(discardTile)) {
                count++;
            }
        }

        // 若手牌中有四张相同的牌，则可以进行暗杠
        if (count == 4) {
            return true;
        }

        // 获取下家的索引
        int nextPlayerIndex = (currentPlayerIndex + 1) % 4;

        // 获取下家的手牌
        MahjongTile[] nextPlayerHand;
        if (nextPlayerIndex == 0) {
            nextPlayerHand = players[nextPlayerIndex].getHand().toArray(new MahjongTile[0]);
        } else {
            nextPlayerHand = computers[nextPlayerIndex - 1].getHand().toArray(new MahjongTile[0]);
        }

        // 检查下家的手牌中是否有三张与出的牌相同的牌
        count = 0;
        for (MahjongTile tile : nextPlayerHand) {
            if (Objects.equals(tile, discardTile)) {
                count++;
            }
            if (count >= 3) {
                return true;
            }
        }

        return false;
    }

    // 进行杠牌操作
    public void gangTile(int currentPlayerIndex) {
        // 获取当前玩家的手牌
        MahjongTile[] currentPlayerHand;
        if (currentPlayerIndex == 0) {
            currentPlayerHand = players[currentPlayerIndex].getHand().toArray(new MahjongTile[0]);
            players[currentPlayerIndex].getHand().removeIf(tile -> Objects.equals(tile, discardTile));
        } else {
            currentPlayerHand = computers[currentPlayerIndex - 1].getHand().toArray(new MahjongTile[0]);
            computers[currentPlayerIndex - 1].getHand().removeIf(tile -> Objects.equals(tile, discardTile));
        }

        // 从当前玩家的手牌中移除四张相同的牌
        int count = 0;
        for (MahjongTile tile : currentPlayerHand) {
            if (Objects.equals(tile, discardTile)) {
                count++;
                if (count >= 3) {
                    break;
                }
            }
        }
    }
}