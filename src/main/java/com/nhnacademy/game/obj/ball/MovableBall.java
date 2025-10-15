package com.nhnacademy.game.obj.ball;

import java.awt.Color;
import java.util.UUID;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.effect.Effect;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Movable;
import com.nhnacademy.game.vector.PositionalVector;
import java.awt.Rectangle;

public class MovableBall extends PaintableBall implements Movable {
    protected PositionalVector motion = new PositionalVector(10, 10);
    protected int dt = 50;
    protected boolean isMove = false;
    protected Thread thread;
    protected final Object lock = new Object();

    private static final Logger logger = LoggerFactory.getLogger(MovableBall.class);

    public MovableBall(Rectangle bounds) {
        super(bounds);
    }

    public MovableBall(UUID id, Rectangle bounds) {
        super(id, bounds);
    }

    public MovableBall(String id, Rectangle bounds) {
        super(id, bounds);
    }

    public MovableBall(Rectangle bounds, Color color) {
        super(bounds, color);
    }

    public MovableBall(UUID id, Rectangle bounds, Color color) {
        super(id, bounds, color);
    }

    public MovableBall(String id, Rectangle bounds, Color color) {
        super(id, bounds, color);
    }

    @Override
    public int getDt() {
        return dt;
    }

    @Override
    public void setDt(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }

    @Override
    public PositionalVector getMotion() {
        return motion;
    }

    @Override
    public void setMotion(PositionalVector motion) {
        if (Math.abs(motion.getDx()) > 30 || Math.abs(motion.getDy()) > 30) {
            throw new IllegalArgumentException();
        }

        // logger.info("ball (dx, dy): {} 설정", motion);

        this.motion = motion;
    }

    @Override
    public void move() {
        if ((motion.getDx() < 0 && getMinX() < Integer.MIN_VALUE - motion.getDx())
                || (motion.getDy() < 0 && getMinY() < Integer.MIN_VALUE - motion.getDy())
                || (motion.getDx() < 0 && getMaxX() < Integer.MAX_VALUE - motion.getDx())
                || (motion.getDy() < 0 && getMaxY() < Integer.MAX_VALUE - motion.getDy())) {
            throw new OutOfBoundsException();
        }

        synchronized (lock) {
            moveTo(getMinX() + motion.getDx(), getMinY() + motion.getDy());
        }
    }

    @Override
    public void moveTo(int x, int y) {
        bounds.setLocation(x, y);
        // logger.info("({}, {}) 이동, 현재 좌표 : ({}, {})", motion.getDx(), motion.getDy(),
    }

    @Override
    public void run() {
        isMove = true;

        while (isMove) {
            long oldTime = System.currentTimeMillis();

            synchronized (lock) {
                move();
            }

            long currentTime = System.currentTimeMillis();
            try {
                long tempDt = currentTime - oldTime;
                if (getDt() > tempDt) {
                    Thread.sleep(getDt() - tempDt);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    @Override
    public void start() {
        if (Objects.isNull(thread) || !thread.isAlive()) {
            thread = new Thread(this);
        }

        thread.start();
    }

    @Override
    public void stop() {
        isMove = false;
    }

    public void applyEffect(Effect effect) {
        synchronized (lock) {
            effect.apply(motion);
        }
    }
}
