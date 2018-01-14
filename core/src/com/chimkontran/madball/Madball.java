package com.chimkontran.madball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chimkontran.madball.screens.PlayScreen;
import com.chimkontran.madball.system.InputTracker;

public class Madball extends Game {
	public static final int V_WIDTH = 450;
	public static final int V_HEIGHT = 250;
	public static final float PPM = 100; // Pixel per meter

	public static final short GROUND_BIT = 1;
	public static final short BALL_BIT = 2;
	public static final short GOLD_BIT = 4;
	public static final short DESTROYED_BIT = 8;
	public static final short ENEMY_BIT = 16;
	public static final short OBJECT_BIT = 32;
//	public static final short BULLET_BIT = 16;


	public static SpriteBatch batch;

	public static AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/ball_walk.wav", Sound.class);
		manager.load("audio/ball_shoot.wav", Sound.class);
		manager.finishLoading();

		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
