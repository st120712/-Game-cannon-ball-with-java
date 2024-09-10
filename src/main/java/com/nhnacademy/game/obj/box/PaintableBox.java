package com.nhnacademy.game.obj.box;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.UUID;
import com.nhnacademy.game.obj.Paintable;

public class PaintableBox extends Box implements Paintable {
    static final Color DEFAULT_COLOR = Color.white;
    Color color;

    public PaintableBox(Rectangle bounds) {
        this(bounds, DEFAULT_COLOR);
    }

    public PaintableBox(UUID id, Rectangle bounds) {
        this(id, bounds, DEFAULT_COLOR);
    }

    public PaintableBox(String uuid, Rectangle bounds) {
        this(uuid, bounds, DEFAULT_COLOR);
    }

    public PaintableBox(Rectangle bounds, Color color) {
        super(bounds);
        this.color = color;
    }

    public PaintableBox(UUID id, Rectangle bounds, Color color) {
        super(id, bounds);
        this.color = color;
    }

    public PaintableBox(String uuid, Rectangle bounds, Color color) {
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
        g.fillRect(getMinX(), getMinY(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return String.format("[%s, (%d, %d), %d, %d, %s]", id.toString(), getMinX(), getMinY(),
                getWidth(), getHeight(), getColor().toString());
    }
}
