package com.draw.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.draw.game.AnimatedActor;
import com.draw.game.Constants;
import com.draw.game.MainScreenActor;
import com.draw.game.MyGdxGame;
import com.draw.game.VehiculeActor;
import com.draw.game.actor.AnimatedBaseActor;
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
    private MyGdxGame myGdxGame;
    private Group background;
    private Group buildingGroup;
    private Group decorationBefGroup;
    private Group vehiculeGroup;
    private Group decorationAftGroup;

    public MainScreen(MyGdxGame game){
        this.myGdxGame = game;
        backgroundImage = new Image(new Texture(Gdx.files.internal("ground.png")));
        //shadowImg = new Image(new Texture(Gdx.files.internal("icon/building_icon.png")));

        Group background= new Group();
        Group buildingGroup;
        Group decorationBefGroup;
        Group vehiculeGroup;
        Group decorationAftGroup;

        shadowImg = new Image(myGdxGame.getAssetManager().getAssetManager().get("shadow.png",Texture.class));
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
        //Array<TextureRegion> frames = new Array<TextureRegion>();
        //frames.add(new TextureRegion(new Texture(Gdx.files.internal("building/build1.png"))));
        //frames.add(new TextureRegion(new Texture(Gdx.files.internal("building/build2.png"))));
        //frames.add(new TextureRegion(new Texture(Gdx.files.internal("building/build2.png"))));
        Array<TextureRegion> truckFrames = myGdxGame.getAssetManager().getTruckFrames();

        //AnimatedActor animatedActor = new AnimatedActor(0,0,Constants.OBJECT_BUILDING_WIDTH,Constants.OBJECT_BUILDING_HEIGHT,1,frames, Animation.PlayMode.NORMAL);
        //MainScreenActor dadActor = new MainScreenActor(animatedActor,this, Constants.OBJECT_TYPE_BUILDING);
        AnimatedActor animatedTruck = new AnimatedActor(0,0,300,250,0.05f,truckFrames, Animation.PlayMode.NORMAL);
        VehiculeActor truckActor = new VehiculeActor(animatedTruck,this, Constants.OBJECT_TYPE_VEHICULE,100f);

        stage.addActor(backgroundImage);
        stage.addActor(shadowImg);
        //stage.addActor(dadActor);
        stage.addActor(truckActor);

        //PARTIE DE TEST: LONG PRESS
       truckActor.addListener(new GameObjectGestureListener(truckActor));

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

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
