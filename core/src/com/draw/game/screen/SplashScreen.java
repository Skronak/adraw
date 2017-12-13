package com.draw.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.draw.game.Constants;
import com.draw.game.MyGdxGame;

/**
 * Created by Skronak on 15/07/2017.
 */

public class SplashScreen implements Screen {

    private Texture texture=new Texture(Gdx.files.internal("logo.png"));
    private Image splashImage=new Image(texture);
    private MainScreen playScreen = new MainScreen();
    private MyGdxGame game;
    private Camera camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
    private StretchViewport viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
    private Stage stage = new Stage(viewport);

    public SplashScreen(MyGdxGame game){
        this.game=game;
    }

    @Override
    public void show() {
        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0)
                , Actions.fadeIn(2.0f), Actions.delay(0.5f), Actions.fadeOut(2.0f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(playScreen);
                    }
                })));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
        texture.dispose();
        stage.dispose();
    }
}
