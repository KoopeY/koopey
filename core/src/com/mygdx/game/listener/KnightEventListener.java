package com.mygdx.game.listener;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.stage.GameStage;

public class KnightEventListener extends ClickListener {
    boolean isClicked = false;

    public boolean isClicked() {
        return isClicked;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("touchDown");
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        isClicked = isClicked ? false : true;
        if (!isClicked) {
            GameStage.removeMovableArea();
        }
    }

    @Override
    public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
    }
}
