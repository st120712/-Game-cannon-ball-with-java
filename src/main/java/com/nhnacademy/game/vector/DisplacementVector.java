package com.nhnacademy.game.vector;

public class DisplacementVector extends Vector {
    public DisplacementVector(int v, int degree) {
        super((int) (v * Math.cos(degree)), (int) (v * Math.sin(degree)));
    }

}
