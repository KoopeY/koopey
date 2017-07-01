package com.mygdx.game.listener;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameClass;

/**
 * Created by KoopeY on 27.06.2017.
 */
public class StageGestureListener implements GestureDetector.GestureListener {

    private GameClass game;

    public StageGestureListener(GameClass game) {
        this.game = game;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        /*if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                game.moveCameraLeft();
            } else {
                game.moveCameraRight();
            }
        } else {
            if (velocityY > 0) {
                game.moveCameraUp();
            } else {
                game.moveCameraDown();
            }
        }*/
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        game.changeCameraPosition(-deltaX / 2, deltaY / 2);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (initialDistance / distance > 1) {
            game.decreaseZoom();
        }

        if (initialDistance / distance < 1) {
            game.increaseZoom();
        }

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
