package com.nhnacademy.game.ball;

import java.awt.Color;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.exception.OutOfBoundsException;

public class MovableBall extends PaintableBall {
    protected int dx = 10;
    protected int dy = 10;

    private static final Logger logger = LoggerFactory.getLogger(MovableBall.class);

    public MovableBall(int x, int y, int radius) {
        super(x, y, radius);
    }

    public MovableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public MovableBall(UUID id, int x, int y, int radius, Color color) {
        super(id, x, y, radius, color);
    }

    public MovableBall(String id, int x, int y, int radius, Color color) {
        super(id, x, y, radius, color);
    }

    public MovableBall(UUID id, int x, int y, int radius) {
        super(id, x, y, radius);
    }

    public MovableBall(String id, int x, int y, int radius) {
        super(id, x, y, radius);
    }

    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }

    public void setDX(int dx) {
        if (Math.abs(dx) < 5 || Math.abs(dx) > 30) {
            throw new IllegalArgumentException();
        }

        this.dx = dx;
        logger.info("dx : {} 설정", getDX());
    }

    public void setDY(int dy) {


        if (Math.abs(dy) < 5 || Math.abs(dy) > 30) {
            throw new IllegalArgumentException();
        }

        this.dy = dy;
        logger.info("dy : {} 설정", getDY());
    }

    public void move() {
        if (x > Integer.MAX_VALUE - dx || y > Integer.MAX_VALUE - dy) {
            throw new OutOfBoundsException();
        }

        moveTo(this.x + dx, this.y + dy);
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        logger.info("({}, {}) 이동, 현재 좌표 : ({}, {})", getDX(), getDY(), getX(), getY());
    }


}
