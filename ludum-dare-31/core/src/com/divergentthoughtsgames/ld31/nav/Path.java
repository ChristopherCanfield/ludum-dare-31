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

import java.util.Queue;

/**
 * A navigation path.
 * @author Christopher D. Canfield
 */
public class Path
{
	// The list of nodes in the path.
	private Queue<Node> nodes;
	// The target node.
	private Node targetNode;
	
	/**
	 * Constructs a navigation path.
	 * @param nodes the list of nodes in the path.
	 * @param targetNode the target node.
	 */
	public Path(Queue<Node> nodes, Node targetNode)
	{
		this.nodes = nodes;
		this.targetNode = targetNode;
	}
	
	/**
	 * Gets an ordered list of the nodes in the path.
	 * @return the list of nodes in the path.
	 */
	public Queue<Node> getNodes()
	{
		return nodes;
	}
	
	/**
	 * Gets the target node.
	 * @return the target node.
	 */
	public Node getTargetNode()
	{
		return targetNode;
	}
}
