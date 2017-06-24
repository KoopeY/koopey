package com.mygdx.game.figure;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class PolygonMoveArea {
    private int spriteSize;
    private ShapeRenderer shapeRenderer;
    private int moveTiles;

    public PolygonMoveArea(int spriteSize, int moveTiles) {
        shapeRenderer = new ShapeRenderer();
        this.spriteSize = spriteSize;
        this.moveTiles = moveTiles;
    }

    private MovableAreaVertex[][] getVertices(Point point) {
        MovableAreaVertex[][] rectanglesPoints = new MovableAreaVertex[moveTiles * 2 + 1][moveTiles * 2 + 1];

        Point leftTopPoint = new Point(
                point.x - moveTiles * spriteSize,
                point.y - moveTiles * spriteSize
        );

        for (int i = 0; i < moveTiles * 2 + 1; i++) {
            for (int j = 0; j < moveTiles * 2 + 1; j++) {
                boolean isMovable = true;

                if ((i + j) < moveTiles) {
                    isMovable = false;
                }

                if ((i + j) > moveTiles * 4 - moveTiles) {
                    isMovable = false;
                }

                if (i != moveTiles && Math.abs(j - i) > moveTiles) {
                    isMovable = false;
                }

                rectanglesPoints[i][j] = new MovableAreaVertex(
                    new Point(
                        leftTopPoint.x + i * spriteSize,
                        leftTopPoint.y + j * spriteSize
                    ),
                    isMovable
                );
            }
        }

        return rectanglesPoints;
    }

    public void draw(Point position) {
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(80 / 255.0f, 80 / 255.0f, 50 / 255.0f, 1);
        MovableAreaVertex[][] points = getVertices(position);
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length; j++) {
                if (points[i][j].isMovable()) {
                    shapeRenderer.rect(
                            points[i][j].getPoint().x,
                            points[i][j].getPoint().y,
                            spriteSize,
                            spriteSize
                    );
                }
            }
        }
        shapeRenderer.end();
    }
}

class MovableAreaVertex {
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
