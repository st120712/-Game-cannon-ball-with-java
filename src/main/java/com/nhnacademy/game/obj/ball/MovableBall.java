package com.nhnacademy.game.obj.ball;

import java.awt.Color;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Movable;
import com.nhnacademy.game.vector.PositionalVector;
import java.awt.Rectangle;

public class MovableBall extends PaintableBall implements Movable {
    protected PositionalVector motion = new PositionalVector(10, 10);

    private static final Logger logger = LoggerFactory.getLogger(MovableBall.class);

    public MovableBall(Rectangle bounds) {
        super(bounds);
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

    public MovableBall(UUID id, Rectangle bounds) {
        super(id, bounds);
    }

    public MovableBall(String id, Rectangle bounds) {
        super(id, bounds);
    }

    public PositionalVector getMotion() {
        return motion;
    }

    public void setMotion(PositionalVector motion) {
        if (Math.abs(motion.getDx()) < 5 || Math.abs(motion.getDx()) > 30
                || Math.abs(motion.getDy()) < 5 || Math.abs(motion.getDy()) > 30) {
            throw new IllegalArgumentException();
        }

        logger.info("ball (dx, dy): {} 설정", motion);

        this.motion = motion;
    }

    public void move() {
        if (Math.abs(getMinX()) > Integer.MAX_VALUE - Math.abs(motion.getDx())
                || Math.abs(getMinY()) > Integer.MAX_VALUE - Math.abs(motion.getDy())) {
            throw new OutOfBoundsException();
        }

        moveTo(getMinX() + motion.getDx(), getMinY() + motion.getDy());

    }

    public void moveTo(int x, int y) {
        bounds.setLocation(x, y);
        logger.info("({}, {}) 이동, 현재 좌표 : ({}, {})", motion.getDx(), motion.getDy(), getCenterX(),
                getCenterY());
    }


}
