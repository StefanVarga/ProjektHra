package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameGraphics extends JFrame {
    private GameLogic logic;
    private Draw draw;
    private BufferedImage menu, gameOver, gameRules;

    public GameGraphics(GameLogic logic) throws HeadlessException {
        this.logic = logic;
        this.draw = new Draw();




        setSize(800, 510);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Hra");
        setResizable(false);
        addKeyListener(logic.car);
        addKeyListener(logic);

        add(draw);
        try {
            menu = ImageIO.read(new File("src/main/resources/barrier.jpg"));
            gameOver = ImageIO.read(new File("src/main/resources/gameOver.png"));
            gameRules = ImageIO.read(new File("src/main/resources/bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
                 }

    }

    public void render(GameLogic logic) {
        this.logic = logic;
        repaint();
    }
    public class Draw extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("NFS Font", Font.BOLD, 30));
            g.setColor(Color.gray);

            if(logic.gameActive == 0) {
                g.drawImage(menu, 0, 0, 800, 500, null);
                if(logic.gameRules) {
                    g.drawImage(gameRules, 0, 0, 800, 500, null);
                }
            }
            if (logic.gameActive == 1) {
                for (Object object : logic.backgrounds) {
                    object.draw(g);
                }
                for(Object hearth: logic.hearts) {
                    hearth.draw(g);
                }
                logic.car.draw(g);
                for (Object object : logic.points) {
                    object.draw(g);
                }
                for (Object barrier : logic.barriers) {
                    barrier.draw(g);
                }
                g.drawString("Health   " + logic.car.health+" /3", 550, 30);
            }
            if(logic.gameActive == 2) {
                g.drawImage(gameOver, 0, 0, 800, 500, null);
            }
            g.drawString("Score   " + logic.score, 10, 30);
        }
    }
}
