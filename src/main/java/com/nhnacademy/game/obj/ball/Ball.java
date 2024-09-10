package com.nhnacademy.game.obj.ball;

import java.util.UUID;
import com.nhnacademy.game.exception.InvalidSizeException;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Boundable;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball implements Boundable {
    protected Rectangle bounds;
    protected final UUID id;
    protected String name;

    public Ball(UUID id, Rectangle bounds) {

        if (bounds.getWidth() / 2 < 0) {
            throw new InvalidSizeException("공의 반지름이 0보다 커야합니다.");
        }

        if (bounds.getX() > Integer.MAX_VALUE - (bounds.getWidth() / 2)
                || bounds.getY() > Integer.MAX_VALUE - (bounds.getWidth() / 2)
                || bounds.getX() < Integer.MIN_VALUE + (bounds.getWidth() / 2)
                || bounds.getY() < Integer.MIN_VALUE + (bounds.getWidth() / 2)) {
            throw new OutOfBoundsException("공의 크기가 너무 큽니다.");
        }

        this.id = id;
        this.name = id.toString();
        this.bounds = bounds;
    }

    public Ball(Rectangle bounds) {
        this(UUID.randomUUID(), bounds);
    }

    public Ball(String id, Rectangle bounds) {
        this(UUID.fromString(id), bounds);
    }

    public int getCenterX() {
        return (int) bounds.getCenterX();
    }

    public int getCenterY() {
        return (int) bounds.getCenterY();
    }

    public int getRadius() {
        return (int) (bounds.getWidth() / 2);
    }

    public int getMinX() {
        return (int) bounds.getMinX();
    }

    public int getMinY() {
        return (int) bounds.getMinY();
    }

    public int getMaxX() {
        return (int) bounds.getMaxX();
    }

    public int getMaxY() {
        return (int) bounds.getMaxY();
    }

    public int getWidth() {
        return (int) bounds.getWidth();
    }

    public int getHeight() {
        return (int) bounds.getHeight();
    }

    public Point getLocation() {
        return bounds.getLocation();
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
        return String.format("[%s, (%d,%d), %d]", getId(), getCenterX(), getCenterY(), getRadius());
    }

    @Override
    public boolean intersects(Boundable other) {
        return bounds.intersects(new Rectangle(other.getMinX(), other.getMinY(), other.getWidth(),
                other.getHeight()));
    }

    @Override
    public Rectangle intersection(Boundable other) {
        return bounds.intersection(new Rectangle(other.getMinX(), other.getMinY(), other.getWidth(),
                other.getHeight()));
    }
}
