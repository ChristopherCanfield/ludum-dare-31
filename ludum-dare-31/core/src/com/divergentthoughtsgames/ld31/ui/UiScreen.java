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
package com.divergentthoughtsgames.ld31.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.divergentthoughtsgames.ld31.GameApp;

/**
 * Base class for ui screens that are drawn in the LWJGL window.
 * 
 * @author Christopher D. Canfield
 */
public abstract class UiScreen
{
	/** The scene2d.ui stage. **/
	protected final Stage stage;
	
	/** The base table for the screen. **/
	protected final Table rootTable;
	
	protected final Color color;
	
	protected final GameApp app;
	
	/**
	 * Constructs a user interface screen.
	 * @param app reference to the GameApp instance.
	 * @param color the screen's clear color.
	 */
	protected UiScreen(GameApp app, Color color)
	{
		this.app = app;
		this.color = color;
		
		stage = new Stage();
		rootTable = new Table();

		rootTable.setFillParent(true);
		rootTable.top();
	    stage.addActor(rootTable);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	public void clear()
	{
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Updates and draws the screen.
	 */
	public final void update()
	{
		update(false);
	}
	
	/**
	 * Updates and draws the screen.
	 * @param debug true if the screen should be drawn in debug mode.
	 */
	public final void update(boolean debug)
	{
		rootTable.setDebug(debug, true);
		
		stage.act();
		onUpdate();
		stage.draw();
	}
	
	/**
	 * Called once per tick. Child classes can override this if necessary.
	 */
	protected void onUpdate()
	{
	}
	
	/**
	 * Releases all heavy-weight resources.
	 */
	public abstract void dispose();
}
