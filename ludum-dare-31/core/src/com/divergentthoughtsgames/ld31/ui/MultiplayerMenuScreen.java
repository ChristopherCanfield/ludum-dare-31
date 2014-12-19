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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.divergentthoughtsgames.ld31.GameApp;

public class MultiplayerMenuScreen extends UiScreen
{
	public MultiplayerMenuScreen(GameApp app)
	{
		super(app, new Color(0.5f, 0.5f, 0.5f, 1.f));
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
		
		table.setFillParent(true);
		
		table.padLeft(20);
		table.padRight(20);
		
		addTitle(skin);
		addNameField(skin);
		addCreateGameButton(skin);
		addJoinGameButton(skin);
	}
	
	private void addTitle(Skin skin)
	{
		table.row().colspan(2);
		Label title = new Label("Multiplayer Game", skin);
		title.setColor(Color.WHITE);
		title.setFontScale(1.5f);
		table.add(title).expandX().padBottom(100f);
	}
	
	private void addNameField(Skin skin)
	{
		table.row();
		
		Label nameLabel = new Label("Name:", skin);
		nameLabel.setFontScale(1.0f);
		table.add(nameLabel).padBottom(30);
		
		TextField nameField = new TextField("", skin);
		table.add(nameField);
	}
	
	private void addCreateGameButton(Skin skin)
	{
		
	}
	
	private void addJoinGameButton(Skin skin)
	{
		
	}
	
	@Override
	protected void onUpdate()
	{
	}


	@Override
	public void dispose()
	{
	}
	
	
}
