package com.draw.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.draw.game.screen.MainScreen;

/**
 * Created by Skronak on 10/12/2017.
 */

public class TipsManager {

    private Skin skin;
    private MainScreen mainScreen;
    private boolean firstTips = true;
    private Image image;

    public TipsManager(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public void generateTips(){
        if (firstTips) {
            image = new Image(new TextureRegion((new Texture(Gdx.files.internal("tips1.png")))));
            image.setPosition(500,400);
            mainScreen.getStage().addActor(image);
        }
    }

    public void updateTips() {
        if (firstTips) {
            image.addAction(Actions.sequence(
                    Actions.fadeOut(2f),
                    Actions.removeActor(image))
            );
            firstTips = false;
        }
    }

    public boolean isFirstTips() {
        return firstTips;
    }

    public void setFirstTips(boolean firstTips) {
        this.firstTips = firstTips;
    }
}
