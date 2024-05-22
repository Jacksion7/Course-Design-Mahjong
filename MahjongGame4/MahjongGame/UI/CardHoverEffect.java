package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardHoverEffect extends JFrame {
    private static final int NUM_CARDS = 14;
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 150;
    private static final int HOVER_DISTANCE = 20;

    public CardHoverEffect() {
        setTitle("Card Hover Effect");
        setSize(1600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        for (int i = 0; i < NUM_CARDS; i++) {
            JLabel card = createCard(i);
            add(card);
        }

        setVisible(true);
    }

    private JLabel createCard(int index) {
        JLabel card = new JLabel("Card " + (index + 1));
        card.setOpaque(true);
        card.setBackground(Color.LIGHT_GRAY);
        card.setHorizontalAlignment(SwingConstants.CENTER);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setBounds(index * (CARD_WIDTH + 10), 100, CARD_WIDTH, CARD_HEIGHT);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBounds(card.getX(), card.getY() - HOVER_DISTANCE, CARD_WIDTH, CARD_HEIGHT);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBounds(card.getX(), card.getY() + HOVER_DISTANCE, CARD_WIDTH, CARD_HEIGHT);
            }
        });

        return card;
    }


    private void move(){
        SwingUtilities.invokeLater(CardHoverEffect::new);
    }

    public static void main(String[] args) {
       CardHoverEffect cardHoverEffect = new CardHoverEffect();
       cardHoverEffect.move();
    }
}

