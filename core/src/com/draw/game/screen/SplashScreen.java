package com.draw.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.draw.game.Constants;
import com.draw.game.MyGdxGame;

/**
 * Created by Skronak on 15/07/2017.
 */

public class SplashScreen implements Screen {

    private Image splashImage;
    private MyGdxGame game;
    private Camera camera;
    private StretchViewport viewport;
    private Stage stage;
    private boolean devMode;

    public SplashScreen(MyGdxGame game, boolean devMode){
        this.game = game;
        splashImage=new Image(new Texture(Gdx.files.internal("logo.png")));
        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);
        this.devMode = devMode;
    }

    @Override
    public void show() {
        stage.addActor(splashImage);

        if (!devMode){
            splashImage.addAction(Actions.sequence(Actions.alpha(0)
                    , Actions.fadeIn(1.0f)));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        if (game.getAssetManager().getAssetManager().update() && splashImage.getActions().size==0) {
            Gdx.app.log("SplashScreen","Asset loaded !");
            if (devMode){
                game.setScreen(new MainScreen(game));
            } else {
                splashImage.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new MainScreen(game));
                    }
                })));
            }
        }
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
        dispose();
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
