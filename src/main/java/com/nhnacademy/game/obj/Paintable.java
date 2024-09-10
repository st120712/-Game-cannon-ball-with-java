package com.nhnacademy.game.obj;

import java.awt.Color;
import java.awt.Graphics;

public interface Paintable {
    public Color getColor();

    public void setColor(Color color);

    void paint(Graphics g);
}
