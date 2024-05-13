package UI;

import Mahjong.MahjongGameManager;


import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    final MahjongGameManager game;

    public GameScreen(MahjongGameManager game) {
        this.game = game;
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (game != null) {
            Image image = new ImageIcon("MahjongGame4/imgSet/playBackground.png").getImage();
            Image player1 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player1.png").getImage();
            Image player2 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player2.png").getImage();
            Image player3 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player3.png").getImage();
            Image player4 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player4.png").getImage();


            // 绘制图片
            g.drawImage(image, 0, 0, this);
            g.drawImage(player1, 10, 0, 200, 200, this);
            g.drawImage(player2, 10, 750, 200, 200, this);
            g.drawImage(player3, 810, 750, 200, 200, this);
            g.drawImage(player4, 810, 0, 200, 200, this);

            Graphics2D g2 = (Graphics2D) g.create();
           // Graphics g2 =  g;
            initialPlayerTiles(g2);

//            if (game.isPaused() && !game.isGameOver()) {
//                g.setColor(Color.WHITE);
//                g.drawString("Press 'p' to continue ", 256, 256);
//            } else if (game.isGameOver()) {
//                g.setColor(Color.WHITE);
//                g.drawString("Game over ", 480, 256);
//            }
        }
    }


    protected void initialPlayerTiles(Graphics2D g2d) {
        Image tile_Face = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Face.png").getImage();
        Image tile_Back = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Back.png").getImage();
        // 获取屏幕的宽度
        int mahjongWidth = 60;
        int mahjongHeight = 90;

        // 计算麻将的起始绘制位置，确保它们在屏幕下方
        int startX1 = (Constant.SCREEN_WIDTH - (13 * mahjongWidth )) / 2;
        int startY1 = getHeight() - mahjongHeight - 20; // 20像素距离屏幕底部

        // 在屏幕下方从左到右绘制13张相同的空白麻将图片
        for (int i = 0; i < 13; i++) {
            g2d.drawImage(tile_Face, startX1 + i * (mahjongWidth ), startY1, mahjongWidth, mahjongHeight, this);
        }
        for (int i = 0; i < 13; i++) {
            g2d.drawImage(tile_Back, startX1 + i * (mahjongWidth ), startY1, mahjongWidth, mahjongHeight, this);
        }

    }

//    protected void initialConputerPlayerTiles(Graphics g) {
//        Image tile_Face = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Face.png").getImage();
//        Image tile_Back = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Back.png").getImage();
//
//
//
//    }


}




