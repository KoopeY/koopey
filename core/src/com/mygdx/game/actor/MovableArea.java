package com.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.figure.Point;
import com.mygdx.game.stage.GameStage;

public class MovableArea extends Actor {
    private Sprite movableAreaSprite;
    private Point position;
    private int size;

    public MovableArea(final Point position, final int size) {
        this.position = position;
        this.size = size;

        movableAreaSprite = new Sprite(new Texture(Gdx.files.internal("map/area/movableArea.gif")));
        setWidth(size);
        setHeight(size);
        setBounds(position.getX(), position.getY(), size, size);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                Actor activeActor = GameStage.getActiveActor();
                if (activeActor != null) {
                    activeActor.setBounds(position.getX(), position.getY(), size, size);
                    activeActor.setPosition(position.getX(), position.getY());
                }
            };
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(
                movableAreaSprite,
                position.getX(),
                position.getY(),
                size,
                size
        );
    }
}
