package com.draw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Skronak on 04/11/2017.
 */

public class AnimationImage extends Image
{
    protected Animation animation = null;
    private float stateTime = 0;

    public AnimationImage(Animation animation) {
        super((TextureRegion) animation.getKeyFrame(0f));
        this.animation = animation;
    }

    @Override
    public void act(float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion((TextureRegion) animation.getKeyFrame(stateTime+=delta, true));
        super.act(delta);
    }
}