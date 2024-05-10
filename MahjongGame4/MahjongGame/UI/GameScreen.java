package UI;

import Mahjong.MahjongGameManager;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private MahjongGameManager game;

    public GameScreen(MahjongGameManager game) {
        this.game = game;
    }

    protected void paintComponent(Graphics g) {

        if (game != null) {
            Graphics2D g2 = (Graphics2D) g;
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


//            if (game.isPaused() && !game.isGameOver()) {
//                g.setColor(Color.WHITE);
//                g.drawString("Press 'p' to continue ", 256, 256);
//            } else if (game.isGameOver()) {
//                g.setColor(Color.WHITE);
//                g.drawString("Game over ", 480, 256);
//            }
        }
    }

}




