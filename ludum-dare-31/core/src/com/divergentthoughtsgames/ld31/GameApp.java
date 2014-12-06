package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class GameApp extends ApplicationAdapter {
	private FPSLogger fpsLogger;
	
	private SpriteBatch batch;
	private Texture img;
	
	private OrthographicCamera camera;
	
	private World world;
	
	@Override
	public void create () {
		fpsLogger = new FPSLogger();
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
//		world = new World();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
