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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.divergentthoughtsgames.ld31.GameApp;

public class StartMenuScreen extends UiScreen
{
	public StartMenuScreen(GameApp app)
	{
		super(app, new Color(0.5f, 0.5f, 0.5f, 1.f));
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
		
		addTitle(skin);
		
		rootTable.setFillParent(true);
		
		rootTable.padLeft(20);
		rootTable.padRight(20);
		
		rootTable.row();
		
		Table topScoreGroup = new Table(skin);
		rootTable.add(topScoreGroup).align(Align.left + Align.top).width(500);
		addTopScores(skin, topScoreGroup);
		
		Table buttonGroup = new Table(skin);
		buttonGroup.setWidth(600);
		rootTable.add(buttonGroup).align(Align.right + Align.top);
		addButtons(skin, buttonGroup);
	}
	
	private void addTitle(Skin skin)
	{
		rootTable.row().colspan(2);
		Label gameTitle = new Label("Game Title Placeholder", skin);
		gameTitle.setColor(Color.WHITE);
		gameTitle.setFontScale(1.5f);
		rootTable.add(gameTitle).expandX().padBottom(100f);
	}
	
	private static void addTopScores(Skin skin, Table group)
	{
		group.row().colspan(2).align(Align.center);
		Label topScoreLabel = new Label("Top Scores", skin);
		topScoreLabel.setFontScale(1.1f);
		group.add(topScoreLabel).expandX().padBottom(30);
		
		group.row().align(Align.left);
		
		Label topScoreNames = new Label("", skin);
		topScoreNames.setWrap(true);
		topScoreNames.setText("Christopher D. Canfield\nTest player\nAnother test player");
		group.add(topScoreNames);
		
		Label topScores = new Label("", skin);
		topScores.setWrap(true);
		topScores.setText("500\n425\n397");
		group.add(topScores);
	}
	
	private void addButtons(Skin skin, Table group)
	{
		TextButton singlePlayerBtn = new SizedTextButton("Single Player", skin, 400, 50);
		group.add(singlePlayerBtn).padBottom(20);
		
		group.row();
		
		TextButton multiplayerBtn = new SizedTextButton("Multiplayer", skin, 400, 50);
		multiplayerBtn.addListener(new ClickListener() {
			@Override public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				app.setScreen(new MultiplayerMenuScreen(app));
			}
		});
		group.add(multiplayerBtn).padBottom(20);
		
		group.row();
		
		TextButton settingsBtn = new SizedTextButton("Settings", skin, 400, 50);
		group.add(settingsBtn).padBottom(20);
		
		group.row();
		
		TextButton exitBtn = new SizedTextButton("Exit", skin, 400, 50);
		group.add(exitBtn);
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
