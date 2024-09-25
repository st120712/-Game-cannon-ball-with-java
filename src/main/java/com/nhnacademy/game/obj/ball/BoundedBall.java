package com.nhnacademy.game.obj.ball;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.UUID;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Bounded;

public class BoundedBall extends MovableBall implements Bounded {
    private Rectangle boundedArea;
    private List<Boundable> boundableList;

    private static final Logger logger = LoggerFactory.getLogger(BoundedBall.class);

    public BoundedBall(Rectangle bounds) {
        super(bounds);
        setInitBoundedArea(bounds);
    }

    public BoundedBall(Rectangle bounds, Color color) {
        super(bounds, color);
        setInitBoundedArea(bounds);
    }

    public BoundedBall(UUID id, Rectangle bounds, Color color) {
        super(id, bounds, color);
        setInitBoundedArea(bounds);
    }

    public BoundedBall(String id, Rectangle bounds, Color color) {
        super(id, bounds, color);
        setInitBoundedArea(bounds);
    }

    public BoundedBall(UUID id, Rectangle bounds) {
        super(id, bounds);
        setInitBoundedArea(bounds);
    }

    public BoundedBall(String id, Rectangle bounds) {
        super(id, bounds);
        setInitBoundedArea(bounds);
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

    public void setBoundableList(List<Boundable> boundableList) {
        this.boundableList = boundableList;
    }

    private int[] calculateOverlap() {
        int overX = calculateOverlapX();
        int overY = calculateOverlapY();
        return new int[] {overX, overY};
    }

    private int calculateOverlapX() {
        int overX = 0;
        int dx = getMotion().getDx();

        if (dx < 0 && getMinX() + dx < 0) {
            overX = getMinX() + dx;
            updateMotion(dx, true);

        } else if (dx > 0 && getMaxX() + dx > boundedArea.getWidth()) {
            overX = (int) (getMaxX() + dx - boundedArea.getWidth());
            updateMotion(dx, true);

        }

        return overX;
    }

    private int calculateOverlapY() {
        int overY = 0;
        int dy = getMotion().getDy();

        if (dy < 0 && getMinY() + dy < 0) {
            overY = getMinY() + dy;
            updateMotion(dy, false);

        } else if (dy > 0 && getMaxY() + dy > boundedArea.getHeight()) {
            overY = (int) (getMaxY() + dy - boundedArea.getHeight());
            updateMotion(dy, false);

        }

        return overY;
    }

    private void updateMotion(int originalSpeed, boolean isHorizontal) {
        int newSpeed = (int) (originalSpeed * 0.7);

        if (isHorizontal) {
            getMotion().setDx(newSpeed);
            getMotion().turnDx();
        } else {
            getMotion().setDy(newSpeed);
            getMotion().turnDy();
        }
    }



    @Override
    public void move() {
        super.move();

        synchronized (lock) {
            handleCollisions();
            updatePosition();
        }
    }

    private void handleCollisions() {
        for (Boundable other : boundableList) {
            if (equals(other)) {
                continue;
            }

            if (intersects(other)) {
                Rectangle intersection = intersection(other);
                resolveCollision(other, intersection);
            }
        }
    }

    private void resolveCollision(Boundable other, Rectangle intersection) {
        if (getHeight() == intersection.getHeight()
                || other.getHeight() == intersection.getHeight()) {

            getMotion().turnDx();

        } else if (getWidth() == intersection.getWidth()
                || other.getWidth() == intersection.getWidth()) {

            getMotion().turnDy();

        } else {
            getMotion().turnDx();
            getMotion().turnDy();
        }
    }

    private void updatePosition() {
        int[] distance = calculateOverlap();
        if (distance[0] != 0 || distance[1] != 0) {
            int dx = getMotion().getDx();
            int dy = getMotion().getDy();

            int newX = getMinX() + dx - distance[0];
            int newY = getMinY() + dy - distance[1];

            moveTo(newX, newY);
        }
    }
}
