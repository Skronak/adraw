package com.draw.game.actor;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.draw.game.listener.IconMovableListener;
import com.draw.game.screen.Hud;

/**
 * Icon du hud pouvant etre deplacé
 */
public class IconMovableActor extends Image {

    public IconMovableActor(Hud hud, Texture texture, int type){
        {
            setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
        }
        this.addListener(new IconMovableListener(hud,this,type));
    }

}
