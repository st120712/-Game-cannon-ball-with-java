package com.nhnacademy.game.obj.cannon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Paintable;
import com.nhnacademy.game.vector.DisplacementVector;

public class Cannon implements Paintable, Boundable {
    static final Color DEFAULT_COLOR = Color.black;

    DisplacementVector fireVector = new DisplacementVector(10, 60);
    Color color;
    int frameHeight;
    Rectangle bounds;

    public Cannon(int frameHeight, Color color) {
        this.color = color;
        this.frameHeight = frameHeight;
        this.bounds = new Rectangle(0, frameHeight - 170, 70, 120);
    }

    public Cannon(int frameHeight) {
        this(frameHeight, DEFAULT_COLOR);
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
        g.fillRect(getMinX(), getMaxY() - 35, getWidth(), 35);
        g.fillOval(getMinX() + 6, getMaxY() - 35 - ((getWidth() - 14) / 2), getWidth() - 14,
                getWidth() - 14);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.blue);

        Rectangle rect = new Rectangle(0, 0, 10, 50);

        int degree = 35;
        g2d.rotate(Math.toRadians(degree));
        g2d.translate(rect.getHeight() * (1 - Math.sin(degree)), 0);

        g2d.draw(rect);
        g2d.fill(rect);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public Point getLocation() {
        return new Point(getMinX() + (getWidth() / 2), getMinY() + (getHeight() / 2));
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
        return bounds.intersects((Rectangle) other);
    }

    @Override
    public Rectangle intersection(Boundable other) {
        return bounds.intersection((Rectangle) other);
    }



}
