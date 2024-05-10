package UI;

import Mahjong.MahjongGameManager;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JPanel {
    private MahjongGameManager game;

    protected void paintComponent(Graphics g) {



            Image image = new ImageIcon("MahjongGame4/imgSet/menuBackground.png").getImage();
            Image startGame = new ImageIcon("MahjongGame4/imgSet/startGame.png").getImage();
            Image controlMenu = new ImageIcon("MahjongGame4/imgSet/controlMenu.png").getImage();
            Image highScore = new ImageIcon("MahjongGame4/imgSet/HighScore.png").getImage();
            Image menuTitle = new ImageIcon("MahjongGame4/imgSet/MenuTitle.png").getImage();

            // 绘制图片
            g.drawImage(image, 0, 0, this);
            g.drawImage(startGame,130,700,200,200,this);
            g.drawImage(controlMenu,430,700,200,200,this);
            g.drawImage(highScore,730,700,200,200,this);
            g.drawImage(menuTitle,300,0,400,400,this);


    }

}
