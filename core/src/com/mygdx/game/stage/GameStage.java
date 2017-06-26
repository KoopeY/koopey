package com.mygdx.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.actor.Knight;
import com.mygdx.game.actor.group.MovableAreaGroup;
import com.mygdx.game.figure.Point;

public class GameStage {
    private Stage stage;
    private static MovableAreaGroup movableAreaGroup;
    private static Actor activeActor;

    public GameStage(FitViewport fitViewport) {
        stage = new Stage(fitViewport);
        Knight knight = new Knight(5, 2);
        movableAreaGroup = new MovableAreaGroup();
        stage.addActor(knight);
        stage.addActor(new Knight(10, 10));
        stage.addActor(new Knight(15, 15));
        stage.addActor(movableAreaGroup);
        Gdx.input.setInputProcessor(stage);
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public static void addMovableArea(Point position, int moveTiles, int size) {
        movableAreaGroup.clear();
        movableAreaGroup.clearMovableArea();
        movableAreaGroup.createMovableArea(position, moveTiles, size);
    }

    public static void removeMovableArea() {
        movableAreaGroup.clear();
    }

    public static void setActiveActor(Actor actor) {
        activeActor = actor;
    }

    public static Actor getActiveActor() {
        return activeActor;
    }

    public static void clearActiveActor() {
        activeActor = null;
    }
}
