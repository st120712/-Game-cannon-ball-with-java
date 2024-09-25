package com.nhnacademy.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.component.TextButton;
import com.nhnacademy.game.effect.Effect;
import com.nhnacademy.game.obj.ball.Ball;
import com.nhnacademy.game.obj.ball.BoundedBall;
import com.nhnacademy.game.obj.ball.MovableBall;
import com.nhnacademy.game.obj.ball.PaintableBall;
import com.nhnacademy.game.obj.box.PaintableBox;
import com.nhnacademy.game.obj.cannon.Cannon;
import com.nhnacademy.game.obj.triangle.MovableTriangle;
import com.nhnacademy.game.vector.DisplacementVector;
import com.nhnacademy.game.world.BoundedWorld;
import com.nhnacademy.game.world.MovableWorld;
import com.nhnacademy.game.world.World;

public class Main {
    static final Color[] COLORS =
            new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.WHITE};
    static final int FRAME_WIDTH = 1500;
    static final int FRAME_HEIGHT = 750;

    static final int BALL_COUNT = 5;
    static final int BOX_COUNT = 0;
    static final int TRIANGLE_COUNT = 5;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int ballCount = 0;
        int boxCount = 0;
        int triangleCount = 0;

        JFrame frame = new JFrame();
        Random rand = new Random();
        BoundedWorld world = new BoundedWorld();

        Effect gravityEffect = new Effect(0, 2);
        Effect windEffect = new Effect(-2, 0);


        world.setBackground(Color.black);
        world.addEffect(gravityEffect);
        world.addEffect(windEffect);
        // world.addEffect(new Effect(0, 0));
        world.setBounds(300, 0, 1200, 600);

        JLabel velocityLabel = new JLabel("속도");
        velocityLabel.setFont(new Font("velocity", Font.BOLD, 20));
        JSlider velocitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        velocitySlider.setPreferredSize(new Dimension(250, 100));
        velocitySlider.setMinorTickSpacing(5);
        velocitySlider.setMajorTickSpacing(20);
        velocitySlider.setPaintTicks(true);
        velocitySlider.setPaintLabels(true);

        JLabel angleLabel = new JLabel("각도");
        angleLabel.setFont(new Font("angle", Font.BOLD, 20));
        JSlider angleSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 45);
        angleSlider.setPreferredSize(new Dimension(250, 100));
        angleSlider.setMinorTickSpacing(5);
        angleSlider.setMajorTickSpacing(20);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);

        JLabel gravityLabel = new JLabel("중력");
        gravityLabel.setFont(new Font("gravity", Font.BOLD, 20));
        JSlider gravitySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        gravitySlider.setPreferredSize(new Dimension(250, 100));
        gravitySlider.setMajorTickSpacing(2);
        gravitySlider.setPaintTicks(true);
        gravitySlider.setPaintLabels(true);

        JLabel windLabel = new JLabel("바람");
        windLabel.setFont(new Font("wind", Font.BOLD, 20));
        JSlider windSlider = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        windSlider.setPreferredSize(new Dimension(250, 100));
        windSlider.setMajorTickSpacing(2);
        windSlider.setPaintTicks(true);
        windSlider.setPaintLabels(true);

        JPanel totalSliderPanel = new JPanel();
        totalSliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        totalSliderPanel.setSize(280, 600);
        totalSliderPanel.setLocation(10, 0);
        totalSliderPanel.add(velocityLabel);
        totalSliderPanel.add(velocitySlider);
        totalSliderPanel.add(angleLabel);
        totalSliderPanel.add(angleSlider);
        totalSliderPanel.add(gravityLabel);
        totalSliderPanel.add(gravitySlider);
        totalSliderPanel.add(windLabel);
        totalSliderPanel.add(windSlider);

        ActionListener fireActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gravityEffect.setDy(gravitySlider.getValue());
                world.start();
            }
        };
        TextButton fireButton = new TextButton("Fire!", fireActionListener);

        ActionListener clearActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.stop();
            }
        };
        TextButton clearButton = new TextButton("Stop!", clearActionListener);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        btnPanel.setSize(300, 100);
        btnPanel.setLocation(0, 620);
        btnPanel.add(fireButton);
        btnPanel.add(clearButton);

        frame.add(totalSliderPanel);
        frame.add(btnPanel);

        frame.setBackground(Color.white);
        frame.setLocation(500, 1600);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.add(world);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // world.add(cannon);

        // while (boxCount != BOX_COUNT) {
        // int width = 10 + rand.nextInt(100);
        // int height = 10 + rand.nextInt(100);
        // int x = width + rand.nextInt(FRAME_WIDTH - width);
        // int y = height + rand.nextInt(FRAME_HEIGHT - height);

        // Rectangle bounds = new Rectangle(x, y, width, height);

        // try {
        // PaintableBox box = new PaintableBox(bounds, COLORS[rand.nextInt(COLORS.length)]);
        // world.add(box);
        // boxCount++;
        // } catch (Exception e) {
        // // logger.error("ADD BALL {} : {}", e, e.getStackTrace());
        // }
        // }

        while (ballCount != BALL_COUNT) {
            int radius = 10 + rand.nextInt(10);
            int x = radius + rand.nextInt(FRAME_WIDTH - 2 * radius);
            int y = radius + rand.nextInt(FRAME_HEIGHT - 2 * radius);

            Rectangle bounds = new Rectangle(x, y, 2 * radius, 2 * radius);

            try {
                MovableBall ball = new MovableBall(bounds, COLORS[rand.nextInt(COLORS.length)]);
                // ball.setBoundedArea(new Rectangle(300, 0, 1200, 700));
                ball.getMotion().setDx(rand.nextInt(5) + 5);
                ball.getMotion().setDy(rand.nextInt(5) + 5);
                world.add(ball);
                ballCount++;
            } catch (Exception e) {
                logger.error("ADD BALL {} : {}", e, e.getStackTrace());
            }
        }

        // while (triangleCount != TRIANGLE_COUNT) {
        // int width = 10 + rand.nextInt(100);
        // int height = 10 + rand.nextInt(100);
        // int x = width + rand.nextInt(FRAME_WIDTH - width);
        // int y = height + rand.nextInt(FRAME_HEIGHT - height);

        // Rectangle bounds = new Rectangle(x, y, width, height);

        // try {
        // MovableTriangle triangle =
        // new MovableTriangle(bounds, COLORS[rand.nextInt(COLORS.length)]);
        // triangle.getMotion().setDx(rand.nextInt(3) + 5);
        // triangle.getMotion().setDy(rand.nextInt(5) + 5);
        // world.add(triangle);
        // triangleCount++;
        // } catch (Exception e) {
        // // logger.error("ADD BALL {} : {}", e, e.getStackTrace());
        // }
        // }

        world.paint(world.getGraphics());

        try {
            world.start();
        } catch (Exception e) {
            logger.error("RUN {} : {}", e, e.getStackTrace());
        }
    }
}
