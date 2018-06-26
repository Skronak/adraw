package com.draw.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.draw.game.MyGdxGame;

public class MyAssetManager implements Disposable{

    private AssetManager assetManager;
    private MyGdxGame game;
    private Skin skin;

    public MyAssetManager(MyGdxGame game) {
         this.game = game;
         assetManager = new AssetManager();
         loadTexture();
         loadUtils();
    }

    private void loadTexture(){
        assetManager.load("icon/building_icon.png", Texture.class);
        assetManager.load("icon/building2_icon.png", Texture.class);
        assetManager.load("icon/building3_icon.png", Texture.class);
        assetManager.load("shadow.png", Texture.class);

        assetManager.load("vehicules/truck2.png",Texture.class);
        assetManager.load("vehicules/truck3.png",Texture.class);
        assetManager.load("vehicules/truck4.png",Texture.class);
        assetManager.load("vehicules/truck5.png",Texture.class);
        assetManager.load("vehicules/truck6.png",Texture.class);
        assetManager.load("vehicules/truck7.png",Texture.class);
        assetManager.load("vehicules/truck8.png",Texture.class);
        assetManager.load("vehicules/truck9.png",Texture.class);
        assetManager.load("vehicules/truck10.png",Texture.class);
        assetManager.load("vehicules/truck11.png",Texture.class);
        assetManager.load("vehicules/truck12.png",Texture.class);
        assetManager.load("vehicules/truck13.png",Texture.class);
        assetManager.load("vehicules/truck14.png",Texture.class);
        assetManager.load("vehicules/truck15.png",Texture.class);
        assetManager.load("vehicules/truck16.png",Texture.class);
    }

    private void loadUtils(){
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    }

    public Array<TextureRegion> getTruckFrames(){
        Array<TextureRegion> truckFrames = new Array<TextureRegion>();
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck2.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck3.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck4.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck5.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck6.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck7.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck8.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck9.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck10.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck11.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck12.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck13.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck14.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck15.png",Texture.class)));
        truckFrames.add(new TextureRegion(assetManager.get("vehicules/truck16.png",Texture.class)));
        return truckFrames;
    }

    public Skin getSkin(){
        return skin;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
