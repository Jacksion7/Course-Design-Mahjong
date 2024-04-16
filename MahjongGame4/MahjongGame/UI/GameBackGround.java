package UI;

import UI.Constant;

import javax.swing.*;
import java.awt.*;

public class GameBackGround extends JFrame{

    public GameBackGround() throws HeadlessException {

    }

    public void draw(Graphics g){

//        g.setColor(Constant.BK_COLOR);
//        g.fillRect(0,0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
//        g.setColor(Color.black);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 加载图片文件
                Image image = new ImageIcon("/Users/zhiqiaozhang/Desktop/未命名文件夹/MahjongGame4/MahjongGame1/MahjongGame/img/麻将桌.png").getImage();
                // 绘制图片
                g.drawImage(image, 0, 0, this);
            }
        };

        add(panel);


    }

}
