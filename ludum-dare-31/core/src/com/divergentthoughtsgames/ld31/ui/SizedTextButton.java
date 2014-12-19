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
