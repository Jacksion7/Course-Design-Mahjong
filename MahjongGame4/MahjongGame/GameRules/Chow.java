package GameRules;

import Mahjong.MahjongTile;
import Players.Player;

import java.util.List;

public class Chow {
    private MahjongTile firstTile;
    private MahjongTile secondTile;
    private MahjongTile thirdTile;

    public Chow(MahjongTile firstTile, MahjongTile secondTile, MahjongTile thirdTile) {
        this.firstTile = firstTile;
        this.secondTile = secondTile;
        this.thirdTile = thirdTile;
    }

    public MahjongTile getFirstTile() {
        return firstTile;
    }

    public MahjongTile getSecondTile() {
        return secondTile;
    }

    public MahjongTile getThirdTile() {
        return thirdTile;
    }

    public boolean isValidChow(MahjongTile tile) {
        // 判断传入的牌是否可以和当前顺子牌型组成吃
        if (firstTile.getValue() == tile.getValue() - 1 && secondTile.getValue() == tile.getValue() && thirdTile.getValue() == tile.getValue() + 1) {
            return true;
        }
        return false;
    }

    public boolean canChow(Player player, MahjongTile tile) {
        // 判断玩家是否可以吃掉指定的牌
        List<MahjongTile> hand = player.getHand();
        // 首先判断手牌中是否有与传入的牌可以组成吃的牌
        for (int i = 0; i < hand.size(); i++) {
            if (isValidChow(hand.get(i))) {
                // 如果存在可组成吃的牌，则返回 true
                return true;
            }
        }
        return false;
    }

    //我在这里测试在IDEA中快捷上传github
}
