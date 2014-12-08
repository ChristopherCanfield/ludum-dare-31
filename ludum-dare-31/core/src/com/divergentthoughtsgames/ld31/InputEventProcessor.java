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
