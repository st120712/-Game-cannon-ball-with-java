package com.nhnacademy.game.vector;

abstract class Vector {

    int dx;
    int dy;

    Vector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getAngle() {
        if (dx == 0 || dy == 0) {
            throw new IllegalArgumentException();
        }

        double inner = dx;
        double length = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        return (int) Math.acos(inner / length);
    }

    public int getMagnitude() {
        return (int) Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    public void add(Vector other) {
        setDx(getDx() + other.getDx());
        setDy(getDy() + other.getDy());
    };

    public void sub(Vector other) {
        setDx(getDx() - other.getDx());
        setDy(getDy() - other.getDy());
    };

    public void turnDx() {
        setDx(-getDx());
    };

    public void turnDy() {
        setDy(-getDy());
    };
}
