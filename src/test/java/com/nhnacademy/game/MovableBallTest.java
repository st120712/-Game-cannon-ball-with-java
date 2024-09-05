package com.nhnacademy.game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.nhnacademy.game.ball.MovableBall;
import com.nhnacademy.game.exception.OutOfBoundsException;

public class MovableBallTest {

    MovableBall ball;

    @BeforeEach
    void setUp() {
        ball = new MovableBall(10, 10, 4, Color.blue);
    }

    @Test
    @DisplayName("set dx, dy invalid argument exception test")
    void testSetDXDYInvalidArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ball.setDX(0);
        });

        try {
            ball.setDX(50);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }

        assertThrows(IllegalArgumentException.class, () -> {
            ball.setDY(0);
        });

        try {
            ball.setDY(50);
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
        assertEquals(10, ball.getDX());

        ball.setDX(20);

        assertEquals(20, ball.getDX());
    }

    @Test
    @DisplayName("get dy test")
    void testGetDY() {
        assertEquals(10, ball.getDY());

        ball.setDY(20);

        assertEquals(20, ball.getDY());
    }

    @Test
    void testMove() {
        ball.setDX(15);
        ball.setDY(10);
        ball.move();

        assertAll(() -> {
            assertEquals(25, ball.getX());
            assertEquals(20, ball.getY());
        });
    }

    @Test
    void testMoveTo() {
        ball.moveTo(20, 25);

        assertAll(() -> {
            assertEquals(20, ball.getX());
            assertEquals(25, ball.getY());
        });
    }

    @Test
    @DisplayName("test set dx")
    void testSetDX() {
        ball.setDX(15);
        assertEquals(15, ball.getDX());
    }

    @Test
    @DisplayName("test set dy")
    void testSetDY() {
        ball.setDY(15);
        assertEquals(15, ball.getDY());
    }
}
