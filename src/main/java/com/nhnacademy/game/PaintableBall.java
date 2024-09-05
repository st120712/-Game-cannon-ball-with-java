package com.nhnacademy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

public class PaintableBall extends Ball {
    static final Color DEFAULT_COLOR = Color.black;
    protected Color color;


    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);

        this.color = color;
    }

    public PaintableBall(int x, int y, int radius) {
        this(x, y, radius, DEFAULT_COLOR);
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
        return "[(" + getX() + "," + getY() + ")" + ", " + getRadius() + ", " + getColor() + "]";
    }
}
