package com.mygdx.game.actor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.GameClass;
import com.mygdx.game.actor.group.MovableAreaGroup;
import com.mygdx.game.figure.MovableAreaVertex;
import com.mygdx.game.figure.PolygonMoveArea;
import com.mygdx.game.listener.KnightEventListener;
import com.mygdx.game.stage.GameStage;

import java.awt.*;

public class Knight extends Actor {
    private Sprite currentSprite;
    private Sprite activeSprite;
    private Sprite inactiveSprite;

    private KnightEventListener listener;
    private boolean isActive = false;

    //settings
    int moveTiles = 3;
    Point position;
    int size = 16;

    public Knight(int x, int y) {
        position = new Point(size * x, size * y);
        listener = new KnightEventListener();
        inactiveSprite = new Sprite(new Texture(Gdx.files.internal("actor/knight.png")));
        activeSprite = new Sprite(new Texture(Gdx.files.internal("actor/knight2.png")));

        setWidth(size);
        setHeight(size);
        setBounds(position.x, position.y, size, size);
        setTouchable(Touchable.enabled);
        addListener(listener);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        isActive = listener.isClicked();
        if (isActive) {
            currentSprite = activeSprite;
            GameStage.addMovableArea(position, moveTiles, size);
            GameStage.clearActiveActor();
            GameStage.setActiveActor(this);
        } else {
            //GameStage.removeMovableArea();
            currentSprite = inactiveSprite;
        }

        batch.draw(currentSprite, position.x, position.y, size, size);
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
