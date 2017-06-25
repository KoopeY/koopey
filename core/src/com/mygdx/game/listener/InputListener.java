package com.mygdx.game.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.GameClass;
import org.apache.log4j.Logger;

public class InputListener {

    private Logger logger = Logger.getLogger(InputListener.class);
    private GameClass game;

    public InputListener(GameClass game) {
        this.game = game;
    }

    public void keyPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.moveCameraLeft();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.moveCameraRight();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.moveCameraUp();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.moveCameraDown();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            game.increaseZoom();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.decreaseZoom();
        }
    }
}
