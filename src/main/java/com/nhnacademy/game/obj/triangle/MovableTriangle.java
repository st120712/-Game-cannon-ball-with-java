package com.nhnacademy.game.obj.triangle;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.UUID;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Movable;
import com.nhnacademy.game.vector.PositionalVector;

public class MovableTriangle extends PaintableTriangle implements Movable {

    protected PositionalVector motion = new PositionalVector(10, 10);

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

}
