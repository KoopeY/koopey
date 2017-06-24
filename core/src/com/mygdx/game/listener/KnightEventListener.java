package com.mygdx.game.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class KnightEventListener extends ClickListener {
    float x = 0;
    float y = 0;

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("touchDown");
        this.x = x;
        this.y = y;
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        System.out.println("touchDragged");
        this.x = x;
        this.y = y;
        super.touchDragged(event, x, y, pointer);
    }
}
