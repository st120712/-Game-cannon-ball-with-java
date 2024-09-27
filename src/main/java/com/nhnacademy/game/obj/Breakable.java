package com.nhnacademy.game.obj;

import java.util.List;

public interface Breakable extends Runnable {
    public void breaked();

    public void setBoundableList(List<Boundable> boundableList);

    public void start();

    public void stop();
}
