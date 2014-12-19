package com.divergentthoughtsgames.ld31.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * A Scene2d text button that takes a width and height in its constructor. 
 * @author Christopher D. Canfield
 */
public class SizedTextButton extends TextButton
{
	private final float width;
	private final float height;

	public SizedTextButton(String text, Skin skin, float width, float height)
	{
		super(text, skin);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public float getPrefWidth()
	{
		return width;
	}
	
	@Override
	public float getMinWidth()
	{
		return width;
	}
	
	@Override
	public float getMaxWidth()
	{
		// No max width.
		return 0;
	}
	
	@Override
	public float getPrefHeight()
	{
		return height;
	}
	
	@Override
	public float getMinHeight()
	{
		return height;
	}
	
	@Override
	public float getMaxHeight()
	{
		// No max height.
		return 0;
	}
}
