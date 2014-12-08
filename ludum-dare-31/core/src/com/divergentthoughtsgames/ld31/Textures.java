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

import com.badlogic.gdx.graphics.Texture;

public class Textures
{
	public static Texture map;
	
	public static Texture organism;
	
	public static Texture createSpeciesDialog;
	
	public static Texture createSpeciesDialogText;
	
	public static void load()
	{
		map = new Texture("map.png");
		organism = new Texture("organism.png");
		createSpeciesDialog = new Texture("create-species-dialog.png");
		createSpeciesDialogText = new Texture("dialog-text.png");
	}
}
