package com.nhnacademy.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhnacademy.game.exception.InvalidSizeException;
import com.nhnacademy.game.exception.OutOfBoundsException;
import com.nhnacademy.game.obj.ball.Ball;

public class BallTest {

    Ball ball1;
    static final Logger logger = LoggerFactory.getLogger(BallTest.class);

    @BeforeEach
    void setUp() {
        try {
            ball1 = new Ball(new Rectangle(10, 30, 10, 10));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

    @Test
    @DisplayName("공 생성 시  OutOfBoundsException 테스트")
    void testCreateLargeBall() {
        assertThrows(OutOfBoundsException.class, () -> {
            Ball ball = new Ball(new Rectangle(Integer.MAX_VALUE, 0, 2, 2));
        });

        try {
            Ball ball = new Ball(new Rectangle(0, Integer.MAX_VALUE, 2, 2));
        } catch (Exception e) {
            assertEquals(OutOfBoundsException.class, e.getClass());
        }

        try {
            Ball ball = new Ball(new Rectangle(Integer.MIN_VALUE, 0, 2, 2));
        } catch (Exception e) {
            assertEquals(OutOfBoundsException.class, e.getClass());
        }

        try {
            Ball ball = new Ball(new Rectangle(0, Integer.MIN_VALUE, 2, 2));
        } catch (Exception e) {
            assertEquals(OutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("공 생성 시  InvalidSizeException 테스트")
    void testCreateInvalidSizeBall() {
        try {
            Ball ball = new Ball(new Rectangle(0, 0, -2, -2));
        } catch (Exception e) {
            assertEquals(InvalidSizeException.class, e.getClass());
        }

        try {
            Ball ball = new Ball(new Rectangle(0, 0, 0, 0));
        } catch (Exception e) {
            assertEquals(InvalidSizeException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("공 반지름 얻기")
    void testGetRadius() {
        assertEquals(5, ball1.getRadius());
    }

    @Test
    @DisplayName("공 중심의 x좌표값 얻기")
    void testGetX() {
        assertEquals(15, ball1.getCenterX());
    }

    @Test
    @DisplayName("공 중심의 y좌표값 얻기")
    void testGetY() {
        assertEquals(35, ball1.getCenterY());
    }

    @Test
    @DisplayName("공이 차지하는 공간 중 x좌표 최솟값 얻기")
    void testGetMinX() {
        assertEquals(10, ball1.getMinX());
    }

    @Test
    @DisplayName("공이 차지하는 공간 중 y좌표 최솟값 얻기")
    void testGetMinY() {
        assertEquals(30, ball1.getMinY());
    }

    @Test
    @DisplayName("공이 차지하는 공간 중 x좌표 최댓값 얻기")
    void testGetMaxX() {
        assertEquals(20, ball1.getMaxX());
    }

    @Test
    @DisplayName("공이 차지하는 공간 중 y좌표 최댓값 얻기")
    void testGetMaxY() {
        assertEquals(40, ball1.getMaxY());
    }

    @Test
    @DisplayName("공의 너비 얻기")
    void testGetWidth() {
        assertEquals(10, ball1.getWidth());
    }

    @Test
    @DisplayName("공의 높이 얻기")
    void testGetHeight() {
        assertEquals(10, ball1.getHeight());
    }


    @Test
    void testToString() {
        assertEquals("[" + ball1.getId() + ", (15,35), 5]", ball1.toString());
    }
}
