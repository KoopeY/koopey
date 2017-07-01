package com.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.figure.Point;
import com.mygdx.game.listener.KnightEventListener;
import com.mygdx.game.stage.GameStage;

public class Knight extends Actor {
    private Sprite currentSprite;
    private Sprite activeSprite;
    private Sprite inactiveSprite;

    private KnightEventListener listener;
    private boolean isActive = false;

    //settings
    private int moveTiles = 3;
    private Point position;
    private int size = 16;


    public Knight(int x, int y) {
        position = new Point(size * x, size * y);
        listener = new KnightEventListener(this);
        inactiveSprite = new Sprite(new Texture(Gdx.files.internal("actor/Knight.png")));
        activeSprite = new Sprite(new Texture(Gdx.files.internal("actor/Knight2.png")));

        setWidth(size);
        setHeight(size);
        setBounds(position.getX(), position.getY(), size, size);
        setTouchable(Touchable.enabled);
        addListener(listener);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (isActive) {
            currentSprite = activeSprite;
            GameStage.addMovableArea(position, moveTiles, size);
            GameStage.clearActiveActor();
            GameStage.setActiveActor(this);
        } else {
            //GameStage.removeMovableArea();
            currentSprite = inactiveSprite;
        }

        batch.draw(currentSprite, position.getX(), position.getY(), size, size);
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive() {
        return isActive;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void setPosition(float x, float y) {
        this.position = new Point((int)x, (int)y);
    }
}
