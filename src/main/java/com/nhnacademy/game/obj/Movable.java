package com.nhnacademy.game.obj;

import com.nhnacademy.game.vector.PositionalVector;

public interface Movable extends Runnable {
    public PositionalVector getMotion();

    public void setMotion(PositionalVector motion);

    public void move();

    public void moveTo(int x, int y);

    public int getDt();

    public void setDt(int dt);

    public void start();

    public void stop();
}
