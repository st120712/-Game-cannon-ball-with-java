package com.nhnacademy.game.world;

import com.nhnacademy.game.ball.Ball;
import com.nhnacademy.game.ball.BoundedBall;
import com.nhnacademy.game.ball.MovableBall;

public class BoundedWorld extends MovableWorld {

    public BoundedWorld() {
        super(0);
    }

    public boolean outOfBounds(Ball ball) {


        return true;
    }

    public void move() {
        repaint();

        for (Ball ball : ballList) {
            if (ball.getClass().equals(MovableBall.class)) {
                if (outOfBounds(ball)) {

                } else {
                    ((MovableBall) ball).move();
                }
            }

            if (ball.getClass().equals(BoundedBall.class)) {
                ((BoundedBall) ball).setBoundedArea(getBounds());
                ((BoundedBall) ball).move();
            }
        }
    }
}
