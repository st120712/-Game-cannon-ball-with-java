package com.nhnacademy.game.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;
import java.util.UUID;

public class PaintableBall extends Ball {
    static final Color DEFAULT_COLOR = Color.black;
    protected Color color;


    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);

        setColor(color);
    }

    public PaintableBall(UUID id, int x, int y, int radius, Color color) {
        super(id, x, y, radius);

        setColor(color);
    }

    public PaintableBall(String id, int x, int y, int radius, Color color) {
        super(id, x, y, radius);

        setColor(color);
    }

    public PaintableBall(int x, int y, int radius) {
        this(x, y, radius, DEFAULT_COLOR);
    }

    public PaintableBall(UUID id, int x, int y, int radius) {
        this(id, x, y, radius, DEFAULT_COLOR);
    }

    public PaintableBall(String id, int x, int y, int radius) {
        this(id, x, y, radius, DEFAULT_COLOR);
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void paint(Graphics g) {

        if (Objects.isNull(g)) {
            throw new NullPointerException();
        }

        g.setColor(color);
        g.fillOval(getMinX(), getMinY(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return String.format("[%s, (%d,%d), %d, %s]", getId(), getX(), getY(), getRadius(),
                getColor().toString());
    }
}
