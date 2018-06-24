package com.draw.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.draw.game.manager.MyAssetManager;
import com.draw.game.screen.SplashScreen;

public class MyGdxGame extends Game {

	private boolean devMode;
	private MyAssetManager assetManager;

	public MyGdxGame(boolean devMode){
		this.devMode=devMode;
	}

	@Override
	public void create () {
		assetManager = new MyAssetManager(this);

		if(devMode) {
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		} else {
			Gdx.app.setLogLevel(Application.LOG_ERROR);
		}
		setScreen(new SplashScreen(this,devMode));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		assetManager.dispose();
	}

	public MyAssetManager getAssetManager() {
		return assetManager;
	}
}
