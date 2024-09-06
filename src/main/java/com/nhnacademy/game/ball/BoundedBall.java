package com.nhnacademy.game.ball;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.exception.OutOfBoundsException;

public class BoundedBall extends MovableBall {
    private Rectangle boundedArea;
    private static final Logger logger = LoggerFactory.getLogger(BoundedBall.class);

    public BoundedBall(int x, int y, int radius) {
        super(x, y, radius);
        setInitBoundedArea(x, y, radius);
    }

    public BoundedBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
        setInitBoundedArea(x, y, radius);
    }

    public BoundedBall(UUID id, int x, int y, int radius, Color color) {
        super(id, x, y, radius, color);
        setInitBoundedArea(x, y, radius);
    }

    public BoundedBall(String id, int x, int y, int radius, Color color) {
        super(id, x, y, radius, color);
        setInitBoundedArea(x, y, radius);
    }

    public BoundedBall(UUID id, int x, int y, int radius) {
        super(id, x, y, radius);
        setInitBoundedArea(x, y, radius);
    }

    public BoundedBall(String id, int x, int y, int radius) {
        super(id, x, y, radius);
        setInitBoundedArea(x, y, radius);
    }

    private void setInitBoundedArea(int x, int y, int radius) {
        setBoundedArea(new Rectangle(x, y, 2 * radius, 2 * radius));
    }

    public Rectangle getBoundedArea() {
        return boundedArea;
    }

    public void setBoundedArea(Rectangle boundedArea) {
        if (getMinX() < boundedArea.getMinX() || getMinY() < boundedArea.getMinY()
                || getMaxX() > boundedArea.getMaxX() || getMaxY() > boundedArea.getMaxY()) {
            logger.info(
                    "Ball = minX : {}, minY : {}, maxX : {}, maxY : {} || Boundary = minX : {}, minY : {}, maxX : {}, maxY : {}",
                    getMinX(), getMinY(), getMaxX(), getMaxY(), boundedArea.getMinX(),
                    boundedArea.getMinY(), boundedArea.getMaxX(), boundedArea.getMaxY());

            throw new IllegalArgumentException();
        }

        this.boundedArea = boundedArea;
    }

    private int[] amountOver() {
        int overX = 0;
        int overY = 0;

        if (dx < 0) {
            if (getMinX() + getDX() < boundedArea.getMinX()) {
                overX = (int) boundedArea.getMinX() - (getMinX() + getDX());
                setDX(-dx);
            }
        } else {
            if (getMaxX() + getDX() > boundedArea.getMaxX()) {
                overX = (getMaxX() + getDX()) - (int) boundedArea.getMaxX();
                setDX(-dx);
            }
        }

        if (dy < 0) {
            if (getMinY() + getDY() < boundedArea.getMinY()) {
                overY = (int) boundedArea.getMinY() - (getMinY() + getDY());
                setDY(-dy);
            }
        } else {
            if (getMaxY() + getDY() > boundedArea.getMaxY()) {
                overY = (getMaxY() + getDY()) - (int) boundedArea.getMaxY();
                setDY(-dy);
            }
        }

        int[] arr = {overX, overY};

        return arr;
    }

    @Override
    public void move() {
        int[] distance = {0, 0};

        if (Math.abs(x) > Integer.MAX_VALUE - Math.abs(dx)
                || Math.abs(y) > Integer.MAX_VALUE - Math.abs(y)) {
            throw new OutOfBoundsException();
        }

        distance = amountOver();

        moveTo(this.x + dx - distance[0], this.y + dy - distance[1]);
    }
}
