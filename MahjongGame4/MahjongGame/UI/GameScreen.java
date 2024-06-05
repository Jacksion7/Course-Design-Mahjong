package UI;

import Mahjong.MahjongGameManager;
import Mahjong.MahjongTile;
import Players.Player;
import Players.PlayerManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Players.Player;
import Mahjong.MahjongTile;

public class GameScreen extends JFrame { ;

    int mahjongWidth = 40;
    int mahjongHeight = 60;
    int startX1 = (Constant.SCREEN_WIDTH - (13 * mahjongWidth)) / 2;
    int startY1 = 800 - mahjongHeight - 30; // 20像素距离屏幕底部
    int startX2 = (13 * mahjongWidth) + 40;
    int startY2 = mahjongHeight; // 20像素距离屏幕底部
    int startY3 = mahjongHeight + 80; // 20像素距离屏幕底部

    static Image bgTile = new ImageIcon("MahjongGame4/imgSet/MahjongTile/tile_Face.png").getImage();

    //private static JPanel  panel;

    private int update_times=0;

    public static Player player;

    public Player[] players;// players connect with GameScreen

    public static List<MahjongTile> Player_hand;// connect with Game Screen



    private static MahjongTile selectedCard ;
    private static JLabel selectedLabel;



    public GameScreen() {
        players= MahjongGameManager.getPlayers();
        JFrame frame = new JFrame();
        frame.setTitle("Image Frame Example");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println("GameS");


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                //super.paintComponent(g);
                drawBackground(g);
                drawPlayers(g);
                drawPlayerTiles(g);

                update_player_and();// update

                for (int i = 0; i < MahjongGameManager.Player_hand.size(); i++) {
                    MahjongTile tile = MahjongGameManager.Player_hand.get(i);
                    matchTilesWithImage(tile);
                    JLabel t = paintTiles(tile, i);
                    add(t);
                }
                if(MahjongGameManager.ifDiscardTiles) {
                    drawUsedTiles(g);
                }
            }


        }; // 设置面板布局
        panel.setLayout(null);

        JButton button = new JButton();
        JButton draw_button = new JButton("Draw ");
        JButton button3 = new JButton("Select ");
        button.setOpaque(false);

        draw_button.addActionListener(e -> moveSelectedLabelsToCenter());

        ImageIcon touchCon = new ImageIcon(getScaledImage("MahjongGame4/imgSet/PlayScreen/button/touch.png", 90, 130));
        button.setIcon(touchCon);
        ImageIcon drawCon = new ImageIcon(getScaledImage("MahjongGame4/imgSet/PlayScreen/button/draw.png", 90, 130));
        draw_button.setIcon(drawCon);
        ImageIcon selectCon = new ImageIcon(getScaledImage("MahjongGame4/imgSet/PlayScreen/button/select.png", 80, 120));
        button3.setIcon(selectCon);

        button.setBounds(200, 550, 70, 100);
        draw_button.setBounds(350, 550, 70, 100);
        button3.setBounds(500, 550, 70, 100);

        panel.add(button);
        panel.add(draw_button);
        panel.add(button3);


        // 将面板添加到窗口中
        add(panel);


    }


    private void drawUsedTiles(Graphics g) {
        int[] xy = {200, 200, 50, 80};
        for (int i = 0; i < MahjongGameManager.usedTiles.size(); i++) {
            MahjongTile curTile = MahjongGameManager.usedTiles.get(i);
            int x = xy[0] + (i % 10) * xy[2];
            int y = xy[1] + (i / 10 % 5) * xy[3];
            drawBackgroundTile(g, x, y - 10, mahjongWidth, mahjongHeight + 10);
            matchTilesWithImage(curTile);
            Image image = new ImageIcon(curTile.ImagePath).getImage();
            System.out.println(curTile.ImagePath);
            g.drawImage(image, x, y, mahjongWidth, mahjongHeight, this);
            if (i == MahjongGameManager.usedTiles.size() - 1) {
                Graphics2D g2d = (Graphics2D) g;
                float thickness = 4;
                g2d.setStroke(new BasicStroke(thickness));

                g2d.drawRect(x, y - 10, mahjongWidth, mahjongHeight + 9);
            }
        }
    }

    public void drawRuleTiles_1(Graphics g){
        for(int i = 0; i < players[0].rulesTiles.size(); i++) {
            MahjongTile curTile = MahjongGameManager.usedTiles.get(i);
            //int x =
        }
    }

    private void drawBackgroundTile(Graphics g, int x, int y, int dx, int dy) {
        g.drawImage( bgTile, x, y, dx, dy, this);
    }





    public void update_player_hand(){
        player=MahjongGameManager.player;// 把MJ manager的player 传过来
    }

    public void update_player_and(){
        Player_hand = MahjongGameManager.Player_hand;
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


    public JLabel paintTiles(MahjongTile tile, int index) {
        // assign the ImagePath of tiles
        ImageIcon originalIcon = new ImageIcon(tile.ImagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(mahjongWidth, mahjongHeight, Image.SCALE_FAST);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel card = new JLabel(scaledIcon);
        card.setOpaque(true);
        card.setHorizontalAlignment(SwingConstants.CENTER);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setBounds(startX1 - 120 + index * 40, startY1 -40 , mahjongWidth, mahjongHeight);

        card.addMouseListener(new MouseAdapter() {
            private int initialY; // 初始Y坐标
            public void mousePressed(MouseEvent e) {
                selectedLabel = card;
                selectedCard = tile;
                //redrawWindow();
                initialY = card.getY(); // 记录初始Y坐标
                card.setLocation(card.getX(), initialY - 20);
                card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            }
        });

        return card;
    }

    private static void moveSelectedLabelsToCenter() {
        if (selectedLabel != null) {
            Container parent = selectedLabel.getParent();
            int parentWidth = parent.getWidth();
            int parentHeight = parent.getHeight();

            int labelWidth = selectedLabel.getWidth();
            int labelHeight = selectedLabel.getHeight();

            int centerX = (parentWidth - labelWidth) / 2;
            int centerY = (parentHeight - labelHeight) / 2;

            selectedLabel.setLocation(centerX, centerY);

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
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong1.png";
            }
            if (tile.getValue() == 2) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong2.png";
            }
            if (tile.getValue() == 3) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong3.png";
            }
            if (tile.getValue() == 4) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong4.png";
            }
            if (tile.getValue() == 5) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong5.png";
            }
            if (tile.getValue() == 6) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong6.png";
            }
            if (tile.getValue() == 7) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong7.png";
            }
            if (tile.getValue() == 8) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong8.png";
            }
            if (tile.getValue() == 9) {
                tile.ImagePath = "MahjongGame4/imgSet/MahjongTile/tong/tong9.png";
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

    private void drawBackground(Graphics g) {
        Image image = new ImageIcon("MahjongGame4/imgSet/playBackground.png").getImage();
        g.drawImage(image, 0, 0, 800, 800, this);
    }

    private void drawPlayers(Graphics g) {
        Image player1 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player1.png").getImage();
        Image player2 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player2.png").getImage();
        Image player3 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player3.png").getImage();
        Image player4 = new ImageIcon("MahjongGame4/imgSet/PlayScreen/player4.png").getImage();

        g.drawImage(player1, 10, 10, 100, 100, this);
        g.drawImage(player2, 10, 660, 100, 100, this);
        g.drawImage(player3, 660, 660, 100, 100, this);
        g.drawImage(player4, 660, 10, 100, 100, this);
    }

    private void drawPlayerTiles(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        initialPlayerTiles(g2);
    }

//    private void addButtons() {
//        JButton button = new JButton();
//        JButton draw_button = new JButton("Draw ");
//        JButton button3 = new JButton("Select ");
//        button.setOpaque(false);
//
//        draw_button.addActionListener(e -> moveSelectedLabelsToCenter());
//
//        ImageIcon touchCon = new ImageIcon(getScaledImage("MahjongGame4/imgSet/PlayScreen/button/touch.png", 90, 130));
//        button.setIcon(touchCon);
//        ImageIcon drawCon = new ImageIcon(getScaledImage("MahjongGame4/imgSet/PlayScreen/button/draw.png", 90, 130));
//        draw_button.setIcon(drawCon);
//        ImageIcon selectCon = new ImageIcon(getScaledImage("MahjongGame4/imgSet/PlayScreen/button/select.png", 80, 120));
//        button3.setIcon(selectCon);
//
//        button.setBounds(200, 550, 70, 100);
//        draw_button.setBounds(350, 550, 70, 100);
//        button3.setBounds(500, 550, 70, 100);
//
//        panel.add(button);
//        panel.add(draw_button);
//        panel.add(button3);
//    }

    private Image getScaledImage(String imagePath, int width, int height) {
        Image image = new ImageIcon(imagePath).getImage();
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }




}






