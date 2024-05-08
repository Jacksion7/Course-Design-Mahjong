package Models;

import java.util.Objects;

//这个方法主要是用来创建麻将牌的类，每个麻将牌都有花色和数值。输出的是String的形式。
public class MahjongTile {
    //花色
    private String suit;
    //数值
    private int value;

    //构造麻将牌
    public MahjongTile(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    //获取花色
    public String getSuit() {
        return suit;
    }

    //获取数值
    public int getValue() {
        return value;
    }

    //重写toString方法，输出String形式
    public String toString() {
        return value + suit;
    }

    //重写equals方法，比较两个麻将牌是否相等
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

    //重写hashCode方法
    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

}
