package com.nhnacademy.game.aaaaaa;

import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import com.nhnacademy.game.component.TextButton;
import com.nhnacademy.game.effect.Effect;
import com.nhnacademy.game.obj.Movable;
import com.nhnacademy.game.obj.Paintable;
import com.nhnacademy.game.obj.ball.BoundedBall;
import com.nhnacademy.game.obj.box.BreakableBox;
import com.nhnacademy.game.obj.box.PaintableBox;
import com.nhnacademy.game.vector.PositionalVector;
import com.nhnacademy.game.world.MovableWorld;

public class CannonGame extends JFrame {
    static final int FRAME_WIDTH = 1500;
    static final int FRAME_HEIGHT = 750;
    static final int TARGET_COUNT = 1;
    static final int GRAVITY = 2;
    static final int WIND = -2;

    int targetCount = 0;
    Random rand = new Random();
    MovableWorld world;

    public CannonGame() {

        // 이펙트 정의
        Effect gravityEffect = new Effect(0, GRAVITY);
        Effect windEffect = new Effect(WIND, 0);

        // world 설정
        world = new MovableWorld();
        world.setBackground(Color.white);
        world.addEffect(gravityEffect);
        world.addEffect(windEffect);
        world.setBounds(300, 0, 1200, 600);

        stageSetting();

        // slider 구현
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


        // 버튼 구현 : thread start
        ActionListener fireActionListener = e -> {
            world.removeBoundalbes(Movable.class);

            BoundedBall ball = new BoundedBall(new Rectangle(50, 500, 25, 25), Color.blue);
            gravityEffect.setDy(gravitySlider.getValue());
            windEffect.setDx(windSlider.getValue());

            PositionalVector ballMotion = new PositionalVector(
                    (int) (velocitySlider.getValue()
                            * Math.cos(Math.toRadians(angleSlider.getValue()))),
                    -(int) (velocitySlider.getValue()
                            * Math.sin(Math.toRadians(angleSlider.getValue()))));

            ball.setMotion(ballMotion);
            world.add(ball);
            ball.start();
        };
        TextButton fireButton = new TextButton("Fire!", fireActionListener);

        ActionListener clearActionListener = e -> {
            world.removeBoundalbes(Movable.class);
        };
        TextButton clearButton = new TextButton("Clear!", clearActionListener);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        btnPanel.setSize(300, 100);
        btnPanel.setLocation(0, 620);
        btnPanel.add(fireButton);
        btnPanel.add(clearButton);

        // jframe에 panel add
        add(totalSliderPanel);
        add(btnPanel);
        add(world);

        // jframe 설정
        setLocation(500, 1600);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // paint
        world.paint(world.getGraphics());

        // thread start
        world.start();
    }

    public void stageSetting() {
        world.removeBoundalbes(Paintable.class);

        // 방해물 랜덤 배치
        int topObstacleHeight = 75 * rand.nextInt(8);
        PaintableBox topObstacle =
                new PaintableBox(new Rectangle(450, 0, 250, topObstacleHeight), Color.GRAY);

        PaintableBox bottomObstacle = new PaintableBox(
                new Rectangle(450, topObstacleHeight + 75, 250, 525 - topObstacleHeight),
                Color.gray);

        world.add(topObstacle);
        world.add(bottomObstacle);

        // 목표물 랜덤 배치
        while (targetCount < TARGET_COUNT) {
            BreakableBox target = new BreakableBox(
                    new Rectangle(700 + 100 * rand.nextInt(5), 75 * rand.nextInt(8), 100, 75),
                    Color.yellow);
            try {
                world.add(target);
                targetCount++;
            } catch (Exception e) {
            }
        }
    }
}