package com.draw.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.draw.game.Constants;
import com.draw.game.MyGdxGame;
import com.draw.game.listener.IconMovableListener;
import com.draw.game.manager.MyAssetManager;
import com.draw.game.utils.CustomVerticalGroup;

/**
 * Created by Skronak on 11/12/2016.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Table table;
    private MainScreen playScreen;
    private Skin skin;
    private CustomVerticalGroup scrollContainerVG;
    private MyAssetManager myAssetManager;

    // TODO: TESTER HUD DIRECTEMENT DANS STAGE, DEPLACE SYSTEMATIQUEMENT
    public Hud(MainScreen playScreen, MyAssetManager myAssetManager) {
        this.playScreen = playScreen;
        this.myAssetManager = myAssetManager;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);
       // texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        initHud();
    }

    private void initHud(){
        table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bgd3.png")))));
        table.setWidth(Constants.MENU_WIDTH);
        table.setHeight(Constants.MENU_HEIGHT);

        table.add(new Image(new Texture(Gdx.files.internal("direction2.png")))).top();
        table.row();

        scrollContainerVG = new CustomVerticalGroup();
        scrollContainerVG.space(10f);

        Texture texture = myAssetManager.getAssetManager().get("icon/building_icon.png",Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image image = new Image(texture);
        image.addListener(new IconMovableListener(this,image,Constants.OBJECT_TYPE_BUILDING));
        scrollContainerVG.addActor(image);

        Texture texture2 = myAssetManager.getAssetManager().get("icon/building2_icon.png",Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image image2 = new Image(texture2);
        image2.addListener(new IconMovableListener(this,image2,Constants.OBJECT_TYPE_BUILDING));
        scrollContainerVG.addActor(image2);

        Texture texture3 = myAssetManager.getAssetManager().get("icon/building3_icon.png",Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image image3 = new Image(texture3);
        image3.addListener(new IconMovableListener(this,image3,Constants.OBJECT_TYPE_BUILDING));
        scrollContainerVG.addActor(image3);

        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);
        table.pad(5f);
        table.add(pane).fill().expand();

        table.row();
        table.add(new Image(new Texture(Gdx.files.internal("direction.png")))).top();

        stage.addActor(table);

    }

    /**
     * Methode draw specifique
     */
    public void draw () {
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    public Stage getStage() {
        return stage;
    }

    public MainScreen getPlayScreen() {
        return playScreen;
    }

    public CustomVerticalGroup getScrollContainerVG() {
        return scrollContainerVG;
    }

}
