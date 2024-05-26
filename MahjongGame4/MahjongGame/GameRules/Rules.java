package GameRules;

import Mahjong.MahjongTile;
import Players.Player;

import java.util.*;

public class Rules {
    private Scanner scanner;
    private Player[] players;
    private MahjongTile discardTile;
    private int playerIndex;
    private MahjongTile secondTile;
    private MahjongTile thirdTile;
    List<MahjongTile> rulesTiles;
    private MahjongTile fourthTile;

    public Rules(Player[] players) {
        scanner = new Scanner(System.in);
        this.players = players;
    }
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
    public void setPlayers(Player[] players) {
        this.players = players;
    }
    public int getPlayerIndex() {
        return playerIndex;
    }
    public MahjongTile getDiscardTile() {
        return discardTile;
    }
    public List<MahjongTile> getRulesTiles() {
        return players[playerIndex].getRulesTiles();
    }

    public void sortRulesTiles(List<MahjongTile> rulesTiles) {
        rulesTiles.sort((t1, t2) -> {
            int suitOrder1 = getSuitOrder(t1.getSuit());
            int suitOrder2 = getSuitOrder(t2.getSuit());
            if (suitOrder1 != suitOrder2) {
                return suitOrder1 - suitOrder2;
            }
            return t1.getValue() - t2.getValue();
        });
    }

    private int getSuitOrder(String suit) {
        return switch (suit) {
            case "万" -> 0;
            case "条" -> 1;
            case "筒" -> 2;
            case "东" -> 3;
            case "西" -> 4;
            case "南" -> 5;
            case "北" -> 6;
            case "中" -> 7;
            case "发" -> 8;
            default -> 9;
        };
    }

    //------------TouchDeal-----------
    public void discardTile() {
        Player currentPlayer = players[playerIndex];
        System.out.println("请输入您要出的牌（值 牌面）：");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            System.out.println("输入格式不正确，请重新输入！");
            discardTile();
            return;
        }

        int value;
        try {
            value = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            System.out.println("牌值必须为整数，请重新输入！");
            discardTile();
            return;
        }

        String suit = parts[1];
        MahjongTile Tile = new MahjongTile(value, suit);

        if (!isTileInHand(Tile, currentPlayer)) {
            System.out.println("您手上的牌中没有该牌，请重新输入！");
            discardTile();
            return;
        }

        currentPlayer.dealPlayerTile(Tile);
        System.out.println("Players.Player " + (playerIndex + 1) + "出了一张牌：" + Tile);
        discardTile = Tile;
    }

    private boolean isTileInHand(MahjongTile tile, Player player) {
        for (MahjongTile tile2 : player.getHand()) {
            if (tile2.getValue() == tile.getValue() && tile2.getSuit().equals(tile.getSuit())) {
                return true;
            }
        }
        return false;
    }

//----------------------------------------------------Chow--------------------------------------------------------

    public int updatePlayerIndex() {
        playerIndex = (playerIndex + 1) % 4;
        return playerIndex;
    }

    public boolean canChow(MahjongTile discardedTile, int playerIndex, Player[] players) {
        //System.out.println("出的牌是：---->>>" + discardedTile);

        if (discardedTile != null) {
            int value = discardedTile.getValue();
            String suit = discardedTile.getSuit();
            // 检测一下读取的牌是否正确
            System.out.println("This: ----------------" + value + suit);

            // 获取当前玩家或电脑的手牌
            List<MahjongTile> hand = players[playerIndex].getHand();

            //System.out.println("Hand: " + hand);

            // 检查是否为万、筒、条中的一种
            if (!suit.equals("万") && !suit.equals("筒") && !suit.equals("条")) {
                return false;
            }

            for (MahjongTile tile : hand) {
                if (tile.getSuit().equals(suit)) {
                    // 如果值为1，查找是否有值为2和3的牌
                    if (value == 1 && tile.getValue() == 2 && tileExists(3, suit, playerIndex, players)) {
                        secondTile = new MahjongTile(2, suit);
                        thirdTile = new MahjongTile(3, suit);
                        System.out.println("[从1查 " + value + secondTile + thirdTile + "]");
                        return true;
                    }
                    // 如果值为9，查找是否有值为8和7的牌
                    else if (value == 9 && tile.getValue() == 8 && tileExists(7, suit, playerIndex, players)) {
                        secondTile = new MahjongTile(8, suit);
                        thirdTile = new MahjongTile(7, suit);
                        System.out.println("[从9查 " + value + secondTile + thirdTile + "]");
                        return true;
                    }
                    // 如果值为2，分两部分查，第一查（1,3），第二查（3,4）
                    else if (value == 2) {
                        if (tile.getValue() == 1 && tileExists(3, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(1, suit);
                            thirdTile = new MahjongTile(3, suit);
                            System.out.println("[2查（1,3） " + value + secondTile + thirdTile + "]");
                            return true;
                        } else if (tile.getValue() == 3 && tileExists(4, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(3, suit);
                            thirdTile = new MahjongTile(4, suit);
                            System.out.println("[2查（3,4） " + value + secondTile + thirdTile + "]");
                            return true;
                        }
                        return false;
                    }
                    // 如果值为8，分两部分查，第一查（7,9），第二查（6,7）
                    else if (value == 8) {
                        if (tile.getValue() == 7 && tileExists(9, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(7, suit);
                            thirdTile = new MahjongTile(9, suit);
                            System.out.println("[8查（7,9） " + value + secondTile + thirdTile + "]");
                            return true;
                        } else if (tile.getValue() == 6 && tileExists(7, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(6, suit);
                            thirdTile = new MahjongTile(7, suit);
                            System.out.println("[8查（6,7） " + value + secondTile + thirdTile + "]");
                            return true;
                        }
                        return false;
                    }
                    // 如果值为3~7，分三部分查，第一查（value-1和value-2），第二查（value+1和value+2），第三查（value-1和value+1）
                    else if (value >= 3 && value <= 7) {
                        if (tile.getValue() == value - 1 && tileExists(value - 2, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(value - 1, suit);
                            thirdTile = new MahjongTile(value - 2, suit);
                            System.out.println("[-1-2 " + value + secondTile + thirdTile + "]");
                            return true;
                        } else if (tile.getValue() == value + 1 && tileExists(value + 2, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(value + 1, suit);
                            thirdTile = new MahjongTile(value + 2, suit);
                            System.out.println("[+1+2 " + value + secondTile + thirdTile + "]");
                            return true;
                        } else if (tile.getValue() == value - 1 && tileExists(value + 1, suit, playerIndex, players)) {
                            secondTile = new MahjongTile(value - 1, suit);
                            thirdTile = new MahjongTile(value + 1, suit);
                            System.out.println("[-1+1 " + value + secondTile + thirdTile + "]");
                            return true;
                        }
                        return false;
                    }
//                    players[playerIndex].dealPlayerTile(secondTile);
//                    players[playerIndex].dealPlayerTile(thirdTile);
                }
            }
        }
        return false;
    }

    private boolean tileExists(int value, String suit, int playerIndex, Player[] players) {
        List<MahjongTile> hand = players[playerIndex].getHand();
        for (MahjongTile tile : hand) {
            if (tile.getValue() == value && tile.getSuit().equals(suit)) {
                return true;
            }
        }
        return false;
    }

    public void chowTile(MahjongTile discardedTile, int playerIndex) {
        List<MahjongTile> rulesTile = getRulesTiles();
        // 添加顺子牌到新的列表中
        rulesTile.add(discardedTile);
        rulesTile.add(secondTile);
        rulesTile.add(thirdTile);
        sortRulesTiles(rulesTile);
        players[playerIndex].sortHand();
        //System.out.println(playerIndex + 1);
        // 输出吃牌信息
        System.out.println("玩家" + (playerIndex + 1) + "吃牌成功：" + rulesTile);
        rulesTiles = rulesTile;
        players[playerIndex].dealPlayerTile(secondTile);
        players[playerIndex].dealPlayerTile(thirdTile);
    }

    public boolean isChow() {
        //System.out.println("进入判断isChow");
        //System.out.println("在isChow方法中discardedTile是: " + discardTile);

        if (canChow(discardTile, playerIndex, players)) {
            System.out.println("可以吃牌");
            chowTile(discardTile, playerIndex);
            return true;
        } else {
            System.out.println("不可以吃牌");;
            return false;
        }
    }

//-----------------------------------------------Peng---------------------------------------------------

    public boolean canPeng(MahjongTile discardedTile, int playerIndex, Player[] players) {
        //System.out.println("出的牌是：---->>>" + discardedTile);

        if (discardedTile != null) {
            int value = discardedTile.getValue();
            String suit = discardedTile.getSuit();
            // 检测一下读取的牌是否正确
            System.out.println("This: ----------------" + value + suit);

            // 获取当前玩家或电脑的手牌
            List<MahjongTile> hand = players[playerIndex].getHand();

            int count = 0;
            for (MahjongTile tile : hand) {
                if (Objects.equals(tile.getSuit(), suit) && tile.getValue() == value) {
                    secondTile = tile;
                    thirdTile = tile;
                    count++;
                }
                if (count >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    // 进行碰牌操作
    public void pengTile(MahjongTile discardedTile, int playerIndex) {
        List<MahjongTile> rulesTile = players[playerIndex].getRulesTiles();
        // 添加顺子牌到新的列表中
        rulesTile.add(discardedTile);
        rulesTile.add(secondTile);
        rulesTile.add(thirdTile);
        sortRulesTiles(rulesTile);
        players[playerIndex].sortHand();
        //System.out.println(playerIndex + 1);
        // 输出吃牌信息
        System.out.println("玩家" + (playerIndex + 1) + "碰牌成功：" + rulesTile);
        rulesTiles = rulesTile;
        players[playerIndex].dealPlayerTile(secondTile);
        players[playerIndex].dealPlayerTile(thirdTile);
    }

    public boolean isPeng() {
        //System.out.println("进入判断isPeng");
        //System.out.println("在isPeng方法中discardedTile是: " + discardTile);

        if (canPeng(discardTile, playerIndex, players)) {
            System.out.println("可以碰牌");
            pengTile(discardTile, playerIndex);
            return true;
        } else {
            System.out.println("不可以碰牌");;
            return false;
        }
    }

    //----------------------------------Gang----------------------------------
    public boolean canGang(MahjongTile discardedTile, int playerIndex, Player[] players) {
        //System.out.println("出的牌是：---->>>" + discardedTile);

        if (discardedTile != null) {
            int value = discardedTile.getValue();
            String suit = discardedTile.getSuit();
            // 检测一下读取的牌是否正确
            System.out.println("This: ----------------" + value + suit);

            // 获取当前玩家或电脑的手牌
            List<MahjongTile> hand = players[playerIndex].getHand();

            int count = 0;
            for (MahjongTile tile : hand) {
                if (Objects.equals(tile.getSuit(), suit) && tile.getValue() == value) {
                    if (count == 0) {
                        secondTile = tile;
                    } else if (count == 1) {
                        thirdTile = tile;
                    } else if (count == 2) {
                        fourthTile = tile;
                    }
                    count++;
                }
                if (count >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    // 进行杠牌操作
    public void gangTile(MahjongTile discardedTile, int playerIndex) {
        List<MahjongTile> rulesTile = players[playerIndex].getRulesTiles();
        // 添加顺子牌到新的列表中
        rulesTile.add(discardedTile);
        rulesTile.add(secondTile);
        rulesTile.add(thirdTile);
        sortRulesTiles(rulesTile);
        players[playerIndex].sortHand();
        //System.out.println(playerIndex + 1);
        // 输出吃牌信息
        System.out.println("玩家" + (playerIndex + 1) + "杠牌成功：" + rulesTile);
        rulesTiles = rulesTile;
        players[playerIndex].dealPlayerTile(secondTile);
        players[playerIndex].dealPlayerTile(thirdTile);
        players[playerIndex].dealPlayerTile(fourthTile);
    }

    public boolean isGang() {
        //System.out.println("进入判断isGang");
        //System.out.println("在isGang方法中discardedTile是: " + discardTile);

        if (canGang(discardTile, playerIndex, players)) {
            System.out.println("可以杠牌");
            gangTile(discardTile, playerIndex);
            return true;
        } else {
            System.out.println("不可以杠牌");;
            return false;
        }
    }

    //----------------------------------Win-------------------------------------



}






