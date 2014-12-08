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
