package com.mygdx.game.figure;

public class MovableAreaVertex {
    private Point point;
    private boolean isMovable = false;

    public MovableAreaVertex(Point point, boolean isMovable) {
        this.point = point;
        this.isMovable = isMovable;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public String toString() {
        return point.toString();
    }
}