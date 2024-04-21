package GameRules;

import Mahjong.MahjongTile;
import Players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chow {
    private MahjongTile discardedTile;
    public Player currentPlayer;
    private Player[] players;
    private List<MahjongTile> hand;
    private MahjongTile firstTile;
    private MahjongTile secondTile;
    private MahjongTile thirdTile;
    public Chow(MahjongTile discardedTile, Player[] players, Player currentPlayer) {
        this.discardedTile = discardedTile;
        this.players = players;
        this.currentPlayer = currentPlayer;
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
        // 如果当前的顺子牌型为空，那么无法吃牌
        if (firstTile == null || secondTile == null || thirdTile == null) {
            return false;
        }

        // 将传入的牌与当前的顺子牌型进行比较，判断是否可以组成顺子
        int value = tile.getValue();
        String suit = tile.getSuit();

        // 判断是否为同一种花色
        if (!suit.equals(firstTile.getSuit()) || !suit.equals(secondTile.getSuit()) || !suit.equals(thirdTile.getSuit())) {
            return false;
        }

        // 对牌值进行排序，确保 firstTile < secondTile < thirdTile
        int[] values = {firstTile.getValue(), secondTile.getValue(), thirdTile.getValue()};
        Arrays.sort(values);

        // 判断传入的牌是否可以与顺子牌型组成顺子
        if (values[0] == value - 1 && values[1] == value && values[2] == value + 1) {
            return true;
        }

        return false;
    }

    public boolean canChow(int value, String suit,  Player currentPlayer) {
        MahjongTile discardedTile = new MahjongTile(suit, value); // 获取上家打出的牌
        Player nextPlayer = getNextPlayer(currentPlayer);
        for (MahjongTile tile : nextPlayer.getHand()) {
            if (tile.getSuit().equals(discardedTile.getSuit())) {
                // 如果值为1，查找是否有值为2和3的牌
                if (value == 1 && (tile.getValue() == 2 || tile.getValue() == 3)) {
                    return true;
                }
                // 如果值为9，查找是否有值为8和7的牌
                else if (value == 9 && (tile.getValue() == 8 || tile.getValue() == 7)) {
                    return true;
                }
                // 如果值为2，分两部分查，第一查（1,3），第二查（3,4）
                else if (value == 2 && ((tile.getValue() == 1 && tileExists(3, suit, nextPlayer)) || (tile.getValue() == 3 && tileExists(4, suit, nextPlayer)))) {
                    return true;
                }
                // 如果值为8，分两部分查，第一查（7,9），第二查（6,7）
                else if (value == 8 && ((tile.getValue() == 9 && tileExists(7, suit, nextPlayer)) || (tile.getValue() == 7 && tileExists(6, suit, nextPlayer)))) {
                    return true;
                }
                // 如果值为3~7，分两部分查，第一查（value-1和value-2），第二查（value+1和value+2）
                else if (value >= 3 && value <= 7 && ((tile.getValue() == value - 1 && tileExists(value - 2, suit, nextPlayer)) || (tile.getValue() == value + 1 && tileExists(value + 2, suit, nextPlayer)))) {
                    return true;
                }
            }
        }
        return false;
    }
    public Player getNextPlayer(Player currentPlayer) {
        int currentPlayerIndex = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i] == currentPlayer) {
                currentPlayerIndex = i;
                break;
            }
        }
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.length;
        return players[nextPlayerIndex];
    }

    // 检查玩家手牌中是否存在指定牌值和类型的牌
    private boolean tileExists(int value, String suit, Player player) {
        for (MahjongTile tile : player.getHand()) {
            if (tile.getValue() == value && tile.getSuit().equals(suit)) {
                return true;
            }
        }
        return false;
    }

    public void chowTile(MahjongTile discardedTile, Player currentPlayer) {
        // 获取当前玩家的手牌
        List<MahjongTile> hand = currentPlayer.getHand();

        // 检查是否可以吃牌
        if (canChow(discardedTile.getValue(), discardedTile.getSuit(), currentPlayer)) {
            // 获取顺子牌
            MahjongTile firstTile = getFirstTile();
            MahjongTile secondTile = getSecondTile();
            MahjongTile thirdTile = getThirdTile();

            // 添加顺子牌到新的列表中
            List<MahjongTile> chowTiles = new ArrayList<>();
            chowTiles.add(firstTile);
            chowTiles.add(secondTile);
            chowTiles.add(thirdTile);

            // 从手牌中移除被吃的牌
            hand.remove(discardedTile);

            // 输出吃牌信息
            System.out.println("玩家吃牌成功：" + chowTiles);
        } else {
            System.out.println("当前玩家无法吃牌！");
        }
    }


}
