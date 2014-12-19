package com.divergentthoughtsgames.ld31.ui;

import javafx.scene.control.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class StartMenuUi extends UiScreen
{
	public StartMenuUi()
	{
		super(new Color(0.5f, 0.5f, 0.5f, 1.f));
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
		
		addTitle(skin);
		
		table.row();
		
//		VerticalGroup topScoreGroup = new VerticalGroup();
		Table topScoreGroup = new Table(skin);
		table.add(topScoreGroup).align(Align.left);
		addTopScores(skin, topScoreGroup);
		
		VerticalGroup buttonGroup = new VerticalGroup();
		buttonGroup.setWidth(600);
		table.add(buttonGroup).align(Align.right);
		addButtons(skin, buttonGroup);
	}
	
	private void addTitle(Skin skin)
	{
		table.row().colspan(2).align(Align.center);
		Label gameTitle = new Label("Game Title Placeholder", skin);
		gameTitle.setColor(Color.WHITE);
		gameTitle.setFontScale(1.5f);
		table.add(gameTitle).expandX().padBottom(100f);
	}
	
	private static void addButtons(Skin skin, VerticalGroup group)
	{
//		table.row().align(Align.center);
		
		TextButton singlePlayerBtn = new WidthTextButton("Single Player", skin, 400);
		group.addActor(singlePlayerBtn);
		
		TextButton multiplayerBtn = new WidthTextButton("Multiplayer", skin, 400);
		group.addActor(multiplayerBtn);
		
		TextButton settingsBtn = new WidthTextButton("Settings", skin, 400);
		group.addActor(settingsBtn);
		
		TextButton exitBtn = new WidthTextButton("Exit", skin, 400);
		group.addActor(exitBtn);
	}

	private static void addTopScores(Skin skin, Table group)
	{
		group.row().colspan(2);
		Label topScoreLabel = new Label("Top Scores", skin);
		topScoreLabel.setFontScale(1.1f);
		group.add(topScoreLabel);
		
		group.row();
		
		Label topScoreNames = new Label("", skin);
		topScoreNames.setWrap(true);
		topScoreNames.setText("Christopher D. Canfield\nTest player\nAnother test player");
		group.add(topScoreNames);
		
		Label topScores = new Label("", skin);
		topScores.setWrap(true);
		topScores.setText("500\n425\n397");
		group.add(topScores);
	}
	
	@Override
	protected void onUpdate()
	{
	}


	@Override
	public void dispose()
	{
	}
	
	private static class WidthTextButton extends TextButton
	{
		private final float width;

		public WidthTextButton(String text, Skin skin, float width)
		{
			super(text, skin);
			this.width = width;
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
				
	}
}
