package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.listener.InputListener;
import com.mygdx.game.player.Player;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MenuScreen;

public class GameClass extends Game {

    private InputListener inputListener;

    private OrthographicCamera camera;
    //Camera settings
    private final float MAX_CAMERA_ZOOM = 1.2f;
    private final float MIN_CAMERA_ZOOM = 0.8f;

    //world
    private int worldWidth = 800;
    private int worldHeight = 800;

    private static Player currentPlayer;

    //screens
    private Screen currentScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;

    //batch
    private Batch batch;

	@Override
	public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        inputListener = new InputListener(this);
        gameScreen = new GameScreen(camera, this, w, h);
        menuScreen = new MenuScreen(this, w, h);
        currentScreen = gameScreen;
        this.setScreen(currentScreen);
        batch = new SpriteBatch();
	}

	public Batch getBatch() {
	    return batch;
    }

	@Override
	public void render() {
	    super.render();
        handleInput();
		//Gdx.gl.glClearColor(1, 0, 0, 0);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
	}

	public void increaseZoom() {
        if (camera.zoom <= MAX_CAMERA_ZOOM) {
            camera.zoom += 0.02;
            boundCamera();
        }
    }

    public void decreaseZoom() {
        if (camera.zoom >= MIN_CAMERA_ZOOM) {
            camera.zoom -= 0.02;
            boundCamera();
        }
    }

    public void moveCameraLeft() {
        camera.translate(-3, 0, 0);
        boundCamera();
    }

    public void moveCameraRight() {
        camera.translate(3, 0, 0);
        boundCamera();
    }

    public void moveCameraUp() {
        camera.translate(0, 3, 0);
        boundCamera();
    }

    public void moveCameraDown() {
        camera.translate(0, -3, 0);
        boundCamera();
    }

    public void setCameraPosition(float x, float y) {
        camera.translate(x, y, 0);
        boundCamera();
    }

    public void changeCameraPosition(float deltaX, float deltaY) {
        camera.translate(deltaX, deltaY, 0);
        boundCamera();
    }

    private void boundCamera() {
        camera.zoom = MathUtils.clamp(camera.zoom, MIN_CAMERA_ZOOM, MAX_CAMERA_ZOOM);

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, worldWidth - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, worldHeight - effectiveViewportHeight / 2f);
    }

	private void handleInput() {
        inputListener.keyPressed();
        //Gdx.app.log("fps", String.valueOf(Gdx.graphics.getFramesPerSecond()));
    }
	
	@Override
	public void dispose () {
	}

    public static Player getCurrentPlayer() {
	    if (currentPlayer == null) {
	        currentPlayer = new Player();
        }

        return currentPlayer;
    }
}
