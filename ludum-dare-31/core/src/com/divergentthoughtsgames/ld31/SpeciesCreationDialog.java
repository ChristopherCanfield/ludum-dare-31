package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
	
	private static final int SpeedUp = 6;
	private static final int SpeedDown = 7;
	
	private static final int AggressionUp = 8;
	private static final int AggressionDown = 9;
	
	private static final int ColorUp = 10;
	private static final int ColorDown = 11;
	
	private static final int OkButton = 12;
	private static final int CancelButton = 13;
	
	private final BitmapFont font;
	private final GameApp app;
	
	public SpeciesCreationDialog(final GameApp app, BitmapFont font)
	{
		super(Textures.createSpeciesDialog);
		setPosition(Screen.Zero.x + (1920/2) - getWidth()/2, Screen.Zero.y + (1080/2) - getHeight()/2);
		
		this.font = font;
		this.app = app;
		
		hitboxes = new Hitbox[14];
		hitboxes[TypeUp] = new Hitbox("TypeUp", getX() + 415, getY() + 432, 62, 24);
		hitboxes[TypeDown] = new Hitbox("TypeDown", getX() + 415, getY() + 405, 62, 24);
		hitboxes[DietUp] = new Hitbox("DietUp", getX() + 415, getY() + 372, 62, 24);
		hitboxes[DietDown] = new Hitbox("DietDown", getX() + 415, getY() + 345, 62, 24);
		hitboxes[LifespanUp] = new Hitbox("LifespanUp", getX() + 415, getY() + 310, 62, 24);
		hitboxes[LifespanDown] = new Hitbox("LifespanDown", getX() + 415, getY() + 284, 62, 24);
		hitboxes[SpeedUp] = new Hitbox("SpeedUp", getX() + 415, getY() + 249, 62, 24);
		hitboxes[SpeedDown] = new Hitbox("SpeedDown", getX() + 415, getY() + 222, 62, 24);
		hitboxes[AggressionUp] = new Hitbox("AggressionUp", getX() + 415, getY() + 187, 62, 24);
		hitboxes[AggressionDown] = new Hitbox("AggressionDown", getX() + 415, getY() + 161, 62, 24);
		hitboxes[ColorUp] = new Hitbox("LifespanUp", getX() + 415, getY() + 128, 62, 24);
		hitboxes[ColorDown] = new Hitbox("LifespanDown", getX() + 415, getY() + 101, 62, 24);
		
		hitboxes[OkButton] = new Hitbox("OkButton", getX() + 102, getY() + 19, 108, 54);
		hitboxes[CancelButton] = new Hitbox("CancelButton", getX() + 241, getY() + 19, 153, 54);
		hitboxes[CancelButton].setOnClickAction(new UserInputAction() {
			@Override public void onEvent(float x, float y) { app.showSpeciesCreationDialog = false; }
		});
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
	
	public void drawHitboxes(ShapeRenderer renderer)
	{
		Color originalColor = renderer.getColor();
		
		renderer.begin(ShapeType.Filled);
		renderer.setColor(new Color(0.38f, 0.38f, 0.38f, 0.65f));
		for (Hitbox hb : hitboxes)
		{
			renderer.rect(hb.rect.x, hb.rect.y, hb.rect.width, hb.rect.height);
		}
		renderer.end();
		
		renderer.setColor(originalColor);
	}
	
	public void onClick(float x, float y)
	{
		if (app.showSpeciesCreationDialog && getBoundingRectangle().contains(x, y))
		{
			for (Hitbox hb : hitboxes)
			{
				if (hb.contains(x, y))
				{
					hb.onClick(x, y);
				}
			}
		}
	}
	
	public void onHover(float x, float y)
	{
		if (app.showSpeciesCreationDialog && getBoundingRectangle().contains(x, y))
		{
			for (Hitbox hb : hitboxes)
			{
				if (hb.contains(x, y))
				{
					hb.onHover(x, y);
				}
			}
		}
	}
	
	
	private static class Hitbox
	{
		public final Rectangle rect;
		public final String name;
		
		private UserInputAction onClickAction;
		private UserInputAction onHoverAction;
		
		public Hitbox(String name, float x, float y, float width, float height)
		{
			this.name = name;
			this.rect = new Rectangle(x, y, width, height);
			this.onClickAction = NullUserInputAction.getInstance();
			this.onHoverAction = NullUserInputAction.getInstance();
		}
		
		public boolean contains(float x, float y)
		{
			return this.rect.contains(x, y);
		}
		
		public void setOnClickAction(UserInputAction action)
		{
			onClickAction = action;
		}
		
		public void setOnHoverAction(UserInputAction action)
		{
			onHoverAction = action;
		}
		
		public void onClick(float x, float y)
		{
			onClickAction.onEvent(x, y);
		}
		
		public void onHover(float x, float y)
		{
			onHoverAction.onEvent(x, y);
		}
	}
}
