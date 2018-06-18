package com.draw.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.draw.game.Constants;
import com.draw.game.listener.HudButtonListener;
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
    private Image test1;
    private Image test2;
    private Image test3;
    private Image test4;
    private Image test5;
    private Image test6;
    private Image test7;
    private Image test8;
    private Image test9;
    private CustomVerticalGroup scrollContainerVG;

    // TODO: TESTER HUD DIRECTEMENT DANS STAGE, DEPLACE SYSTEMATIQUEMENT
    public Hud(MainScreen playScreen) {
        this.playScreen = playScreen;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        Texture texture = new Texture(Gdx.files.internal("building_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test1 = new Image(texture);
        test1.addListener(new HudButtonListener(this,test1,2));
        texture = new Texture(Gdx.files.internal("truck_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test2 = new Image(texture);
        test2.addListener(new HudButtonListener(this,test2,1));
        texture = new Texture(Gdx.files.internal("traffic_light_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test3 = new Image(texture);
        test3.addListener(new HudButtonListener(this,test3,1));
        texture = new Texture(Gdx.files.internal("building2_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test4 = new Image(texture);
        test4.addListener(new HudButtonListener(this,test4,1));
        texture = new Texture(Gdx.files.internal("building3_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test5 = new Image(texture);
        test5.addListener(new HudButtonListener(this,test5,1));
        texture = new Texture(Gdx.files.internal("bat5_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test6 = new Image(texture);
        test6.addListener(new HudButtonListener(this,test6,1));
        texture = new Texture(Gdx.files.internal("bat5_icon.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        test7 = new Image(texture);
        test7.addListener(new HudButtonListener(this,test7,1));
        test8 = new Image(new Texture(Gdx.files.internal("bat1_icon.png")));
        test9 = new Image(new Texture(Gdx.files.internal("bat1_icon.png")));

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
        scrollContainerVG.addActor(test1);
        scrollContainerVG.addActor(test2);
        scrollContainerVG.addActor(test3);
        scrollContainerVG.addActor(test4);
        scrollContainerVG.addActor(test5);
        scrollContainerVG.addActor(test6);
        scrollContainerVG.addActor(test7);
        scrollContainerVG.addActor(test8);
        scrollContainerVG.addActor(test9);

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
