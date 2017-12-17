package com.draw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

public class DragAndDropManager extends Group {

    com.draw.game.screen.PlayScreen playScreen;
    Texture shadowImg;

    public DragAndDropManager(com.draw.game.screen.PlayScreen playScreen) {
        this.playScreen=playScreen;
        shadowImg = new Texture(Gdx.files.internal("bat1.png"));
    }

}
