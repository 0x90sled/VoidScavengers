package com.mikefdorst.voidscavengers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikefdorst.voidscavengers.screen.MainMenuScreen;

public class VoidScavengers extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
	}
}
