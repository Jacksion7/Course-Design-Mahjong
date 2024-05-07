package UI;

import javax.swing.*;
import java.awt.*;

public class PlayScreen extends JFrame {

    public PlayScreen() {
        setTitle("Image Frame Example");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个JPanel用于显示图片
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 加载图片文件
                Image image = new ImageIcon("MahjongGame4/imgSet/playBackground.png").getImage();
                // 绘制图片
                g.drawImage(image, 0, 0, this);
            }
        };

        add(panel);
    }

}

