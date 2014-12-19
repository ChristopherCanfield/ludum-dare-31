/*
  Copyright 2014 Christopher D. Canfield
 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.divergentthoughtsgames.ld31;

import java.util.ArrayList;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.divergentthoughtsgames.ld31.ui.StartMenuScreen;
import com.divergentthoughtsgames.ld31.ui.UiScreen;

public class GameApp extends ApplicationAdapter {
	private FPSLogger fpsLogger;
	
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private RayHandler rayHandler;
	
	private AppState appState;
	
	public boolean showPath;
	public boolean showNodes;
	public boolean logFramesPerSecond;
	public boolean showSpeciesCreationDialog;
	
	private BitmapFont font;
	
	SpeciesCreationDialog speciesCreationDialog;
	
	private UiScreen ui;
	
	/**
	 * Sets the UI screen.
	 * @param screen UI screen. 
	 */
	public void setScreen(UiScreen screen)
	{
		ui.dispose();
		this.ui = screen;
	}
	
	@Override
	public void create () {
		fpsLogger = new FPSLogger();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		appState = AppState.NewGameMenu;
		
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		Textures.load();
		
		camera = new OrthographicCamera();
		viewport = new StretchViewport(1920, 1080, camera);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		Screen.Zero = new Vector2(-viewport.getWorldWidth() / 2, -viewport.getWorldHeight() / 2);
		
		GameWorld.initialize();
		
		Gdx.input.setInputProcessor(new InputEventProcessor(this, camera));
		
		GameWorld.physicsWorld = new World(new Vector2(1920, 1080), true);
		
		rayHandler = new RayHandler(GameWorld.physicsWorld);
		rayHandler.setCombinedMatrix(camera.combined);
		
	    rayHandler.setAmbientLight(1f, 1f, 1f, 0.05f);
	    
	    new PointLight(rayHandler, 100, new Color(Color.rgba8888(1, 1, 1, 0.75f)), 1000f, 200, 200);
	    
	    GameWorld.organisms = new ArrayList<Organism>();
	    GameWorld.organisms.add(new Organism());
	    GameWorld.organisms.get(0).setX(Screen.Zero.x + 100).setY(Screen.Zero.y + 300);
	    
	    FileHandle fntFile = Gdx.files.internal("arial_36_bold.fnt");
	    FileHandle pngFile = Gdx.files.internal("arial_36_bold.png");
	    font = new BitmapFont(fntFile, pngFile, false);
	    
	    speciesCreationDialog = new SpeciesCreationDialog(this, font);
	    
	    ui = new StartMenuScreen(this);
	}

	@Override
	public void render () {
		if (appState == AppState.NewGameMenu)
		{
			ui.clear();
			ui.update(Gdx.input.isKeyPressed(Keys.NUM_1));
		}
		else
		{
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
			if (!showSpeciesCreationDialog)
			{
				for (Organism o : GameWorld.organisms)
				{
					o.update();
				}
			}
			
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			rayHandler.setCombinedMatrix(camera.combined);
			
			drawMap();
			drawOrganisms();
			
			shapeRenderer.setProjectionMatrix(camera.combined);
			
			drawNodes();
			drawPaths();
			
			if (appState == AppState.Active)
			{
				rayHandler.updateAndRender();
			}
			
			drawUi();
			
			GameWorld.physicsWorld.step(1/60f, 6, 2);
		
			if (logFramesPerSecond) fpsLogger.log();
		}
	}
	
	private void drawMap()
	{
		batch.begin();
		batch.draw(Textures.map, Screen.Zero.x, Screen.Zero.y);
		batch.end();
	}
	
	private void drawOrganisms()
	{
		batch.begin();
		for (Organism o : GameWorld.organisms)
		{
			o.draw(batch);
		}
		batch.end();
	}
	
	private void drawNodes()
	{
		if (showNodes)
		{
			GameWorld.drawNodes(shapeRenderer);
		}
	}
	
	private void drawPaths()
	{
		if (showPath)
		{
			for (Organism o : GameWorld.organisms)
			{
				o.drawPath(shapeRenderer);
			}
		}
	}
	
	private void drawUi()
	{
		batch.begin();
		
		font.setColor(Color.WHITE);
		font.draw(batch, "Organisms: " + GameWorld.organisms.size(), Screen.Zero.x + 20, Screen.Zero.y + 1060);
		if (showSpeciesCreationDialog)
		{
			speciesCreationDialog.draw(batch);
			speciesCreationDialog.drawHitboxes(shapeRenderer);
		}
		else
		{
			batch.end();
		}
		
	}
	
	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}
}
