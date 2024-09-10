package com.nhnacademy.game.obj.box;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.UUID;
import com.nhnacademy.game.obj.Boundable;

public class Box implements Boundable {
    UUID id;
    Rectangle bounds;
    String name;

    public Box(Rectangle bounds) {
        this(UUID.randomUUID(), bounds);
    }

    public Box(UUID id, Rectangle bounds) {
        this.id = id;
        this.bounds = bounds;
        name = id.toString();
    }

    public Box(String uuid, Rectangle bounds) {
        this(UUID.fromString(uuid), bounds);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Point getLocation() {
        return bounds.getLocation();
    }

    public int getMinX() {
        return (int) bounds.getMinX();
    }

    public int getMaxX() {
        return (int) bounds.getMaxX();
    }

    public int getMinY() {
        return (int) bounds.getMinY();
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

    @Override
    public String toString() {
        return String.format("[%s, (%d, %d), %d, %d]", id.toString(), getMinX(), getMinY(),
                getWidth(), getHeight());
    }

    @Override
    public boolean intersects(Boundable other) {
        return getBounds().intersects(new Rectangle(other.getMinX(), other.getMinY(),
                other.getWidth(), other.getHeight()));
    }

    @Override
    public Rectangle intersection(Boundable other) {
        return getBounds().intersection(new Rectangle(other.getMinX(), other.getMinY(),
                other.getWidth(), other.getHeight()));
    }
}
