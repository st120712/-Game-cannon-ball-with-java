package com.nhnacademy.game.obj;

import java.awt.Point;
import java.awt.Rectangle;

public interface Boundable {
    public Point getLocation();

    public int getWidth();

    public int getHeight();

    public int getMinX();

    public int getMaxX();

    public int getMinY();

    public int getMaxY();

    public Rectangle getBounds();

    public boolean intersects(Boundable other);

    public Rectangle intersection(Boundable other);
}
