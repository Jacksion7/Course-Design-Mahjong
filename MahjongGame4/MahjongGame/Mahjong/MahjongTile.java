package Mahjong;

import java.util.Objects;

public class MahjongTile {
    //花色
    private String suit;
    //数值
    private int value;

    public MahjongTile(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return value + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MahjongTile that = (MahjongTile) obj;
        return value == that.value && Objects.equals(suit, that.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }


    public Object getTileFace() {
        return this;
    }
}
