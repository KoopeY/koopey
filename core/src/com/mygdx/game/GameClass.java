package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.listener.InputListener;
import com.mygdx.game.stage.GameStage;

public class GameClass extends ApplicationAdapter {
	private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    private String mapPath = "map/map.tmx";
    private InputListener inputListener;

    //Camera settings
    private final float MAX_CAMERA_ZOOM = 1.2f;
    private final float MIN_CAMERA_ZOOM = 0.8f;

    //Stage
    private GameStage gameStage;

    //world
    private int worldWidth = 800;
    private int worldHeight = 800;

    //tiled map
    private static TiledMapTileLayer tiledMapTileLayer;

	@Override
	public void create () {
        float w = Gdx.graphics.getWidth() / 3;
        float h = Gdx.graphics.getHeight() / 3;

        System.out.println(Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

		tiledMap = new TmxMapLoader().load(mapPath);
        tiledMapTileLayer = ((TiledMapTileLayer) tiledMap.getLayers().get("steps"));
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        inputListener = new InputListener(this);
        FitViewport viewp = new FitViewport(w, h, camera);
        gameStage = new GameStage(viewp, this);
	}

	@Override
	public void render () {
        handleInput();
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        gameStage.render();
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
		tiledMap.dispose();
	}

	public static TiledMapTileLayer getTiledMapTileLayer() {
	    return tiledMapTileLayer;
    }
}
