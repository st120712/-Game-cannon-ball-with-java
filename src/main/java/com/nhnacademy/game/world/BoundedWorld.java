package com.nhnacademy.game.world;

import com.nhnacademy.game.ball.Ball;
import com.nhnacademy.game.ball.BoundedBall;
import com.nhnacademy.game.ball.MovableBall;
import java.awt.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoundedWorld extends MovableWorld {

    private static final Logger logger = LoggerFactory.getLogger(BoundedWorld.class);

    public BoundedWorld() {
        super(0);
    }

    private int[] amountOver(MovableBall ball) {
        int overX = 0;
        int overY = 0;

        if (ball.getDX() < 0) {
            if (ball.getMinX() + ball.getDX() < getX()) {
                overX = (int) getX() - (ball.getMinX() + ball.getDX());
                ball.setDX(-ball.getDX());
            }
        } else {
            if (ball.getMaxX() + ball.getDX() > getX() + getWidth()) {
                overX = (ball.getMaxX() + ball.getDX()) - (getX() + getWidth());
                ball.setDX(-ball.getDX());
            }
        }

        if (ball.getDY() < 0) {
            if (ball.getMinY() + ball.getDY() < getY()) {
                overY = (int) getY() - (ball.getMinY() + ball.getDY());
                ball.setDY(-ball.getDY());
            }
        } else {
            if (ball.getMaxY() + ball.getDY() > getY() + getHeight()) {
                overY = (ball.getMaxY() + ball.getDY()) - (getY() + getHeight());
                ball.setDY(-ball.getDY());
            }
        }

        int[] arr = {overX, overY};

        return arr;
    }

    @Override
    public void move() {
        repaint();

        for (Ball ball : ballList) {
            if (ball.getClass().equals(MovableBall.class)) {
                int[] distance = amountOver((MovableBall) ball);

                ((MovableBall) ball).moveTo(
                        ((MovableBall) ball).getX() + ((MovableBall) ball).getDX() - distance[0],
                        ((MovableBall) ball).getY() + ((MovableBall) ball).getDY() - distance[1]);
            }

            if (ball.getClass().equals(BoundedBall.class)) {
                ((BoundedBall) ball).setBoundedArea(getBounds());
                ((BoundedBall) ball).move();
            }
        }
    }
}
