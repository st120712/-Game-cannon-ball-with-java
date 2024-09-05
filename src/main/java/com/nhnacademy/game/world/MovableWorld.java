package com.nhnacademy.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.ball.Ball;
import com.nhnacademy.game.ball.MovableBall;

public class MovableWorld extends World {
    protected int moveCount = 0;
    protected int maxMoveCount;
    protected int dt = 100;

    private static final Logger logger = LoggerFactory.getLogger(MovableWorld.class);

    public MovableWorld() {
        this(0);
    }

    public MovableWorld(int maxMoveCount) {
        setMaxMoveCount(maxMoveCount);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int maxMoveCount) {
        if (maxMoveCount < 0) {
            throw new IllegalArgumentException();
        }

        this.maxMoveCount = maxMoveCount;
    }

    public int getDT() {
        return dt;
    }

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }

        this.dt = dt;
    }

    public void reset() {
        moveCount = 0;
    }

    private void move() {
        repaint();

        for (Ball ball : ballList) {
            if (ball.getClass().equals(MovableBall.class)) {
                ((MovableBall) ball).move();
            }
        }

        moveCount++;
    }

    public void run() {
        long startTime = System.currentTimeMillis();

        logger.info("start");

        while (maxMoveCount == 0 ? true : moveCount < maxMoveCount) {
            long oldTime = System.currentTimeMillis();

            move();

            long currentTime = System.currentTimeMillis();
            try {
                long tempDt = currentTime - oldTime;
                if (getDT() > tempDt) {
                    Thread.sleep(getDT() - tempDt);
                }
            } catch (InterruptedException e) {
            }
        }

        logger.info("finished : {}", System.currentTimeMillis() - startTime);
    }
}
