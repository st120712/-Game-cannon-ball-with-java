package com.nhnacademy.game;

import com.nhnacademy.game.exception.InvalidSizeException;
import com.nhnacademy.game.exception.OutOfBoundsException;

public class Ball {
    protected int x;
    protected int y;
    protected final int radius;

    Ball(int x, int y, int radius) {

        if (x + radius > Integer.MAX_VALUE || y + radius > Integer.MAX_VALUE
                || x - radius < Integer.MIN_VALUE || y - radius < Integer.MIN_VALUE) {
            throw new OutOfBoundsException("공의 크기가 너무 큽니다.");
        }

        if (radius < 0) {
            throw new InvalidSizeException("공의 반지름이 0보다 커야합니다.");
        }

        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getMinX() {
        return x - radius;
    }

    public int getMinY() {
        return y - radius;
    }

    public int getMaxX() {
        return x + radius;
    }

    public int getMaxY() {
        return y + radius;
    }

    public int getWidth() {
        return 2 * radius;
    }

    public int getHeight() {
        return 2 * radius;
    }

    @Override
    public String toString() {
        return "[(" + getX() + "," + getY() + ")" + ", " + getRadius() + "]";
    }
}
