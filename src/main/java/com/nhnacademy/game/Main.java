package com.nhnacademy.game;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

public class Main {
    static final Color[] COLORS =
            new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.WHITE};
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 1000;
    static final int BALL_COUNT = 10;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        World world = new World();
        Random rand = new Random();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(world);
        frame.setVisible(true);

        while (world.getBallCount() != BALL_COUNT) {
            int radius = 10 + rand.nextInt(110);
            int x = radius + rand.nextInt(FRAME_WIDTH - 2 * radius);
            int y = radius + rand.nextInt(FRAME_HEIGHT - 2 * radius);

            try {
                world.add(new PaintableBall(x, y, radius, COLORS[rand.nextInt(COLORS.length)]));
            } catch (Exception e) {
            }
        }

        world.paint(frame.getGraphics());
    }
}
