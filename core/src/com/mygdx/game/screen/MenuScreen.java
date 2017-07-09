package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameClass;

/**
 * Created by KoopeY on 01.07.2017.
 */
public class MenuScreen implements Screen {

    private GameClass game;

    private String startGameButtonPath = "screen/buttons/Button08.png";
    private String settingsGameButtonPath = "screen/buttons/Button09.png";
    private String shopGameButtonPath = "screen/buttons/Button10.png";

    private Texture startGameTexture;
    private Texture settingsGameTexture;
    private Texture shopGameTexture;

    private float width;
    private float height;

    public MenuScreen(final GameClass game, float width, float height) {
        this.width = width;
        this.height = height;
        this.game = game;
        this.startGameTexture = new Texture(Gdx.files.internal(startGameButtonPath));
        this.settingsGameTexture = new Texture(Gdx.files.internal(settingsGameButtonPath));
        this.shopGameTexture = new Texture(Gdx.files.internal(shopGameButtonPath));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        game.getBatch().draw(startGameTexture, width / 2 - 128 / 2, height / 2 - 32 / 2, 128, 32);
        game.getBatch().draw(settingsGameTexture, width / 2 - 128 / 2, height / 2 - 32 / 2 - 50, 128, 32);
        game.getBatch().draw(shopGameTexture, width / 2 - 128 / 2, height / 2 - 32 / 2 - 100, 128, 32);
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

    }
}
