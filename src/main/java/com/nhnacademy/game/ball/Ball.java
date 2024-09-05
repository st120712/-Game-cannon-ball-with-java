package com.nhnacademy.game.ball;

import java.util.UUID;
import com.nhnacademy.game.exception.InvalidSizeException;
import com.nhnacademy.game.exception.OutOfBoundsException;

public class Ball {
    protected int x;
    protected int y;
    protected final int radius;
    protected final UUID id;
    protected String name;

    public Ball(UUID id, int x, int y, int radius) {

        if (radius < 0) {
            throw new InvalidSizeException("공의 반지름이 0보다 커야합니다.");
        }

        if (x > Integer.MAX_VALUE - radius || y > Integer.MAX_VALUE - radius
                || x < Integer.MIN_VALUE + radius || y < Integer.MIN_VALUE + radius) {
            throw new OutOfBoundsException("공의 크기가 너무 큽니다.");
        }

        this.id = id;
        this.name = id.toString();
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Ball(int x, int y, int radius) {
        this(UUID.randomUUID(), x, y, radius);
    }

    public Ball(String id, int x, int y, int radius) {
        this(UUID.fromString(id), x, y, radius);
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

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[%s, (%d,%d), %d]", getId(), getX(), getY(), getRadius());
    }
}
