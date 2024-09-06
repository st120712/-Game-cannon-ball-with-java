package com.nhnacademy.game;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.ball.Ball;
import com.nhnacademy.game.ball.BoundedBall;
import com.nhnacademy.game.ball.MovableBall;
import com.nhnacademy.game.world.BoundedWorld;
import com.nhnacademy.game.world.MovableWorld;
import com.nhnacademy.game.world.World;

public class Main {
    static final Color[] COLORS =
            new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.WHITE};
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 800;
    static final int BALL_COUNT = 10;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        Random rand = new Random();
        World world = new BoundedWorld();
        world.setBackground(Color.black);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(world);
        frame.setVisible(true);

        while (world.getBallCount() != BALL_COUNT) {
            int radius = 10 + rand.nextInt(100);
            int x = radius + rand.nextInt(FRAME_WIDTH - 2 * radius);
            int y = radius + rand.nextInt(FRAME_HEIGHT - 2 * radius);

            try {
                BoundedBall ball =
                        new BoundedBall(x, y, radius, COLORS[rand.nextInt(COLORS.length)]);
                ball.setBoundedArea(new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
                ball.setDX(rand.nextInt(3) + 5);
                ball.setDY(rand.nextInt(5) + 5);
                world.add(ball);
            } catch (Exception e) {
                logger.error("ADD BALL {} : {}", e, e.getStackTrace());
            }
        }

        world.paint(frame.getGraphics());
        try {
            ((MovableWorld) world).run();
        } catch (Exception e) {
            logger.error("RUN {} : {}", e, e.getStackTrace());
        }
    }
}
