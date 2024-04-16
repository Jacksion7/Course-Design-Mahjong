package Mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MahjongDeck {
    private List<MahjongTile> tiles;
    private Random random;


    public MahjongDeck() {
        tiles = new ArrayList<>();
        random = new Random();

        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j < 4; j++) {
                tiles.add(new MahjongTile("万", i));
                tiles.add(new MahjongTile("条", i));
                tiles.add(new MahjongTile("筒", i));
            }
        }

        String[] honors = {"东", "南", "西", "北", "中", "发", "白"};
        for (String honor : honors) {
            for (int i = 0; i < 4; i++) {
                tiles.add(new MahjongTile(honor, 0));
            }
        }
    }

    public MahjongTile drawTile() {
        if (tiles.isEmpty()) {
            return null;
        }
        int index = random.nextInt(tiles.size());
        MahjongTile tile = tiles.remove(index);
        return tile;
    }

    public List<MahjongTile> getTiles() {
        return tiles;
    }


}
