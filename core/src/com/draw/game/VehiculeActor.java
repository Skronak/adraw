package com.draw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.draw.game.screen.MainScreen;

/**
 * Created by Skronak on 17/12/2017.
 */

public class VehiculeActor extends MainScreenActor {

    private boolean reverse;
    private float speed;
    private float boundMinX, boundMaxX;

    public VehiculeActor(final Actor actor, final MainScreen playScreen, int type, float speed) {
        super(actor, playScreen, type);
        this.speed=speed;
        boundMinX = playScreen.getBackgroundImage().getX();
        boundMaxX = playScreen.getBackgroundImage().getWidth();
    }

    public void run(float deltatime){
        if (dragGroup.getX() < boundMinX) {
            reverse = false;
            ((AnimatedActor)dragActor).flip();
        } else if (dragGroup.getX()> boundMaxX) {
            reverse = true;
            ((AnimatedActor)dragActor).flip();
        }

        if (reverse) {
            dragGroup.moveBy(-deltatime*speed, 0);
        } else {
            dragGroup.moveBy(deltatime*speed, 0);
        }
    }

    public void act (float delta) {
        if (playAnimation) {
            super.act(delta);
            run(delta);
        }
    }
}
