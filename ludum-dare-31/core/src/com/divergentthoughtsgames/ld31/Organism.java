package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Organism
{
	private Sprite sprite;
	private Animation anim;
	private float animTime;
	
	public Organism()
	{
		Array<TextureRegion> frames = new Array<TextureRegion>();
		frames.add(new TextureRegion(Textures.organism, 0, 0, 32, 32));
		frames.add(new TextureRegion(Textures.organism, 32, 0, 32, 32));
		frames.add(new TextureRegion(Textures.organism, 32*2, 0, 32, 32));
		frames.add(new TextureRegion(Textures.organism, 32*3, 0, 32, 32));
		frames.add(new TextureRegion(Textures.organism, 32*4, 0, 32, 32));
		frames.add(new TextureRegion(Textures.organism, 32*5, 0, 32, 32));
		
		anim = new Animation(.3f, frames);
		
		sprite = new Sprite(frames.get(0));
	}
	
	public void setX(float x)
	{
		sprite.setX(x);
	}
	
	public float getX()
	{
		return sprite.getX();
	}
	
	public void setY(float y)
	{
		sprite.setY(y);
	}
	
	public float getY()
	{
		return sprite.getY();
	}
	
	public Rectangle getBounds()
	{
		return sprite.getBoundingRectangle();
	}
	
	public void draw(Batch batch)
	{
		animTime += Gdx.graphics.getRawDeltaTime();
		sprite.setRegion(anim.getKeyFrame(animTime, true));
		sprite.draw(batch);
	}
	
	public void update()
	{
		
	}
	
	public void onClick(float x, float y)
	{
		if (sprite.getBoundingRectangle().contains(x, y))
		{
			Gdx.app.debug("Organism onClick", "Organism: " + sprite.getX() + "," + sprite.getY());
		}
	}
	
	public void onHover(float x, float y)
	{
		if (sprite.getBoundingRectangle().contains(x, y))
		{
			Gdx.app.debug("Organism onHover", "Organism: " + sprite.getX() + "," + sprite.getY());
		}
	}
}
