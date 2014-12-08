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
package com.divergentthoughtsgames.ld31;

import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.ld31.nav.Node;
import com.divergentthoughtsgames.ld31.nav.Search;

public class Organism
{
	private Sprite sprite;
	private Animation anim;
	private float animTime;
	
	private Node target;
	private Queue<Node> path;
	
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
	
	public Organism setX(float x)
	{
		sprite.setX(x);
		return this;
	}
	
	public float getX()
	{
		return sprite.getX();
	}
	
	public Organism setY(float y)
	{
		sprite.setY(y);
		return this;
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
		if (target == null)
		{
			setTarget();
		}
	}
	
	public void drawPath(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.begin(ShapeType.Filled);
		for (Node node : path)
		{
			shapeRenderer.setColor(Color.YELLOW);
			node.draw(shapeRenderer);
		}
		shapeRenderer.end();
	}
	
	private Node currentNode()
	{
		return GameWorld.nodes[(int)((sprite.getX() - Screen.Zero.x) / Node.Size)][(int)((sprite.getY() - Screen.Zero.y) / Node.Size)];
	}
	
	private void setTarget()
	{
		target = GameWorld.nodes[50][30];
		path = Search.aStar(currentNode(), target);
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
