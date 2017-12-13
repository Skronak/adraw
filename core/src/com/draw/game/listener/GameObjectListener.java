package com.draw.game.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.draw.game.AnimatedActor;
import com.draw.game.screen.MainScreen;

/**
 * Created by Skronak on 11/11/2017.
 */

public class GameObjectListener extends InputListener {
    AnimatedActor parentActor;
    float touchDown_x;
    float touchDown_y;
    float dx;
    float dy;
    float h;


    public GameObjectListener(AnimatedActor actor) {
        parentActor = actor;
        h = actor.getHeight() / 2;
    }

    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log("down", String.valueOf(x)+"/"+String.valueOf(y));
        parentActor.setPlayAnimation(false);
        touchDown_x = x;
        touchDown_y = h - y;

        return true;
    }

    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (parentActor.isMoveable()) {
            dx = parentActor.getX() - touchDown_x;
            dy = parentActor.getY() - h + touchDown_y;
            parentActor.setPosition(x + dx, y + dy);
        }
        Gdx.app.log("drag", String.valueOf(x+dx)+"/"+String.valueOf(y+dy));

    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log("up", String.valueOf(x)+"/"+String.valueOf(y));
        parentActor.setPlayAnimation(true);
        parentActor.setMoveable(false);

        // x & y = position du curseur relative a l'acteur
        dx = parentActor.getX() - touchDown_x;
    }
}
