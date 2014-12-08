package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class InputEventProcessor extends InputAdapter
{
	private Camera camera;
	
	public InputEventProcessor(Camera camera)
	{
		this.camera = camera;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		Vector3 clickWU = camera.unproject(new Vector3(screenX, screenY, 0));
		
		for (Organism o : GameWorld.organisms)
		{
			o.onClick(clickWU.x, clickWU.y);
		}
		
		GameWorld.isPassable(clickWU.x, clickWU.y);
			
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		Vector3 clickWU = camera.unproject(new Vector3(screenX, screenY, 0));
		
		for (Organism o : GameWorld.organisms)
		{
			o.onHover(clickWU.x, clickWU.y);
		}
		
		return false;
	}
}
