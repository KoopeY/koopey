package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.GameClass;
import com.mygdx.game.player.Player;
import com.mygdx.game.stage.GameStage;

public class GameScreen implements Screen {

    private Player currentPlayer;
    private GameClass game;

    private String mapPath = "map/map.tmx";
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    //camera
    private OrthographicCamera camera;

    //tiled map
    private static TiledMapTileLayer tiledMapTileLayer;

    //Stage
    private GameStage gameStage;

    //game screen
    private String goldPath = "screen/game/goldCoin1.png";
    private Texture goldTexture;
    private float height;
    private float width;
    private BitmapFont font;

    public GameScreen(OrthographicCamera camera, GameClass game, float width, float height) {
        this.camera = camera;
        this.game = game;
        this.width = width;
        this.height = height;

        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        tiledMap = new TmxMapLoader().load(mapPath);
        tiledMapTileLayer = ((TiledMapTileLayer) tiledMap.getLayers().get("steps"));
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        FitViewport viewport = new FitViewport(width, height, camera);
        currentPlayer = GameClass.getCurrentPlayer();
        gameStage = new GameStage(viewport, game);
        goldTexture = new Texture(Gdx.files.internal(goldPath));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        gameStage.render();

        game.getBatch().begin();
        game.getBatch().draw(goldTexture, 15, height - 45, 30, 30);
        font.draw(game.getBatch(), String.valueOf(currentPlayer.getGold()), 40, height - 25);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    public static TiledMapTileLayer getTiledMapTileLayer() {
        return tiledMapTileLayer;
    }
}
