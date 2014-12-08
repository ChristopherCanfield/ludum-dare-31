package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.graphics.Texture;

public class Textures
{
	public static Texture map;
	
	public static Texture organism;
	
	public static void load()
	{
		map = new Texture("map.png");
		organism = new Texture("organism.png");
	}
}
