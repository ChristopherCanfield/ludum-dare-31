package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameApp extends ApplicationAdapter {
	private FPSLogger fpsLogger;
	
	private SpriteBatch batch;
	private Sprite imgSprite;
	private Texture img;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private World world;
	
	@Override
	public void create () {
		fpsLogger = new FPSLogger();
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		imgSprite = new Sprite(img);
		
		camera = new OrthographicCamera();
		viewport = new StretchViewport(1920, 1080, camera);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		Screen.Zero = new Vector2(-viewport.getWorldWidth() / 2, -viewport.getWorldHeight() / 2);
		imgSprite.setPosition(Screen.Zero.x, Screen.Zero.y);
		
		System.out.println(viewport.getWorldWidth());
		System.out.println(viewport.getWorldHeight());
		
//		world = new World();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		
		
		batch.begin();
		imgSprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}
}
