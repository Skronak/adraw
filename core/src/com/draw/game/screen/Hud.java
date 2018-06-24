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
import com.draw.game.listener.IconMovableListener;
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

    // TODO: TESTER HUD DIRECTEMENT DANS STAGE, DEPLACE SYSTEMATIQUEMENT
    public Hud(MainScreen playScreen) {
        this.playScreen = playScreen;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
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

        Texture texture = new Texture(Gdx.files.internal("icon/building_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image image = new Image(texture);
        image.addListener(new IconMovableListener(this,image,1));
        scrollContainerVG.addActor(image);

        Texture texture2 = new Texture(Gdx.files.internal("icon/building2_icon.png"));
        Image image2 = new Image(texture2);
        image2.addListener(new IconMovableListener(this,image2,2));
        scrollContainerVG.addActor(image2);

        Texture texture3 = new Texture(Gdx.files.internal("icon/building3_icon.png"));
        Image image3 = new Image(texture3);
        image3.addListener(new IconMovableListener(this,image3,3));
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
