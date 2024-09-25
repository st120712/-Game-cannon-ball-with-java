package com.nhnacademy.game.world;

import java.awt.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Bounded;
import com.nhnacademy.game.obj.Movable;


public class BoundedWorld extends MovableWorld {

    private static final Logger logger = LoggerFactory.getLogger(BoundedWorld.class);

    public BoundedWorld() {
        super(0);
    }

    private int[] calculateOverlap(Boundable boundable) {
        int overX = calculateOverlapX(boundable);
        int overY = calculateOverlapY(boundable);
        return new int[] {overX, overY};
    }

    private int calculateOverlapX(Boundable boundable) {
        int overX = 0;
        int dx = ((Movable) boundable).getMotion().getDx();

        if (dx < 0 && boundable.getMinX() + dx < 0) {
            overX = boundable.getMinX() + dx;
        } else if (dx > 0 && boundable.getMaxX() + dx > getWidth()) {
            overX = boundable.getMaxX() + dx - getWidth();
        }

        updateMotion((Movable) boundable, dx, true);
        return overX;
    }

    private int calculateOverlapY(Boundable boundable) {
        int overY = 0;
        int dy = ((Movable) boundable).getMotion().getDy();

        if (dy < 0 && boundable.getMinY() + dy < 0) {
            overY = boundable.getMinY() + dy;
        } else if (dy > 0 && boundable.getMaxY() + dy > getWidth()) {
            overY = boundable.getMaxY() + dy - getWidth();
        }

        updateMotion((Movable) boundable, dy, false);
        return overY;
    }

    private void updateMotion(Movable movable, int originalSpeed, boolean isHorizontal) {
        int newSpeed = (int) (originalSpeed * 0.7);

        if (isHorizontal) {
            movable.getMotion().setDx(newSpeed);
            movable.getMotion().turnDx();
        } else {
            movable.getMotion().setDy(newSpeed);
            movable.getMotion().turnDy();
        }
    }

    @Override
    public void move() {
        super.move();

        for (Boundable boundable : boundableList) {
            if (boundable instanceof Bounded) {
                ((Bounded) boundable).setBoundedArea(getBounds());
                ((Movable) boundable).move();
            } else if (boundable instanceof Movable) {
                handleCollisions(boundable);
                updatePosition(boundable);
            }
        }
    }

    private void handleCollisions(Boundable boundable) {
        for (Boundable other : boundableList) {
            if (boundable.equals(other)) {
                continue;
            }

            if (boundable.intersects(other)) {
                Rectangle intersection = boundable.intersection(other);
                resolveCollision(boundable, other, intersection);
            }
        }
    }

    private void resolveCollision(Boundable boundable, Boundable other, Rectangle intersection) {
        if (boundable.getHeight() == intersection.getHeight()
                || other.getHeight() == intersection.getHeight()) {

            ((Movable) boundable).getMotion().turnDx();

        } else if (boundable.getWidth() == intersection.getWidth()
                || other.getWidth() == intersection.getWidth()) {

            ((Movable) boundable).getMotion().turnDy();

        } else {
            ((Movable) boundable).getMotion().turnDx();
            ((Movable) boundable).getMotion().turnDy();
        }
    }

    private void updatePosition(Boundable boundable) {
        int[] distance = calculateOverlap(boundable);
        int x = boundable.getMinX() + ((Movable) boundable).getMotion().getDx() - distance[0];
        int y = boundable.getMinY() + ((Movable) boundable).getMotion().getDy() - distance[1];
        ((Movable) boundable).moveTo(x, y);
        ((Movable) boundable).move();
    }
}
