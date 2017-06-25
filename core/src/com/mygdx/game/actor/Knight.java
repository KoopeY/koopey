package com.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.figure.PolygonMoveArea;
import com.mygdx.game.listener.KnightEventListener;

import java.awt.*;

public class Knight extends Actor {
    private Sprite currentSprite;
    private Sprite activeSprite;
    private Sprite inactiveSprite;
    private KnightEventListener listener;
    private boolean isActive = false;
    private PolygonSpriteBatch polygonSpriteBatch;

    //settings
    int moveTiles = 2;
    Point position;
    int size = 32;
    PolygonMoveArea moveArea;

    public Knight() {
        position = new Point(32 * 5, 32 * 5);
        listener = new KnightEventListener();
        inactiveSprite = new Sprite(new Texture(Gdx.files.internal("actor/knight.png")));
        activeSprite = new Sprite(new Texture(Gdx.files.internal("actor/knight2.png")));
        setWidth(32);
        setHeight(32);
        setBounds(position.x, position.y, size, size);
        setTouchable(Touchable.enabled);
        addListener(listener);
        polygonSpriteBatch = new PolygonSpriteBatch();
        moveArea = new PolygonMoveArea(size, moveTiles);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);

        isActive = listener.isClicked();
        if (isActive) {
            currentSprite = activeSprite;
            moveArea.draw(position);
        } else {
            currentSprite = inactiveSprite;
        }

        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
        batch.draw(currentSprite, position.x, position.y, size, size);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
