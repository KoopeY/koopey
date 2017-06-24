package com.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.listener.KnightEventListener;

public class Knight extends Actor {
    private Sprite sprite;
    private ClickListener listener;

    public Knight() {
        listener = new KnightEventListener();
        sprite = new Sprite(new Texture(Gdx.files.internal("actor/knight.png")));
        setBounds(0, 0, 32, 32);
        setTouchable(Touchable.enabled);
        addListener(listener);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.draw(batch);
        batch.draw(sprite, 0, 0, 32, 32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
