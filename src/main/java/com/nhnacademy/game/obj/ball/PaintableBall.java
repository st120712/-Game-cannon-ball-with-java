package com.nhnacademy.game.obj.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;
import java.util.UUID;
import com.nhnacademy.game.obj.Paintable;
import java.awt.Rectangle;

public class PaintableBall extends Ball implements Paintable {
    static final Color DEFAULT_COLOR = Color.black;
    protected Color color;


    public PaintableBall(Rectangle bounds, Color color) {
        super(bounds);

        setColor(color);
    }

    public PaintableBall(UUID id, Rectangle bounds, Color color) {
        super(id, bounds);

        setColor(color);
    }

    public PaintableBall(String id, Rectangle bounds, Color color) {
        super(id, bounds);

        setColor(color);
    }

    public PaintableBall(Rectangle bounds) {
        this(bounds, DEFAULT_COLOR);
    }

    public PaintableBall(UUID id, Rectangle bounds) {
        this(id, bounds, DEFAULT_COLOR);
    }

    public PaintableBall(String id, Rectangle bounds) {
        this(id, bounds, DEFAULT_COLOR);
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
        g.fillOval(getMinX(), getMinY(), getWidth(), getHeight());


        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.MAGENTA);
        g2d.draw(bounds);
    }

    @Override
    public String toString() {
        return String.format("[%s, (%d,%d), %d, %s]", getId(), getCenterX(), getCenterY(),
                getRadius(), getColor().toString());
    }

}
