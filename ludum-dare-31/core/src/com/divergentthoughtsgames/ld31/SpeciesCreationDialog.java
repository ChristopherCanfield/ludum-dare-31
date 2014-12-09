package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SpeciesCreationDialog extends Sprite
{
	private Hitbox[] hitboxes;
	
	private static final int TypeUp = 0;
	private static final int TypeDown = 1;
	
	private static final int DietUp = 2;
	private static final int DietDown = 3;
	
	private static final int LifespanUp = 4;
	private static final int LifespanDown = 5;
	
	private final BitmapFont font;
	private final GameApp app;
	
	public SpeciesCreationDialog(GameApp app, BitmapFont font)
	{
		super(Textures.createSpeciesDialog);
		setPosition(Screen.Zero.x + (1920/2) - getWidth()/2, Screen.Zero.y + (1080/2) - getHeight()/2);
		
		this.font = font;
		this.app = app;
		
		hitboxes = new Hitbox[8];
		hitboxes[TypeUp] = new Hitbox("TypeUp", 1, 7, 113, 28);
		hitboxes[TypeDown] = new Hitbox("TypeDown", 1, 39, 113, 28);
		hitboxes[DietUp] = new Hitbox("DietUp", 1, 71, 133, 36);
		hitboxes[DietDown] = new Hitbox("DietDown", 1, 104, 133, 36);
		hitboxes[LifespanUp] = new Hitbox("LifespanUp", 1, 139, 133, 36);
		hitboxes[LifespanDown] = new Hitbox("LifespanDown", 1, 1, 1, 1);
	}
	
	@Override
	public void draw(Batch batch)
	{
		super.draw(batch);
		
		font.setColor(Color.GREEN);
		
		// Animal type.
		font.draw(batch, "Animal", getX() + 250, getY() + 450);
		// Diet.
		font.draw(batch, "Animals", getX() + 250, getY() + 390);
		// Lifespan.
		font.draw(batch, "High", getX() + 250, getY() + 328);
		// Speed.
		font.draw(batch, "Animal", getX() + 250, getY() + 267);
		// Aggression.
		font.draw(batch, "Animal", getX() + 250, getY() + 205);
		// Color.
		font.draw(batch, "Animal", getX() + 250, getY() + 142);
	}
	
	public void onClick(float x, float y)
	{
		if (app.showSpeciesCreationDialog && getBoundingRectangle().contains(x, y))
		{
			Gdx.app.debug("Dialog onClick", "Dialog: " + getX() + "," + getY());
		}
	}
	
	public void onHover(float x, float y)
	{
		if (app.showSpeciesCreationDialog && getBoundingRectangle().contains(x, y))
		{
			Gdx.app.debug("Dialog onHover", "Dialog: " + getX() + "," + getY());
			
			for (Hitbox hb : hitboxes)
			{
				if (hb.contains(x, y))
				{
					System.out.println(hb.name);
				}
			}
		}
	}
	
	
	private static class Hitbox
	{
		private Rectangle rect;
		String name;
		
		public Hitbox(String name, float x, float y, float width, float height)
		{
			this.name = name;
			rect = new Rectangle(x, y, width, height);
		}
		
		public boolean contains(float x, float y)
		{
			return rect.contains(x, y);
		}
	}
}
