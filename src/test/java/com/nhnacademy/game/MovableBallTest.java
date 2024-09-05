package com.nhnacademy.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovableBallTest {

    MovableBall ball;

    @BeforeEach
    void setUp() {
        ball = new MovableBall(10, 10, 4, Color.blue);
    }

    @Test
    void testGetDX() {
        assertEquals(0, ball.getDX());

        ball.setDX(10);

        assertEquals(10, ball.getDX());
    }

    @Test
    void testGetDY() {
        assertEquals(0, ball.getDY());

        ball.setDY(10);

        assertEquals(10, ball.getDY());
    }

    @Test
    void testMove() {

    }

    @Test
    void testMoveTo() {

    }

    @Test
    void testSetDX() {

    }

    @Test
    void testSetDY() {

    }
}
