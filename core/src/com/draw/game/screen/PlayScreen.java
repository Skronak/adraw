package com.draw.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.draw.game.AnimatedActor;
import com.draw.game.AnimationImage;
import com.draw.game.Constants;
import com.draw.game.DragAndDropActor;
import com.draw.game.DragAndDropManager;
import com.draw.game.screen.Hud;
import com.draw.game.screen.MainScreen;

public class PlayScreen extends InputListener implements Screen {
	
	Stage stage;
	SpriteBatch	batch;
	ShaderProgram shader;

    Group roadGroup;
    Group buildingGroup;
    Group vehiculeGroup;
    Group trafficLightGroup;

	HorizontalGroup horizontalGroup;
	Hud hud;

    int	WIDTH;
	int	HEIGHT;

	float amplitudeWave = 5.1f;
	float angleWave = 20.0f;
	float angleWaveSpeed = 3.0f;

	OrthographicCamera camera;
	StretchViewport viewport;

	Texture	bat1Img;
    Texture	bat3Img;
    Texture	bat4Img;
    Texture	roadImg;
    Image groundImg;
    Texture	truckImg;
    Texture	trafficLightImg;
    Image shadowImg;
    Image menuImg;
    Image menuImgDelete;

    DragAndDropActor dadActor;
	DragAndDropActor dadActor2;
	DragAndDropActor dadActor3;
    DragAndDropActor dadActor4;
    DragAndDropActor dadActor5;
    DragAndDropActor dadActor6;
	DragAndDropManager dragAndDropManager;

	Animation<TextureRegion> basicAnimation;

	public PlayScreen() {

		dragAndDropManager = new DragAndDropManager(this);

		groundImg = new Image(new Texture(Gdx.files.internal("ground.png")));
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		hud = new Hud(new MainScreen());
		final String vertexShader = Gdx.files.internal("shader/waves.vs.glsl").readString();
        final String fragmentShader = Gdx.files.internal("shader/waves.fs.glsl").readString();
        shader = new ShaderProgram(vertexShader, fragmentShader);

		camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
		viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
		stage = new Stage(viewport);

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		bat1Img = new Texture(Gdx.files.internal("bat1.png"));
        bat3Img = new Texture(Gdx.files.internal("bat3.png"));
        bat4Img = new Texture(Gdx.files.internal("bat4.png"));
        roadImg = new Texture(Gdx.files.internal("road1.png"));

        truckImg = new Texture(Gdx.files.internal("truck.png"));
        trafficLightImg = new Texture(Gdx.files.internal("feu.png"));
        shadowImg = new Image(new Texture(Gdx.files.internal("bat1_ptr.png")));
        shadowImg.setVisible(false);
        menuImg = new Image(new Texture(Gdx.files.internal("bgd.png")));
        menuImg.setWidth(200);
        menuImg.setHeight(Constants.V_HEIGHT);
        menuImgDelete = new Image(new Texture(Gdx.files.internal("bgd2.png")));
        menuImgDelete.setHeight(Constants.V_HEIGHT);

        // TEST ANIMATION
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("bat4.png"))));

        Animation idleAnimation = new Animation(1, frames);
        AnimationImage ai = new AnimationImage(idleAnimation);
/*		dadActor3 = new DragAndDropActor(ai, this, Constants.OBJECT_TYPE_BUILDING);
        dadActor = new DragAndDropActor(new Image(bat1Img),this, Constants.OBJECT_TYPE_BUILDING);
        dadActor2 = new DragAndDropActor(new Image(bat3Img),this, Constants.OBJECT_TYPE_BUILDING);
        dadActor3 = new DragAndDropActor(new Image(bat4Img),this, Constants.OBJECT_TYPE_BUILDING);
        dadActor4 = new DragAndDropActor(new Image(truckImg),this,Constants.OBJECT_TYPE_BUILDING);
        dadActor5 = new DragAndDropActor(new Image(trafficLightImg),this,Constants.OBJECT_TYPE_TRAFFICLIGHT);
        dadActor6 = new DragAndDropActor(new Image(roadImg),this,Constants.OBJECT_TYPE_TRAFFICLIGHT);
		DragAndDropActor dadActor7 = new DragAndDropActor(new Image(bat1Img),this,Constants.OBJECT_TYPE_BUILDING);
		DragAndDropActor dadActor8 = new DragAndDropActor(new Image(bat4Img),this,Constants.OBJECT_TYPE_BUILDING);
		DragAndDropActor dadActor9 = new DragAndDropActor(new Image(bat3Img),this,Constants.OBJECT_TYPE_BUILDING);
		DragAndDropActor dadActor10 = new DragAndDropActor(new Image(bat4Img),this,Constants.OBJECT_TYPE_BUILDING);
		DragAndDropActor dadActor11 = new DragAndDropActor(new Image(bat1Img),this,Constants.OBJECT_TYPE_BUILDING);
        stage.addActor(groundImg);*/
//        stage.addActor(menuImg);// Si on deplace la camera ca restera en place: Sur un autre stage/camera

        stage.addActor(shadowImg);
      	stage.addActor(dadActor);
//	    stage.addActor(dadActor2);
//		stage.addActor(dadActor3);
//        stage.addActor(dadActor4);
//        stage.addActor(dadActor5);
//        stage.addActor(dadActor6);
//		stage.addActor(dadActor7);
//		stage.addActor(dadActor8);
//		stage.addActor(dadActor9);
//		stage.addActor(dadActor10);
//		stage.addActor(dadActor11);
//		AnimatedActor aa = new AnimatedActor(0,0,170,170,1,frames,Animation.PlayMode.NORMAL);
//		stage.addActor(aa);
//		com.draw.game.listener.GameObjectListener gol = new com.draw.game.listener.GameObjectListener(aa);
//		aa.addListener(gol);

		Gdx.input.setInputProcessor( stage );
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
//		Gdx.gl.glClearColor( 223,223,223,1 );
		Gdx.gl.glClearColor( 0,0,0,1 );

		// water waves
		angleWave += delta * angleWaveSpeed;
	    while(angleWave > Math.PI*2)
	        angleWave -= Math.PI*2;
		// set shader vars
		shader.begin();
	    shader.setUniformf("waveData", angleWave, amplitudeWave);
	    shader.end();
		
	    batch.setProjectionMatrix(camera.combined);
//		batch.setShader(shader);
		batch.begin();
//			stage.getRoot().draw(batch, 1);
		stage.draw();
        stage.act();
		batch.flush();
		batch.end();

//		hud.draw();

	}

	@Override
	public void resize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;

		Gdx.app.debug("PlayScreen", "Resize occured w"+width+" h"+height);
		viewport.update(width, height);

//		cam = new OrthographicCamera(width, height);
//		cam.position.set(cam.viewportWidth / 2.0f, cam.viewportHeight / 2.0f, 0.0f);
//		cam.update();
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
		stage.dispose();
		batch.dispose();
	}

    public Image getShadowImg() {
        return shadowImg;
    }

    public void setShadowImg(Image shadowImg) {
        this.shadowImg = shadowImg;
    }
}



