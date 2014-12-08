package com.divergentthoughtsgames.ld31;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private World world;
	private RayHandler rayHandler;
	
	@Override
	public void create () {
		fpsLogger = new FPSLogger();
		
		batch = new SpriteBatch();
		Textures.load();
		
		camera = new OrthographicCamera();
		viewport = new StretchViewport(1920, 1080, camera);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		Screen.Zero = new Vector2(-viewport.getWorldWidth() / 2, -viewport.getWorldHeight() / 2);
		
		System.out.println(viewport.getWorldWidth());
		System.out.println(viewport.getWorldHeight());
		
		world = new World(new Vector2(1920, 1080), true);
		
		rayHandler = new RayHandler(world);
		rayHandler.setCombinedMatrix(camera.combined);
		
	    rayHandler.setAmbientLight(1f, 1f, 1f, 0.05f);
	    
	    new PointLight(rayHandler, 100, new Color(Color.rgba8888(1, 1, 1, 0.75f)), 1000f, 200, 200);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		rayHandler.setCombinedMatrix(camera.combined);
		
		batch.begin();
		batch.draw(Textures.map, Screen.Zero.x, Screen.Zero.y);
		batch.end();
		
		rayHandler.updateAndRender();
		
		world.step(1/60f, 6, 2);
		
		fpsLogger.log();
	}
	
	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}
}
