package com.nhnacademy.game.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.exception.AlreadyExistException;
import com.nhnacademy.game.exception.DuplicatedBoundsException;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.Boundable;
import com.nhnacademy.game.obj.Breakable;
import com.nhnacademy.game.obj.Paintable;
import com.nhnacademy.game.obj.ball.BoundedBall;



public class World extends JPanel {
    public enum Axis {
        AXIS_X, AXIS_Y
    }

    protected List<Boundable> boundableList = Collections.synchronizedList(new ArrayList<>());

    private static Logger logger = LoggerFactory.getLogger(World.class);

    public void add(Boundable boundable) {
        if (Objects.isNull(boundable)) {
            throw new NullPointerException();
        }

        if (boundableList.contains(boundable)) {
            throw new AlreadyExistException();
        }

        if (boundable.getMinX() < 0 || boundable.getMaxX() > getBounds().getWidth()
                || boundable.getMinY() < 0 || boundable.getMaxY() > getBounds().getHeight()) {
            throw new OutOfBoundsException();
        }

        for (Boundable created : boundableList) {
            if (boundable.intersects(created)) {
                throw new DuplicatedBoundsException();
            }
        }

        if (boundable instanceof BoundedBall) {
            ((BoundedBall) boundable).setBoundableList(boundableList);
            ((BoundedBall) boundable).setBoundedArea(new Rectangle(0, 0,
                    (int) getBounds().getWidth(), (int) getBounds().getHeight()));
        }

        if (boundable instanceof Breakable) {
            ((Breakable) boundable).setBoundableList(boundableList);
        }

        boundableList.add(boundable);
        logger.info("boundable 추가 : {}", boundable);
    }

    public void remove(Boundable boundable) {
        if (Objects.isNull(boundable)) {
            throw new NullPointerException();
        }

        if (!boundableList.contains(boundable)) {
            throw new NoSuchElementException();
        }

        boundableList.remove(boundable);
        logger.info("boundable 삭제 : {}", boundable);
    }

    public void remove(int index) {
        if (index > boundableList.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        Boundable temp = boundableList.get(index);

        boundableList.remove(temp);
        logger.info("boundable 삭제 : {}", temp);
    }

    public int getBoundableCount() {
        return boundableList.size();
    }

    Boundable getBoundable(int index) {
        if (index > boundableList.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        return boundableList.get(index);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Boundable boundable : boundableList) {
            if (boundable instanceof Paintable) {
                ((Paintable) boundable).paint(g);
            }
        }
    }
}
