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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.divergentthoughtsgames.ld31.GameApp;

public class TestUi extends UiScreen
{
	private Label messageHistory;

	public TestUi(GameApp app)
	{
		super(app, new Color(0, 0, 0, 0));
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);

		createMessageHistoryBox(skin);
	}

	private void createMessageHistoryBox(Skin skin)
	{
		table.row().colspan(3)
				.width(Gdx.graphics.getWidth() - 20.f)
				.height(Gdx.graphics.getHeight() - 100.f);

		messageHistory = new Label("", skin);
		messageHistory.setWrap(true);
		messageHistory.setAlignment(Align.top + Align.left);

		ScrollPane scrollpane = new ScrollPane(messageHistory, skin);
		table.add(scrollpane).expand();
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
