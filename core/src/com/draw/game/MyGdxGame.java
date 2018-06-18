package com.draw.game;

import com.badlogic.gdx.Game;
import com.draw.game.screen.MainScreen;
import com.draw.game.screen.SplashScreen;

public class MyGdxGame extends Game {

	private boolean devMode;

	public MyGdxGame(boolean devMode){
		this.devMode=devMode;
	}
	@Override
	public void create () {

		if (devMode) {
			setScreen(new MainScreen());
		} else {
			setScreen(new SplashScreen(this));
		}
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
