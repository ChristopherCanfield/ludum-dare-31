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
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.divergentthoughtsgames.ld31.GameApp;

public class MultiplayerMenuScreen extends UiScreen
{
	public MultiplayerMenuScreen(GameApp app)
	{
		super(app, new Color(0.5f, 0.5f, 0.5f, 1.f));
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
		
		rootTable.setFillParent(true);
		
		rootTable.padLeft(20);
		rootTable.padRight(20);
		
		addTitle(skin);
		addNameField(skin);
		addCreateGameButton(skin);
		addJoinGameSection(skin);
	}
	
	private void addTitle(Skin skin)
	{
		rootTable.row().colspan(2);
		Label title = new Label("Multiplayer Game", skin);
		title.setColor(Color.WHITE);
		title.setFontScale(1.5f);
		rootTable.add(title).expandX().padBottom(100f);
	}
	
	private void addNameField(Skin skin)
	{
		rootTable.row().align(Align.top);
		
		Label nameLabel = new Label("Name:", skin);
		nameLabel.setFontScale(1.0f);
		rootTable.add(nameLabel).padBottom(30);
		
		TextField nameField = new TextField("", skin);
		rootTable.add(nameField);
	}
	
	private void addCreateGameButton(Skin skin)
	{
		rootTable.row();
		
		Tree tree = new Tree(skin);
		Node node1 = new Node(new Label("Test 1", skin));
		tree.add(node1);
		Node node2 = new Node(new Label("Test 2", skin));
		tree.add(node2);
		
		node1.add(new Node(new Label("Test Test Test 1", skin)));
		node1.add(new Node(new Label("Test Test 2 2", skin)));
		
		node2.add(new Node(new Label("321 34123 2", skin)));
		node2.add(new Node(new Label("Test 2 2 2", skin)));
		
		rootTable.add(tree);
	}
	
	private void addJoinGameSection(Skin skin)
	{
		rootTable.row();
		
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
