package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.listener.InputListener;

public class GameClass extends ApplicationAdapter {
	private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    private String mapPath = "map/map.tmx";
    private InputListener inputListener;

    //Camera settings
    private final float MAX_CAMERA_ZOOM = 1.2f;
    private final float MIN_CAMERA_ZOOM = 0.8f;

	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

		tiledMap = new TmxMapLoader().load(mapPath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        inputListener = new InputListener(this);
	}

	@Override
	public void render () {
        handleInput();
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
	}

	public void increaseZoom() {
        if (camera.zoom <= MAX_CAMERA_ZOOM) {
            camera.zoom += 0.02;
        }
    }

    public void decreaseZoom() {
        if (camera.zoom >= MIN_CAMERA_ZOOM) {
            camera.zoom -= 0.02;
        }
    }

    public void moveCameraLeft() {
        camera.translate(-3, 0, 0);
    }

    public void moveCameraRight() {
        camera.translate(3, 0, 0);
    }

    public void moveCameraUp() {
        camera.translate(0, 3, 0);
    }

    public void moveCameraDown() {
        camera.translate(0, -3, 0);
    }

	private void handleInput() {
        inputListener.keyPressed();

        Gdx.app.log("fps", String.valueOf(Gdx.graphics.getFramesPerSecond()));
        Gdx.app.log("camera zoom", String.valueOf(camera.zoom));
        //Gdx.app.log("Camera", camera.position.x + " " + camera.position.y);
    }
	
	@Override
	public void dispose () {
		tiledMap.dispose();
	}
}
