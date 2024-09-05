package com.nhnacademy.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaintableBallTest {

    PaintableBall ball;

    @BeforeEach
    void setUp() {
        try {
            ball = new PaintableBall(10, 10, 1, Color.blue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("get color")
    void testGetColor() {
        assertEquals(Color.blue, ball.getColor());
    }

    @Test
    @DisplayName("set color")
    void testSetColor() {
        ball.setColor(Color.black);
        assertEquals(Color.black, ball.getColor());
    }

    @Test
    void testToString() {
        assertEquals("[(10,10), 1, " + Color.blue + "]", ball.toString());
    }
}
