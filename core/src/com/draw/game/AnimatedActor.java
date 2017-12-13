package com.draw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Skronak on 09/02/2017.
 */
public class AnimatedActor extends Actor {

        private Animation animation;
        private float deltatime;
        private TextureRegion currentFrame;
        private boolean playAnimation = true;
        private boolean isMoveable = false;

        public AnimatedActor(int posX, int posY, int width, int height, float animSpeed, Array<TextureRegion> frames, Animation.PlayMode playMode) {
            deltatime = 0;
            this.setWidth(width);
            this.setHeight(height);
            this.setPosition(posX, posY);
            animation = new Animation(animSpeed, frames);
            animation.setPlayMode(playMode);
        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            currentFrame = (TextureRegion) animation.getKeyFrame(deltatime, true);
            batch.draw(currentFrame,getX(),getY(),getWidth(),getHeight());
        }

        @Override
        public void act(float deltaTime)
        {
            if (playAnimation) {
                super.act(deltaTime);
                deltatime += deltaTime;
            }
        }

    public Animation getIdleAnimation() {
        return animation;
    }

    public void increaseSpeed(float value) {
        animation.setFrameDuration(value);
            }

    public void decreaseSpeed(float value){
        animation.setFrameDuration(value);
    }

    public void setDeltatime(float deltatime) {
        this.deltatime = deltatime;
    }

    public boolean isPlayAnimation() {
        return playAnimation;
    }

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
    }

    public boolean isMoveable() {
        return isMoveable;
    }

    public void setMoveable(boolean moveable) {
        isMoveable = moveable;
    }
}
