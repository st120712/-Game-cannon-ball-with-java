package com.nhnacademy.game.obj.triangle;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.UUID;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Movable;
import com.nhnacademy.game.vector.PositionalVector;

public class MovableTriangle extends PaintableTriangle implements Movable {

    protected PositionalVector motion = new PositionalVector(10, 10);
    protected int dt = 100;
    protected boolean isMove = false;

    public MovableTriangle(Rectangle bounds) {
        super(bounds);
    }

    public MovableTriangle(Rectangle bounds, Color color) {
        super(bounds, color);
    }

    public MovableTriangle(UUID id, Rectangle bounds, Color color) {
        super(id, bounds, color);
    }

    public MovableTriangle(String id, Rectangle bounds, Color color) {
        super(id, bounds, color);
    }

    public MovableTriangle(UUID id, Rectangle bounds) {
        super(id, bounds);
    }

    public MovableTriangle(String id, Rectangle bounds) {
        super(id, bounds);
    }

    @Override
    public PositionalVector getMotion() {
        return motion;
    }

    @Override
    public void setMotion(PositionalVector motion) {
        if (Math.abs(motion.getDx()) < 5 || Math.abs(motion.getDx()) > 30
                || Math.abs(motion.getDy()) < 5 || Math.abs(motion.getDy()) > 30) {
            throw new IllegalArgumentException();
        }

        this.motion = motion;
    }

    @Override
    public void move() {
        if (Math.abs(getMinX()) > Integer.MAX_VALUE - Math.abs(motion.getDx())
                || Math.abs(getMinY()) > Integer.MAX_VALUE - Math.abs(motion.getDy())) {
            throw new OutOfBoundsException();
        }

        moveTo(getMinX() + motion.getDx(), getMinY() + motion.getDy());
    }

    public void moveTo(int x, int y) {
        bounds.setLocation(x, y);
    }

    @Override
    public void run() {
        while (isMove) {
            try {
                move();
                Thread.sleep(dt);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public int getDt() {
        return dt;
    }

    @Override
    public void setDt(int dt) {
        this.dt = dt;
    }

    @Override
    public void start() {
        isMove = true;
    }

    @Override
    public void stop() {
        isMove = false;
    }

}
