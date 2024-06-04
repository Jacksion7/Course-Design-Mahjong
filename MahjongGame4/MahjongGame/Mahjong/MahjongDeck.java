package Mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MahjongDeck {
    //牌库
    final List<MahjongTile> tilesLibrary;
    //随机数生成器
    private Random random;

    public MahjongDeck() {
        tilesLibrary = new ArrayList<>();
        random = new Random();
        initializeTiles();
    }

    private void initializeTiles() {
        //初始化牌库
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j < 4; j++) {
                MahjongTile tile1 = new MahjongTile(i, "万",null);
                matchTilesWithImage(tile1);
                tilesLibrary.add(tile1);

                MahjongTile tile2 = new MahjongTile(i, "条",null);
                matchTilesWithImage(tile2);
                tilesLibrary.add(tile2);

                MahjongTile tile3 = new MahjongTile(i, "筒",null);
                matchTilesWithImage(tile3);
                tilesLibrary.add(tile3);


            }
        }
        String[] honors = {"东", "南", "西", "北", "中", "发", "白"};
        for (String honor : honors) {
            for (int i = 0; i < 4; i++) {
                MahjongTile tile4 = new MahjongTile(0, honor,null);
                matchTilesWithImage(tile4);
                tilesLibrary.add(tile4);
                //tilesLibrary.add(new MahjongTile(0, honor,null));
            }
        }
    }

    //从牌库中随机抽一张牌，并且从牌库中移除该牌，返回该牌。！！这一部分应该在出摸牌中实现。！！
    public MahjongTile drawTile() {
        if (tilesLibrary.isEmpty()) {
            return null;
        }
        int index = random.nextInt(tilesLibrary.size());
        return tilesLibrary.remove(index);
    }

    public List<MahjongTile> getTilesLibrary() {
        return tilesLibrary;
    }


    //这部分是用来测试的，出现了重复牌的问题，但不是这个牌堆的问题
    public static void main(String[] args) {
        MahjongDeck deck = new MahjongDeck();
        List<MahjongTile> hand = deck.getTilesLibrary();
        Collections.sort(hand, (t1, t2) -> {
            int suitOrder1 = getSuitOrder(t1.getSuit());
            int suitOrder2 = getSuitOrder(t2.getSuit());
            if (suitOrder1 != suitOrder2) {
                return suitOrder1 - suitOrder2;
            }
            return t1.getValue() - t2.getValue();
        });
        printHand(hand);
    }

    private static int getSuitOrder(String suit) {
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

    private static void printHand(List<MahjongTile> hand) {
        int count = 0;
        for (MahjongTile tile : hand) {
            System.out.print(tile + " ");
            count++;
            if (count % 4 == 0) {
                System.out.println();  // 每四个值进行一次换行
            }
        }
        if (count % 4 != 0) {
            System.out.println();  // 打印后续的换行符，如果最后一行不足四个值
        }
    }

    public static void matchTilesWithImage(MahjongTile tile) {
        if (tile.getSuit() == "万") {
            if (tile.getValue() == 1) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan1.png";
            }
            if (tile.getValue() == 2) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan2.png";
            }
            if (tile.getValue() == 3) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan3.png";
            }
            if (tile.getValue() == 4) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan4.png";
            }
            if (tile.getValue() == 5) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan5.png";
            }
            if (tile.getValue() == 6) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan6.png";
            }
            if (tile.getValue() == 7) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan7.png";
            }
            if (tile.getValue() == 8) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan8.png";
            }
            if (tile.getValue() == 9) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/wan/wan9.png";
            }
        }
        if (tile.getSuit() == "条") {
            if (tile.getValue() == 1) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao1.png";
            }
            if (tile.getValue() == 2) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao2.png";
            }
            if (tile.getValue() == 3) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao3.png";
            }
            if (tile.getValue() == 4) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao4.png";
            }
            if (tile.getValue() == 5) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao5.png";
            }
            if (tile.getValue() == 6) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao6.png";
            }
            if (tile.getValue() == 7) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao7.png";
            }
            if (tile.getValue() == 8) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao8.png";
            }
            if (tile.getValue() == 9) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tiao/tiao9.png";
            }
        }
        if (tile.getSuit() == "筒") {
            if (tile.getValue() == 1) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong1.png";
            }
            if (tile.getValue() == 2) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong2.png";
            }
            if (tile.getValue() == 3) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong3.png";
            }
            if (tile.getValue() == 4) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong4.png";
            }
            if (tile.getValue() == 5) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong5.png";
            }
            if (tile.getValue() == 6) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong6.png";
            }
            if (tile.getValue() == 7) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong7.png";
            }
            if (tile.getValue() == 8) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong8.png";
            }
            if (tile.getValue() == 9) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong9.png";
            }
        }
        if (tile.getSuit()=="中"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/zhong.png";
        }
        if (tile.getSuit()=="发"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/fa.png";
        }
        if (tile.getSuit()=="白"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/white.png";
        }
        if (tile.getSuit()=="东"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/east.png";
        }
        if (tile.getSuit()=="西"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/west.png";
        }
        if (tile.getSuit()=="南"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/south.png";
        }
        if (tile.getSuit()=="北"){
            tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/bonus/north.png";
        }
    }

}

