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
 * An edge between nodes in the navigation graph.
 * @author Christopher D. Canfield
 */
public class Edge
{
	/** The cost to travel over this edge. **/
	public double cost;
	
	private Node node1;
	private Node node2;
	
	private boolean passable;

	/**
	 * Constructs an edge with the specified cost.
	 * @param cost the cost to travel over the edge.
	 */
	public Edge(double cost)
	{
		this(null, null, cost);
	}
	
	/**
	 * Constructs an edge between the two specified nodes, with the stated cost.
	 * @param node1 the first node that this edge connects to.
	 * @param node2 the second node that this edge connects to.
	 * @param cost the cost to travel over the edge.
	 */
	public Edge(Node node1, Node node2, double cost)
	{
		this.cost = cost;
		this.node1 = node1;
		this.node2 = node2;
		
		if (node1 != null)
		{
			this.node1.addEdge(this);
		}
		if (node2 != null)
		{
			this.node2.addEdge(this);
		}
		
		passable = false;
	}
	
	/**
	 * Connects a node to this edge. Calls node.addEdge on the node to add this edge to
	 * the node as well.
	 * @param node the node to connect to this edge.
	 * @return reference to this edge.
	 */
	public Edge addNode(Node node)
	{
		if (node.equals(node1) || node.equals(node2))
		{
			return this;
		}
		
		if (node1 == null)
		{
			node1 = node;
			node1.addEdge(this);
		}
		else if (node2 == null)
		{
			node2 = node;
			node2.addEdge(this);
		}
		else
		{
			throw new RuntimeException("Edge already has two connections.");
		}
		
		return this;
	}
	
	/**
	 * Returns the node opposite the provided node.
	 * @param node the node that the other node will be checked against.
	 * @return the node opposite the provided node.
	 * @throws RuntimeException if the provided node is not connected to this edge.
	 */
	public Node getOppositeNode(Node node)
	{
		if (node.equals(node1))
		{
			return node2;
		}
		else if (node.equals(node2))
		{
			return node1;
		}
		else
		{
			throw new RuntimeException("Node is not connected to this edge.");
		}
	}
	
	/**
	 * Sets whether this edge is passable
	 * @param val true if this edge is passable, or false otherwise.
	 */
	public void setPassable(boolean val)
	{
		passable = val;
	}
	
	/**
	 * Specifies whether this edge is passable.
	 * @return true if this edge is passable, or false otherwise.
	 */
	public boolean isPassable()
	{
		return passable;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[Edge: " )
				.append((node1 != null) ? node1.toString() : "")
				.append((node2 != null) ? node2.toString() : "")
				.append("]");
		return sb.toString();
	}
}
