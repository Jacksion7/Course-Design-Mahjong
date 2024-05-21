package UI;

import Mahjong.MahjongGame;
import Mahjong.MahjongGameManager;
import Mahjong.MahjongTile;
import Players.Player;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Players.Player;
import Mahjong.MahjongTile;

public class GameScreen extends JFrame {

    MahjongGame mahjongGame;

    int mahjongWidth = 40;
    int mahjongHeight = 60;
    int startX1 = (Constant.SCREEN_WIDTH - (13 * mahjongWidth)) / 2;
    int startY1 = 800 - mahjongHeight - 30; // 20像素距离屏幕底部
    int startX2 = (13 * mahjongWidth) + 40;
    int startY2 = mahjongHeight; // 20像素距离屏幕底部
    int startY3 = mahjongHeight + 80; // 20像素距离屏幕底部

    public GameScreen() {

        setTitle("Image Frame Example");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个JPanel用于显示图片
        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                //super.paintComponent(g);
                // 加载图片文件
                super.paintComponent(g);
                //if (game != null) {
                Image image = new ImageIcon("MahjongGame4/imgSet/playBackground.png").getImage();
                Image player1 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player1.png").getImage();
                Image player2 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player2.png").getImage();
                Image player3 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player3.png").getImage();
                Image player4 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player4.png").getImage();

//                Image touch = new ImageIcon("MahjongGame4/imgSet/PlayScreen/bottom/touch.png").getImage();
//                Image draw = new ImageIcon("MahjongGame4/imgSet/PlayScreen/bottom/draw.png").getImage();
//                Image select = new ImageIcon("MahjongGame4/imgSet/PlayScreen/bottom/select.png").getImage();

                // 绘制图片
                g.drawImage(image, 0, 0, 800, 800, this);
                g.drawImage(player1, 10, 10, 100, 100, this);
                g.drawImage(player2, 10, 660, 100, 100, this);
                g.drawImage(player3, 660, 660, 100, 100, this);
                g.drawImage(player4, 660, 10, 100, 100, this);

                ImageIcon scaledIcon = new ImageIcon(image);
                //button.setIcon(scaledIcon);

//                g.drawImage(touch, 500, 550, 60,100, this);
//                g.drawImage(draw, 200, 550, 60,100,this);
//                g.drawImage(select, 350, 550, 60,100, this);

                Graphics2D g2 = (Graphics2D) g.create();
                // Graphics g2 =  g;
                initialPlayerTiles(g2);

                if(MahjongGameManager.ifDealTiles){
                    paintTiles(MahjongGameManager.player,g2);
                }
            }

        };
        panel.setLayout(new BorderLayout());
        JButton button = new JButton();
        JButton button2 = new JButton("Draw ");
        JButton button3 = new JButton("Select ");
        button.setOpaque(false);

        Image touch = new ImageIcon("MahjongGame4/imgSet/PlayScreen/button/touch.png").
                getImage().getScaledInstance(90,130,Image.SCALE_SMOOTH);
        Image draw = new ImageIcon("MahjongGame4/imgSet/PlayScreen/button/draw.png").
                getImage().getScaledInstance(90,130,Image.SCALE_SMOOTH);
        Image select = new ImageIcon("MahjongGame4/imgSet/PlayScreen/button/select.png").
                getImage().getScaledInstance(80,120,Image.SCALE_SMOOTH);;

        ImageIcon touchCon = new ImageIcon(touch);// give the image on the button
        button.setIcon(touchCon);
        ImageIcon drawCon = new ImageIcon(draw);
        button2.setIcon(drawCon);
        ImageIcon selectCon = new ImageIcon(select);
        button3.setIcon(selectCon);

        button.setBounds(200, 550, 70,100); // x, y, width, height
        button2.setBounds(350, 550, 70,100);
        button3.setBounds(500, 550, 70,100);
        panel.setLayout(null);

        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        add(panel);
    }




    protected void initialPlayerTiles(Graphics2D g2d) {
        Image tile_Face = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Face.png").getImage();
        Image tile_Back = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Back.png").getImage();
        Image tile_Lay = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_lay.png").getImage();
        Image tile_Lay2 = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_lay2.png").getImage();

        // 在屏幕下方从左到右绘制13张相同的空白麻将图片
        for (int i = 0; i < 13; i++) {
            g2d.drawImage(tile_Face, startX1 - 120 + i * 40, startY1 -40 , mahjongWidth, mahjongHeight, this);
            g2d.drawImage(tile_Back, startX1 - 120 + i * 40, startY2 + 5, mahjongWidth, mahjongHeight, this);
            g2d.drawImage(tile_Lay2, startX1 - 150, startY3 - 20 + i * 40, mahjongWidth, mahjongHeight, this);
            g2d.drawImage(tile_Lay, startX2 + 90, startY3 - 20 + i * 40, mahjongWidth, mahjongHeight, this);
        }
    }

    public void paintTiles(Player player, Graphics2D g2d) {
        int i = 0;
        for (MahjongTile tile : player.hand) {
            matchTilesWithImage(tile);// assign the ImagePath of tiles
            Image temp = new ImageIcon(tile.ImagePath).getImage();
            g2d.drawImage(temp, startX1 - 120 + i * 40, startY1 -40 , mahjongWidth, mahjongHeight, this);
            i++;
        }
    }

    // set ImagePath of each tiles
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


    public static void main(String[] args) {
        GameScreen frame = new GameScreen();
        frame.setVisible(true);



    }

}






