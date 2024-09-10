package com.nhnacademy.game.world;

import java.awt.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Bounded;
import com.nhnacademy.game.obj.Movable;
import com.nhnacademy.game.obj.ball.BoundedBall;


public class BoundedWorld extends MovableWorld implements Bounded {

    private static final Logger logger = LoggerFactory.getLogger(BoundedWorld.class);

    public BoundedWorld() {
        super(0);
    }

    private int[] amountOver(Boundable boundable) {
        int overX = 0;
        int overY = 0;

        if (((Movable) boundable).getMotion().getDx() < 0) {
            if (boundable.getMinX() + ((Movable) boundable).getMotion().getDx() < getX()) {
                overX = getX() - (boundable.getMinX() + ((Movable) boundable).getMotion().getDx());
                ((Movable) boundable).getMotion().turnDx();
            }
        } else {
            if (boundable.getMaxX() + ((Movable) boundable).getMotion().getDx() > getX()
                    + getWidth()) {
                overX = (boundable.getMaxX() + ((Movable) boundable).getMotion().getDx())
                        - (getX() + getWidth());
                ((Movable) boundable).getMotion().turnDx();
            }
        }

        if (((Movable) boundable).getMotion().getDy() < 0) {
            if (boundable.getMinY() + ((Movable) boundable).getMotion().getDy() < getY()) {
                overY = getY() - (boundable.getMinY() + ((Movable) boundable).getMotion().getDy());
                ((Movable) boundable).getMotion().turnDy();
            }
        } else {
            if (boundable.getMaxY() + ((Movable) boundable).getMotion().getDy() > getY()
                    + getHeight()) {
                overY = (boundable.getMaxY() + ((Movable) boundable).getMotion().getDy())
                        - (getY() + getHeight());
                ((Movable) boundable).getMotion().turnDy();
            }
        }

        int[] arr = {overX, overY};

        return arr;
    }

    @Override
    public void move() {
        repaint();

        for (Boundable boundable : boundableList) {
            if (boundable instanceof Movable) {

                for (Boundable otherBoundable : boundableList) {
                    if (boundable.equals(otherBoundable)) {
                        continue;
                    }

                    if (boundable.intersects(otherBoundable)) {
                        Rectangle inter = boundable.intersection(otherBoundable);

                        if (boundable.getHeight() == inter.getHeight()
                                || otherBoundable.getHeight() == inter.getHeight()) {

                            ((Movable) boundable).getMotion().turnDx();

                        } else if (boundable.getWidth() == inter.getWidth()
                                || otherBoundable.getWidth() == inter.getWidth()) {

                            ((Movable) boundable).getMotion().turnDy();

                        } else {
                            ((Movable) boundable).getMotion().turnDx();
                            ((Movable) boundable).getMotion().turnDy();
                        }

                    }
                }

                int[] distance = amountOver((Boundable) boundable);

                int x = ((Boundable) boundable).getMinX()
                        + ((Movable) boundable).getMotion().getDx() - distance[0];
                int y = ((Boundable) boundable).getMinY()
                        + ((Movable) boundable).getMotion().getDy() - distance[1];

                ((Movable) boundable).moveTo(x, y);
            }

            if (boundable instanceof Bounded) {
                ((Bounded) boundable).setBounds(getBounds());
                ((Movable) boundable).move();
            }
        }
    }
}
