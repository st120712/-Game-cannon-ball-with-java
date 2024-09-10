package com.nhnacademy.game;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.obj.ball.Ball;
import com.nhnacademy.game.obj.ball.BoundedBall;
import com.nhnacademy.game.obj.ball.MovableBall;
import com.nhnacademy.game.obj.ball.PaintableBall;
import com.nhnacademy.game.obj.box.PaintableBox;
import com.nhnacademy.game.obj.triangle.MovableTriangle;
import com.nhnacademy.game.world.BoundedWorld;
import com.nhnacademy.game.world.MovableWorld;
import com.nhnacademy.game.world.World;

public class Main {
    static final Color[] COLORS =
            new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.WHITE};
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 800;
    static final int BALL_COUNT = 5;
    static final int BOX_COUNT = 10;
    static final int TRIANGLE_COUNT = 5;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int ballCount = 0;
        int boxCount = 0;
        int triangleCount = 0;

        JFrame frame = new JFrame();
        Random rand = new Random();
        BoundedWorld world = new BoundedWorld();
        world.setBackground(Color.black);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(world);
        frame.setVisible(true);

        while (boxCount != BOX_COUNT) {
            int width = 10 + rand.nextInt(100);
            int height = 10 + rand.nextInt(100);
            int x = width + rand.nextInt(FRAME_WIDTH - width);
            int y = height + rand.nextInt(FRAME_HEIGHT - height);

            Rectangle bounds = new Rectangle(x, y, width, height);

            try {
                PaintableBox box = new PaintableBox(bounds, COLORS[rand.nextInt(COLORS.length)]);
                world.add(box);
                boxCount++;
            } catch (Exception e) {
                // logger.error("ADD BALL {} : {}", e, e.getStackTrace());
            }
        }

        while (ballCount != BALL_COUNT) {
            int radius = 10 + rand.nextInt(10);
            int x = radius + rand.nextInt(FRAME_WIDTH - 2 * radius);
            int y = radius + rand.nextInt(FRAME_HEIGHT - 2 * radius);

            Rectangle bounds = new Rectangle(x, y, 2 * radius, 2 * radius);

            try {
                MovableBall ball = new MovableBall(bounds, COLORS[rand.nextInt(COLORS.length)]);
                // ball.setBoundedArea(new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
                ball.getMotion().setDx(rand.nextInt(3) + 5);
                ball.getMotion().setDy(rand.nextInt(5) + 5);
                world.add(ball);
                ballCount++;
            } catch (Exception e) {
                // logger.error("ADD BALL {} : {}", e, e.getStackTrace());
            }
        }

        while (triangleCount != TRIANGLE_COUNT) {
            int width = 10 + rand.nextInt(100);
            int height = 10 + rand.nextInt(100);
            int x = width + rand.nextInt(FRAME_WIDTH - width);
            int y = height + rand.nextInt(FRAME_HEIGHT - height);

            Rectangle bounds = new Rectangle(x, y, width, height);

            try {
                MovableTriangle triangle =
                        new MovableTriangle(bounds, COLORS[rand.nextInt(COLORS.length)]);
                triangle.getMotion().setDx(rand.nextInt(3) + 5);
                triangle.getMotion().setDy(rand.nextInt(5) + 5);
                world.add(triangle);
                triangleCount++;
            } catch (Exception e) {
                // logger.error("ADD BALL {} : {}", e, e.getStackTrace());
            }
        }

        world.paint(frame.getGraphics());

        try {
            world.run();
        } catch (Exception e) {
            logger.error("RUN {} : {}", e, e.getStackTrace());
        }
    }
}
