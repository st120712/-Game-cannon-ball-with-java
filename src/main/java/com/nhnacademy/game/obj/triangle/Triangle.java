package com.nhnacademy.game.obj.triangle;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.UUID;
import com.nhnacademy.game.obj.Boundable;

public class Triangle implements Boundable {
    UUID id;
    Rectangle bounds;
    String name;

    public Triangle(Rectangle bounds) {
        this(UUID.randomUUID(), bounds);
    }

    public Triangle(UUID id, Rectangle bounds) {
        this.id = id;
        this.bounds = bounds;
        name = id.toString();
    }

    public Triangle(String uuid, Rectangle bounds) {
        this(UUID.fromString(uuid), bounds);
    }

    @Override
    public Point getLocation() {
        return bounds.getLocation();
    }

    @Override
    public int getWidth() {
        return (int) bounds.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) bounds.getHeight();
    }

    @Override
    public int getMinX() {
        return (int) bounds.getMinX();
    }

    @Override
    public int getMaxX() {
        return (int) bounds.getMaxX();
    }

    @Override
    public int getMinY() {
        return (int) bounds.getMinY();
    }

    @Override
    public int getMaxY() {
        return (int) bounds.getMaxY();
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
