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
package com.divergentthoughtsgames.ld31.nav;

/**
 * A Search Heuristic that finds paths using Euclidean distance. This assumes that the least expensive
 * move has a cost of 1.
 * @author Christopher D. Canfield
 */
public class StraightLineHeuristic
{
	// The singleton 
	private static StraightLineHeuristic instance = new StraightLineHeuristic();
	
	/**
	 * Returns the pre-instantiated instance of the Straight Line Heuristic.
	 * @return the pre-instantiated instance of the Straight Line Heuristic.
	 */
	public static StraightLineHeuristic getInstance()
	{
		return instance;
	}
	
	
}
