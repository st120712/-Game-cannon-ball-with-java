package com.nhnacademy.game.aaaaaa;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import com.nhnacademy.game.component.TextButton;
import com.nhnacademy.game.effect.Effect;
import com.nhnacademy.game.obj.ball.MovableBall;
import com.nhnacademy.game.world.BoundedWorld;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MBBW extends JFrame {
    static final Color[] COLORS =
            new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.WHITE};
    static final int FRAME_WIDTH = 1500;
    static final int FRAME_HEIGHT = 750;
    static final int BALL_COUNT = 5;
    static final int GRAVITY = 2;
    static final int WIND = -2;

    public MBBW() {
        int ballCount = 0;
        Random rand = new Random();
        Effect gravityEffect = new Effect(0, GRAVITY);
        Effect windEffect = new Effect(WIND, 0);

        BoundedWorld world = new BoundedWorld();
        world.setBackground(Color.black);
        world.addEffect(gravityEffect);
        world.addEffect(windEffect);
        world.setBounds(300, 0, 1200, 600);

        JLabel velocityLabel = new JLabel("속도");
        velocityLabel.setFont(new Font("velocity", Font.BOLD, 20));
        JSlider velocitySlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
        velocitySlider.setPreferredSize(new Dimension(250, 100));
        velocitySlider.setMinorTickSpacing(1);
        velocitySlider.setMajorTickSpacing(5);
        velocitySlider.setPaintTicks(true);
        velocitySlider.setPaintLabels(true);

        JLabel angleLabel = new JLabel("각도");
        angleLabel.setFont(new Font("angle", Font.BOLD, 20));
        JSlider angleSlider = new JSlider(JSlider.HORIZONTAL, 0, 90, 45);
        angleSlider.setPreferredSize(new Dimension(250, 100));
        angleSlider.setMinorTickSpacing(5);
        angleSlider.setMajorTickSpacing(10);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);

        JLabel gravityLabel = new JLabel("중력");
        gravityLabel.setFont(new Font("gravity", Font.BOLD, 20));
        JSlider gravitySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, GRAVITY);
        gravitySlider.setPreferredSize(new Dimension(250, 100));
        gravitySlider.setMajorTickSpacing(2);
        gravitySlider.setPaintTicks(true);
        gravitySlider.setPaintLabels(true);

        JLabel windLabel = new JLabel("바람");
        windLabel.setFont(new Font("wind", Font.BOLD, 20));
        JSlider windSlider = new JSlider(JSlider.HORIZONTAL, -10, 10, WIND);
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
                windEffect.setDx(windSlider.getValue());
                world.setMovableBallMotion(
                        (int) (velocitySlider.getValue()
                                * Math.cos(Math.toRadians(angleSlider.getValue()))),
                        (int) (velocitySlider.getValue()
                                * Math.sin(Math.toRadians(angleSlider.getValue()))));
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

        add(totalSliderPanel);
        add(btnPanel);
        add(world);

        setBackground(Color.white);
        setLocation(500, 1600);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (ballCount != BALL_COUNT) {
            int radius = 10 + rand.nextInt(10);
            int x = radius + rand.nextInt(FRAME_WIDTH - 2 * radius);
            int y = radius + rand.nextInt(FRAME_HEIGHT - 2 * radius);

            Rectangle bounds = new Rectangle(x, y, 2 * radius, 2 * radius);

            try {
                MovableBall ball = new MovableBall(bounds, COLORS[rand.nextInt(COLORS.length)]);
                ball.getMotion().setDx(rand.nextInt(5) + 5);
                ball.getMotion().setDy(rand.nextInt(5) + 5);
                world.add(ball);
                ballCount++;
            } catch (Exception e) {
            }
        }

        world.paint(world.getGraphics());

        try {
            world.start();
        } catch (Exception e) {
        }
    }
}
