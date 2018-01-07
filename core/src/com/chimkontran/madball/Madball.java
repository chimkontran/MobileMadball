package com.chimkontran.madball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chimkontran.madball.screens.PlayScreen;
import com.chimkontran.madball.system.InputTracker;

public class Madball extends Game {
	public static final int V_WIDTH = 450;
	public static final int V_HEIGHT = 250;
	public static final float PPM = 100; // Pixel per meter

	public static SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
