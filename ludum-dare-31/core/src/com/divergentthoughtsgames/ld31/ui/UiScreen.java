package com.divergentthoughtsgames.ld31.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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
	protected final Table table;
	
	protected final Color color;
	
	/**
	 * Default constructor.
	 */
	protected UiScreen(Color color)
	{
		this.color = color;
		
		stage = new Stage();
		table = new Table();

		table.setFillParent(true);
		table.top();
	    stage.addActor(table);
		
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
		table.setDebug(debug, true);
		
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
