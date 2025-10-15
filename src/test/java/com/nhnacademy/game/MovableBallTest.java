package com.nhnacademy.game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Color;
import java.awt.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.ball.MovableBall;

public class MovableBallTest {

    MovableBall ball;

    @BeforeEach
    void setUp() {
        ball = new MovableBall(new Rectangle(10, 10, 8, 8), Color.blue);

    }

    @Test
    @DisplayName("set dx, dy invalid argument exception test")
    void testSetDXDYInvalidArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ball.getMotion().setDx(0);
        });

        try {
            ball.getMotion().setDx(50);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }

        assertThrows(IllegalArgumentException.class, () -> {
            ball.getMotion().setDy(0);
        });

        try {
            ball.getMotion().setDy(50);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("int 범위를 벗어난 move 메서드 예외 테스트")
    void testMoveOutOfBoundException() {
        ball.moveTo(Integer.MAX_VALUE - 1, 30);
        assertThrows(OutOfBoundsException.class, () -> {
            ball.move();
        });

        ball.moveTo(30, Integer.MAX_VALUE - 1);
        assertThrows(OutOfBoundsException.class, () -> {
            ball.move();
        });


    }

    @Test
    @DisplayName("get dx test")
    void testGetDX() {
        assertEquals(10, ball.getMotion().getDx());

        ball.getMotion().setDx(20);

        assertEquals(20, ball.getMotion().getDx());
    }

    @Test
    @DisplayName("get dy test")
    void testGetDY() {
        assertEquals(10, ball.getMotion().getDy());

        ball.getMotion().setDy(20);

        assertEquals(20, ball.getMotion().getDy());
    }

    @Test
    void testMove() {
        ball.getMotion().setDx(15);
        ball.getMotion().setDy(10);
        ball.move();

        assertAll(() -> {
            assertEquals(29, ball.getCenterX());
            assertEquals(24, ball.getCenterY());
        });
    }

    @Test
    void testMoveTo() {
        ball.moveTo(20, 25);

        assertAll(() -> {
            assertEquals(24, ball.getCenterX());
            assertEquals(29, ball.getCenterY());
        });
    }

    @Test
    @DisplayName("test set dx")
    void testSetDX() {
        ball.getMotion().setDx(15);
        assertEquals(15, ball.getMotion().getDx());
    }

    @Test
    @DisplayName("test set dy")
    void testSetDY() {
        ball.getMotion().setDy(15);
        assertEquals(15, ball.getMotion().getDy());
    }
}
