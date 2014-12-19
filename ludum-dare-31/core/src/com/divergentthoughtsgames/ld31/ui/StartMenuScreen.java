package com.divergentthoughtsgames.ld31.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class StartMenuScreen extends UiScreen
{
	public StartMenuScreen()
	{
		super(new Color(0.5f, 0.5f, 0.5f, 1.f));
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
		
		addTitle(skin);
		
		table.setFillParent(true);
		
		table.padLeft(20);
		table.padRight(20);
		
		table.row();
		
		Table topScoreGroup = new Table(skin);
		table.add(topScoreGroup).align(Align.left + Align.top).width(500);
		addTopScores(skin, topScoreGroup);
		
		Table buttonGroup = new Table(skin);
		buttonGroup.setWidth(600);
		table.add(buttonGroup).align(Align.right + Align.top);
		addButtons(skin, buttonGroup);
	}
	
	private void addTitle(Skin skin)
	{
		table.row().colspan(2);
		Label gameTitle = new Label("Game Title Placeholder", skin);
		gameTitle.setColor(Color.WHITE);
		gameTitle.setFontScale(1.5f);
		table.add(gameTitle).expandX().padBottom(100f);
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
	
	private static void addButtons(Skin skin, Table group)
	{
		TextButton singlePlayerBtn = new SizedTextButton("Single Player", skin, 400, 50);
		group.add(singlePlayerBtn).padBottom(20);
		
		group.row();
		
		TextButton multiplayerBtn = new SizedTextButton("Multiplayer", skin, 400, 50);
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
