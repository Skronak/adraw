package com.draw.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.draw.game.AnimatedActor;
import com.draw.game.Constants;
import com.draw.game.DragAndDropActor;
import com.draw.game.listener.GameObjectGestureListener;
import com.draw.game.listener.GameObjectListener;
import com.draw.game.listener.MainInputProcessor;
import com.draw.game.manager.TipsManager;

/**
 * Created by Skronak on 20/11/2017.
 */

public class MainScreen implements Screen {

    private Stage stage;
    private Hud hud;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private Image backgroundImage;
    private InputMultiplexer inputMultiplexer;
    private Image shadowImg;
    private boolean isMoveable; // indique si on peut scroller l'ecran
    private TipsManager tipsManager;

    public MainScreen(){
        backgroundImage = new Image(new Texture(Gdx.files.internal("ground.png")));
        shadowImg = new Image(new Texture(Gdx.files.internal("bat1_ptr.png")));
        shadowImg.setVisible(false);
        shadowImg.setScaleX(1.3f);
        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);
        hud = new Hud(this);
        isMoveable=true;
        tipsManager = new TipsManager(this);
        MainInputProcessor customInputProcessor = new MainInputProcessor(this);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(hud.getStage());
        inputMultiplexer.addProcessor(customInputProcessor);

        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void show() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("building/build1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("building/build2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("building/build2.png"))));
        AnimatedActor animatedActor = new AnimatedActor(0,0,170,320,1,frames, Animation.PlayMode.NORMAL);
        DragAndDropActor dadActor = new DragAndDropActor(animatedActor,this, Constants.OBJECT_TYPE_BUILDING);

        stage.addActor(backgroundImage);
        stage.addActor(shadowImg);
        stage.addActor(dadActor);


        //PARTIE DE TEST: LONG PRESS
       // Array<TextureRegion> frames = new Array<TextureRegion>();
       // frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat1.png"))));
       // frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat1a.png"))));
       // frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat1b.png"))));
       // frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat1c.png"))));
       // frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat1d.png"))));
       // AnimatedActor animatedActor = new AnimatedActor(0,0,170,320,1,frames, Animation.PlayMode.NORMAL);
       // animatedActor.addListener(new GameObjectListener(animatedActor));
       // animatedActor.addListener(new GameObjectGestureListener(animatedActor));
       // stage.addActor(animatedActor);
        tipsManager.generateTips();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        Gdx.gl.glClearColor( 1,1,1,1 );
        camera.update();
        stage.draw();
        stage.act();

        hud.draw();
    }

    public void updateTips() {
        this.tipsManager.updateTips();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    public Stage getStage() {
        return stage;
    }

    public Image getShadowImg() {
        return shadowImg;
    }

    public boolean isMoveable() {
        return isMoveable;
    }

    public void setMoveable(boolean moveable) {
        isMoveable = moveable;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public Hud getHud() {
        return hud;
    }
}
