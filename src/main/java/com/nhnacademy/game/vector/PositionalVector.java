package com.nhnacademy.game.vector;

public class PositionalVector extends Vector {
    public PositionalVector(int dx, int dy) {
        super(dx, dy);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", getDx(), getDy());
    }
}
