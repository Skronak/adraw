package com.draw.game.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.draw.game.AnimatedActor;
import com.draw.game.MainScreenActor;

/**
 * Created by Skronak on 11/11/2017.
 */

public class GameObjectGestureListener extends ActorGestureListener {
    MainScreenActor parentActor;
    float h;


    public GameObjectGestureListener(MainScreenActor actor) {
        parentActor = actor;
        h = actor.getHeight() / 2;
    }

    public boolean longPress(Actor actor, float x, float y) {
        Gdx.app.log("LONG PRESS", "LONG PRESS");
        parentActor.showMenu();

        return true;
    }

}
