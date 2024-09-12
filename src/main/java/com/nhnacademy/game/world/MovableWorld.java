package com.nhnacademy.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.effect.Effect;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Movable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class MovableWorld extends World implements Runnable {
    protected int moveCount = 0;
    protected int maxMoveCount;
    protected int dt = 50;
    protected Random rand = new Random();
    protected List<Effect> effects = new ArrayList<>();
    protected Thread thread;
    protected boolean isRun = false;

    private static final Logger logger = LoggerFactory.getLogger(MovableWorld.class);

    public MovableWorld() {
        this(0);
    }

    public MovableWorld(int maxMoveCount) {
        setMaxMoveCount(maxMoveCount);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int maxMoveCount) {
        if (maxMoveCount < 0) {
            throw new IllegalArgumentException();
        }

        this.maxMoveCount = maxMoveCount;
    }

    public int getDT() {
        return dt;
    }

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }

        this.dt = dt;
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void reset() {
        moveCount = 0;
    }

    protected void move() {
        repaint();

        for (Boundable boundable : boundableList) {
            if (boundable instanceof Movable) {
                for (Effect effect : effects) {
                    if (((Movable) boundable).getMotion().getDx() > 30) {
                        ((Movable) boundable).getMotion().setDx(rand.nextInt(5) + 5);
                    }

                    if (((Movable) boundable).getMotion().getDy() > 30) {
                        ((Movable) boundable).getMotion().setDy(rand.nextInt(5) + 5);
                    }

                    if (effect.getDx() == 0 && effect.getDy() == 0) {
                        ((Movable) boundable).getMotion()
                                .add(new Effect(rand.nextInt(6) - 3, rand.nextInt(6) - 3));
                    }

                    ((Movable) boundable).getMotion().add(effect);
                }
            }
        }

        moveCount++;
    }

    @Override
    public void run() {
        isRun = true;

        long startTime = System.currentTimeMillis();

        logger.info("start");

        while (isRun) {
            long oldTime = System.currentTimeMillis();

            move();

            long currentTime = System.currentTimeMillis();
            try {
                long tempDt = currentTime - oldTime;
                if (getDT() > tempDt) {
                    Thread.sleep(getDT() - tempDt);
                }
            } catch (InterruptedException e) {
            }
        }

        logger.info("finished : {}", System.currentTimeMillis() - startTime);
    }

    public void start() {
        if (Objects.isNull(thread) || !thread.isAlive()) {
            thread = new Thread(this);
        }

        for (Boundable boundable : boundableList) {
            if (boundable instanceof Movable) {
                ((Movable) boundable).start();
            }
        }

        thread.start();
    }

    public void stop() {
        isRun = false;

        for (Boundable boundable : boundableList) {
            if (boundable instanceof Movable) {
                ((Movable) boundable).stop();
            }
        }
    }
}
