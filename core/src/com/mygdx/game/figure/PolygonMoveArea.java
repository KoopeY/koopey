package com.mygdx.game.figure;

import java.awt.*;

public class PolygonMoveArea {
    private int spriteSize;
    private int moveTiles;

    public PolygonMoveArea(int spriteSize, int moveTiles) {
        this.spriteSize = spriteSize;
        this.moveTiles = moveTiles;
    }

    public MovableAreaVertex[][] getVertices(Point point) {
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

                if (i == j && i == Math.floor((moveTiles * 2 + 1) / 2)) {
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
}
