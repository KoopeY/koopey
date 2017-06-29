package com.mygdx.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.GameClass;
import com.mygdx.game.actor.Knight;
import com.mygdx.game.actor.group.MovableAreaGroup;
import com.mygdx.game.figure.Point;
import com.mygdx.game.listener.StageGestureListener;

import java.util.ArrayList;

public class GameStage {
    private Stage stage;
    private static MovableAreaGroup movableAreaGroup;
    private static Actor activeActor;
    private static ArrayList<Knight> knights;

    public GameStage(FitViewport fitViewport, GameClass game) {
        knights = new ArrayList<Knight>();
        stage = new Stage(fitViewport);
        movableAreaGroup = new MovableAreaGroup();

        Knight knight1 = new Knight(5, 2);
        Knight knight2 = new Knight(10, 10);
        Knight knight3 = new Knight(15, 15);

        knights.add(knight1);
        knights.add(knight2);
        knights.add(knight3);

        stage.addActor(knight1);
        stage.addActor(knight2);
        stage.addActor(knight3);
        stage.addActor(movableAreaGroup);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new GestureDetector(new StageGestureListener(game)));
        Gdx.input.setInputProcessor(multiplexer);
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

    public static void setInactiveAllUnits() {
        System.out.println("setInactiveAllUnits");
        for (int i = 0; i < knights.size(); i++) {
            knights.get(i).setActive(false);
        }
    }
}
