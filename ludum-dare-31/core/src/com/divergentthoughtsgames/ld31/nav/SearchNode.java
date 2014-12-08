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
 * Wraps a maze node with additional information required when searching
 * for a path.
 * @author Christopher D. Canfield
 */
public class SearchNode extends Node implements Comparable<SearchNode>
{
	// The parent to this node in the path.
	private final SearchNode parent;
	
	// The node's cumulative cost from the start node to this node.
	private final double g;
	// The node's heuristic cost to the end node.
	private final double h;
	
	/**
	 * Wraps a Node with additional information needed during the A* search.
	 * @param underlyingNode The node that will be wrapped.
	 * @param parent The node that lead to this node.
	 * @param g The node's cumulative cost from the start node to this node.
	 * @param h The node's heuristic cost to the end node.
	 */
	public SearchNode(Node underlyingNode, SearchNode parent, double g, double h)
	{
		super(underlyingNode.getX(), underlyingNode.getY());
		this.parent = parent;
		this.g = g;
		this.h = h;
		this.edges = underlyingNode.edges;
	}
	
	/**
	 * Returns the parent of this node in the path.
	 * @return This search node's parent.
	 */
	public SearchNode getParent()
	{
		return parent;
	}
	
	/**
	 * Returns the cumulative cost from the start node to this node.
	 * @return The cumulative cost from the start node to this node.
	 */
	public double getG()
	{
		return g;
	}
	
	/**
	 * Returns the heuristic cost from the end node to this node.
	 * @return The heuristic cost from the end node to this node.
	 */
	public double getH()
	{
		return h;
	}
	
	/**
	 * The compareTo method is required for the A* algorithm's priority queue.
	 */
	@Override
	public int compareTo(SearchNode other)
	{
		double cost = g + h;
		double otherCost = other.g + other.h;
		return (cost < otherCost) ? -1 : 
				(cost > otherCost) ? 1 : 0;
	}
}
