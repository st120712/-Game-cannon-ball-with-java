package com.nhnacademy.game.obj.ball;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Bounded;

public class BoundedBall extends MovableBall implements Bounded {
    private Rectangle boundedArea;

    private static final Logger logger = LoggerFactory.getLogger(BoundedBall.class);

    public BoundedBall(Rectangle bounds) {
        super(bounds);
        setInitBoundedArea(boundedArea);
    }

    public BoundedBall(Rectangle bounds, Color color) {
        super(bounds, color);
        setInitBoundedArea(boundedArea);
    }

    public BoundedBall(UUID id, Rectangle bounds, Color color) {
        super(id, bounds, color);
        setInitBoundedArea(boundedArea);
    }

    public BoundedBall(String id, Rectangle bounds, Color color) {
        super(id, bounds, color);
        setInitBoundedArea(boundedArea);
    }

    public BoundedBall(UUID id, Rectangle bounds) {
        super(id, bounds);
        setInitBoundedArea(boundedArea);
    }

    public BoundedBall(String id, Rectangle bounds) {
        super(id, bounds);
        setInitBoundedArea(boundedArea);
    }

    private void setInitBoundedArea(Rectangle boundedArea) {
        setBoundedArea(new Rectangle((int) boundedArea.getMinX(), (int) boundedArea.getMinY(),
                (int) boundedArea.getWidth(), (int) boundedArea.getHeight()));
    }

    public Rectangle getBoundedArea() {
        return boundedArea;
    }

    public void setBoundedArea(Rectangle boundedArea) {
        if (getMinX() < boundedArea.getMinX() || getMinY() < boundedArea.getMinY()
                || getMaxX() > boundedArea.getMaxX() || getMaxY() > boundedArea.getMaxY()) {
            logger.info(
                    "Ball = minX : {}, minY : {}, maxX : {}, maxY : {} || Boundary = minX : {}, minY : {}, maxX : {}, maxY : {}",
                    getMinX(), getMinY(), getMaxX(), getMaxY(), boundedArea.getMinX(),
                    boundedArea.getMinY(), boundedArea.getMaxX(), boundedArea.getMaxY());

            throw new IllegalArgumentException();
        }

        this.boundedArea = boundedArea;
    }

    private int[] amountOver() {
        int overX = 0;
        int overY = 0;

        if (motion.getDx() < 0) {
            if (getMinX() + motion.getDx() < bounds.getMinX()) {
                overX = (int) bounds.getMinX() - (getMinX() + motion.getDx());
                motion.turnDx();
            }
        } else {
            if (getMaxX() + motion.getDx() > bounds.getMaxX()) {
                overX = (getMaxX() + motion.getDx()) - (int) bounds.getMaxX();
                motion.turnDx();
            }
        }

        if (motion.getDy() < 0) {
            if (getMinY() + motion.getDy() < bounds.getMinY()) {
                overY = (int) bounds.getMinY() - (getMinY() + motion.getDy());
                motion.turnDy();
            }
        } else {
            if (getMaxY() + motion.getDy() > bounds.getMaxY()) {
                overY = (getMaxY() + motion.getDy()) - (int) bounds.getMaxY();
                motion.turnDy();
            }
        }

        int[] arr = {overX, overY};

        return arr;
    }

    @Override
    public void move() {
        int[] distance = {0, 0};

        if (Math.abs(getMinX()) > Integer.MAX_VALUE - Math.abs(motion.getDx())
                || Math.abs(getMinY()) > Integer.MAX_VALUE - Math.abs(motion.getDy())) {
            throw new OutOfBoundsException();
        }

        distance = amountOver();

        int x = getCenterX() + motion.getDx() - distance[0];
        int y = getCenterY() + motion.getDy() - distance[1];

        moveTo(x, y);
    }
}
