package com.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.listener.KnightEventListener;

public class Knight extends Actor {
    private Sprite currentSprite;
    private Sprite activeSprite;
    private Sprite inactiveSprite;
    private KnightEventListener listener;
    private boolean isActive = false;

    public Knight() {
        listener = new KnightEventListener();
        inactiveSprite = new Sprite(new Texture(Gdx.files.internal("actor/knight.png")));
        activeSprite = new Sprite(new Texture(Gdx.files.internal("actor/knight2.png")));
        setWidth(32);
        setHeight(32);
        setBounds(0, 0, 32, 32);
        setTouchable(Touchable.enabled);
        addListener(listener);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.draw(batch);
        isActive = listener.isClicked();

        if (isActive) {
            currentSprite = activeSprite;
        } else {
            currentSprite = inactiveSprite;
        }

        batch.draw(currentSprite, 0, 0, 32, 32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
