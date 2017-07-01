package com.mygdx.game.actor.group;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.GameClass;
import com.mygdx.game.actor.MovableArea;
import com.mygdx.game.figure.MovableAreaVertex;
import com.mygdx.game.figure.Point;
import com.mygdx.game.figure.PolygonMoveArea;

public class MovableAreaGroup extends Table {

    private PolygonMoveArea moveArea;
    private MovableAreaVertex[][] movableArea;

    public MovableAreaGroup() {
    }

    public void clearMovableArea() {
        this.clear();
    }

    public void createMovableArea(Point position, int moveTiles, int size) {
        moveArea = new PolygonMoveArea(size, moveTiles);
        movableArea = moveArea.getVertices(position);
        for (int i = 0; i < movableArea.length; i++) {
            for (int j = 0; j < movableArea[0].length; j++) {

                if (movableArea[i][j].isMovable()
                        && GameClass.getTiledMapTileLayer()
                                .getCell(
                                        movableArea[(int)Math.floor(movableArea.length / 2)][(int)Math.floor(movableArea.length / 2)].getPoint().getX() / size - moveTiles + i,
                                        movableArea[(int)Math.floor(movableArea.length / 2)][(int)Math.floor(movableArea.length / 2)].getPoint().getY() / size - moveTiles + j) != null
                        ) {

                    this.addActor(new MovableArea(
                            new Point(movableArea[i][j].getPoint().getX()
                                    , movableArea[i][j].getPoint().getY())
                            , size)
                    );
                }
            }
        }
    }
}
