package com.draw.game.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.draw.game.screen.MainScreen;

/**
 * Created by Skronak on 20/11/2017.
 *
 * listener de touhé du mainscreen pour le drag
 */
public class MainInputProcessor implements InputProcessor {

    private MainScreen mainScreen;

    public MainInputProcessor(MainScreen mainScreen){
        this.mainScreen = mainScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("MainInputProcessor","drag from customInputProcessor");
        if (mainScreen.isMoveable()) {
            float newPos = Gdx.input.getDeltaX();
            mainScreen.getStage().getCamera().translate(-newPos, 0f, 0f);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
