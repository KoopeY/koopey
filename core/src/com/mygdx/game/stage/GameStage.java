package com.mygdx.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.actor.Knight;

public class GameStage {
    private Stage stage;

    public GameStage(FitViewport fitViewport) {
        stage = new Stage(fitViewport);
        Knight knight = new Knight();
        //knight.setPosition(0, 0);
        stage.addActor(knight);
        Gdx.input.setInputProcessor(stage);
    }

    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
