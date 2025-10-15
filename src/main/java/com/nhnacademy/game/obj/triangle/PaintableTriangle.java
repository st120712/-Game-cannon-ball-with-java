package com.nhnacademy.game.obj.triangle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.UUID;
import com.nhnacademy.game.obj.Paintable;

public class PaintableTriangle extends Triangle implements Paintable {
    static final Color DEFAULT_COLOR = Color.white;
    Color color;

    public PaintableTriangle(Rectangle bounds) {
        this(bounds, DEFAULT_COLOR);
    }

    public PaintableTriangle(UUID id, Rectangle bounds) {
        this(id, bounds, DEFAULT_COLOR);
    }

    public PaintableTriangle(String uuid, Rectangle bounds) {
        this(uuid, bounds, DEFAULT_COLOR);
    }

    public PaintableTriangle(Rectangle bounds, Color color) {
        super(bounds);
        this.color = color;
    }

    public PaintableTriangle(UUID id, Rectangle bounds, Color color) {
        super(id, bounds);
        this.color = color;
    }

    public PaintableTriangle(String uuid, Rectangle bounds, Color color) {
        super(uuid, bounds);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        if (Objects.isNull(g)) {
            throw new NullPointerException();
        }

        g.setColor(color);
        g.fillPolygon(new int[] {getMinX(), getMinX() + (getWidth() / 2), getMaxX()},
                new int[] {getMaxY(), getMinY(), getMaxY()}, 3);
    }

}
