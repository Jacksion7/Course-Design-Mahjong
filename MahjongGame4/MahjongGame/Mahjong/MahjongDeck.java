package Mahjong;

import Item.Deck;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MahjongDeck implements Deck {
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
                tilesLibrary.add(new MahjongTile("万", i));
                tilesLibrary.add(new MahjongTile("条", i));
                tilesLibrary.add(new MahjongTile("筒", i));
            }
        }
        String[] honors = {"东", "南", "西", "北", "中", "发", "白"};
        for (String honor : honors) {
            for (int i = 0; i < 4; i++) {
                tilesLibrary.add(new MahjongTile(honor, 0));
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
}
