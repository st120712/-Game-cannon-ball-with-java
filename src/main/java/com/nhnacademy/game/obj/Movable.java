package com.nhnacademy.game.obj;

import com.nhnacademy.game.vector.PositionalVector;

public interface Movable {
    public PositionalVector getMotion();

    public void setMotion(PositionalVector motion);

    public void move();

    public void moveTo(int x, int y);
}
