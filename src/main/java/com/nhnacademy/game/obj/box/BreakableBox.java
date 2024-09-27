package com.nhnacademy.game.obj.box;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Breakable;

public class BreakableBox extends PaintableBox implements Breakable {
    private List<Boundable> boundableList;
    private Thread thread;

    public BreakableBox(Rectangle bounds) {
        super(bounds, DEFAULT_COLOR);
    }

    public BreakableBox(UUID id, Rectangle bounds) {
        super(id, bounds, DEFAULT_COLOR);
    }

    public BreakableBox(String uuid, Rectangle bounds) {
        super(uuid, bounds, DEFAULT_COLOR);
    }

    public BreakableBox(Rectangle bounds, Color color) {
        super(bounds, color);
    }

    public BreakableBox(UUID id, Rectangle bounds, Color color) {
        super(id, bounds, color);
    }

    public BreakableBox(String uuid, Rectangle bounds, Color color) {
        super(uuid, bounds, color);
    }

    @Override
    public void breaked() {
        boundableList.remove(this);
    }

    @Override
    public void setBoundableList(List<Boundable> boundableList) {
        this.boundableList = boundableList;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (boundableList) {

                Iterator<Boundable> iterator = boundableList.iterator();

                while (iterator.hasNext()) {
                    Boundable boundable = iterator.next();

                    if (equals(boundable)) {
                        continue;
                    }

                    if (intersects(boundable)) {
                        stop();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void start() {
        if (Objects.isNull(thread) || !thread.isAlive()) {
            thread = new Thread(this);
        }

        thread.start();
    }

    @Override
    public void stop() {
        breaked();
    }

}
