package com.divergentthoughtsgames.ld31;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld
{
	public static World physicsWorld;
	
	public static List<Organism> organisms;
	
	private static BufferedImage collisionMap;
	
	public static void initialize()
	{
		try {
			collisionMap = ImageIO.read(new File("collision-map.png"));
		} catch (IOException e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static boolean isPassable(float x, float y)
	{
		return isPassable((int)x, (int)y);
	}
	
	public static boolean isPassable(int x, int y)
	{
		int adjustedX = Math.round(x - Screen.Zero.x);
		int adjustedY = Math.round(collisionMap.getHeight() - (y - Screen.Zero.y));
		
		return collisionMap.getRGB(adjustedX, adjustedY) != -16777216;
	}
	
	private static void createNavMap()
	{
		
	}
}
