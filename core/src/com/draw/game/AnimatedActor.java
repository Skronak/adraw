package com.draw.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Skronak on 09/02/2017.
 * Gere uniquemnet l'animation
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
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        currentFrame = (TextureRegion) animation.getKeyFrame(deltatime, true);
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
    }

    public void act(float deltaTime) {
        super.act(deltaTime);
        deltatime += deltaTime;
    }

    public void flip() {
        for (Object textureRegion : animation.getKeyFrames()) {
            ((TextureRegion) textureRegion).flip(true, false);
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

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }
}
