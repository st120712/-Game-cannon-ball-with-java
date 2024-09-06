package com.nhnacademy.game.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.ball.Ball;
import com.nhnacademy.game.ball.BoundedBall;
import com.nhnacademy.game.ball.MovableBall;
import com.nhnacademy.game.ball.PaintableBall;
import com.nhnacademy.game.exception.AlreadyExistException;
import com.nhnacademy.game.exception.OutOfBoundsException;

public class World extends JPanel {

    protected List<Ball> ballList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(World.class);

    public void add(Ball ball) {
        if (Objects.isNull(ball)) {
            throw new NullPointerException("ball : null입니다.");
        }

        if (ballList.contains(ball)) {
            throw new AlreadyExistException("공이 이미 존재합니다.");
        }

        if (ball.getMinX() < getBounds().getMinX() || ball.getMaxX() > getBounds().getMaxX()
                || ball.getMinY() < getBounds().getMinY()
                || ball.getMaxY() > getBounds().getMaxY()) {
            throw new OutOfBoundsException("공이 범위를 벗어났습니다.");
        }

        ballList.add(ball);
        logger.info("ball 추가 : {}", ball);
    }

    public void remove(Ball ball) {
        if (Objects.isNull(ball)) {
            throw new NullPointerException();
        }

        if (!ballList.contains(ball)) {
            throw new NoSuchElementException();
        }

        ballList.remove(ball);
        logger.info("ball 삭제 : {}", ball);
    }

    public void remove(int index) {
        if (index > ballList.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        Ball temp = ballList.get(index);

        ballList.remove(temp);
        logger.info("ball 삭제 : {}", temp);
    }

    public int getBallCount() {
        return ballList.size();
    }

    Ball getBall(int index) {
        if (index > ballList.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        return ballList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Ball ball : ballList) {
            if (ball.getClass().equals(PaintableBall.class)
                    || ball.getClass().equals(MovableBall.class)
                    || ball.getClass().equals(BoundedBall.class)) {
                ((PaintableBall) ball).paint(g);
            }
        }
    }

}
