package com.draw.game;

import com.badlogic.gdx.Game;
import com.draw.game.screen.MainScreen;
import com.draw.game.screen.SplashScreen;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		setScreen( new MainScreen() );
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
