package com.draw.game.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.draw.game.AnimatedActor;

/**
 * Created by Skronak on 11/11/2017.
 */

public class GameObjectGestureListener extends ActorGestureListener {
    AnimatedActor parentActor;
    float touchDown_x;
    float touchDown_y;
    float dx;
    float dy;
    float h;


    public GameObjectGestureListener(AnimatedActor actor) {
        parentActor = actor;
        h = actor.getHeight() / 2;
    }

    public boolean longPress(Actor actor, float x, float y) {
        Gdx.app.log("LONG PRESS", "LONG PRESS");
        parentActor.setMoveable(true);

        parentActor.addAction(Actions.parallel(
                Actions.scaleTo(1.5f, 1.5f, 0.25f, Interpolation.fade),
                Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.5f), 0.25f, Interpolation.fade) )
        );

        return true;
    }
}
