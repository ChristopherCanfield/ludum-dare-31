package com.divergentthoughtsgames.ld31.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A node in the navigation graph.
 * @author Christopher D. Canfield
 */
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
public class Node
{
	/** The nav graph node size. **/
	public static int Size = 12;
	
	/** The nav graph node half size. **/
	public static int HalfSize = Size / 2;
	
	/** Edges that are connected to this Node. **/
	protected ArrayList<Edge> edges = new ArrayList<Edge>(8);
	
	// The node's bounding box.
	private final Rectangle bounds;
	
	// Whether this node is passable by entities.
	private boolean passable;
	
	/**
	 * Constructs a navigation graph node.
	 * @param leftX the left point of the node.
	 * @param bottomY the bottom point of the node.
	 */
	public Node(int leftX, int bottomY)
	{
		this.bounds = new Rectangle(leftX, bottomY, Size, Size);
		this.passable = false;
	}
	
	/**
	 * Adds the specified edge, if it is not already connected to this node. 
	 * Also calls edge.addNode to add this edge to the node, if it doesn't already exist.
	 * @param edge the edge to connect to this node.
	 * @return reference to this node.
	 */
	public Node addEdge(Edge edge)
	{
		if (!edges.contains(edge))
		{
			edges.add(edge);
			edge.addNode(this);
		}
		return this;
	}
	
	/**
	 * Gets the list of edges attached to this node.
	 * @return the list of edges attached to this node.
	 */
	public List<Edge> getEdges()
	{
		return edges;
	}
	
	/**
	 * Returns the left x value of the node.
	 * @return the left x value of the node.
	 */
	public int getX()
	{
		return (int)bounds.x;
	}
	
	/**
	 * Returns the bottom y value of the node.
	 * @return the bottom y value of the node.
	 */
	public int getY()
	{
		return (int)bounds.y;
	}
	
	/**
	 * Returns the center x value of the node.
	 * @return the center x value of the node.
	 */
	public int getCenterX()
	{
		return (int)(bounds.x + HalfSize);
	}
	
	/**
	 * Returns the center y value of the node.
	 * @return the center y value of the node.
	 */
	public int getCenterY()
	{
		return (int)(bounds.y + HalfSize);
	}
	
	/**
	 * Whether this node overlaps the specified rectangle
	 * @param rect the rectangle to check for overlap with this node.
	 * @return true if the rectangle and the node overlap, or false otherwise.
	 */
	public boolean overlaps(Rectangle rect)
	{
		return Intersector.overlaps(bounds, rect);
	}
	
	/**
	 * Gets the node's row index.
	 * @return the node's row index.
	 */
	public int getRowIndex()
	{
		return (int)(bounds.y / Size);
	}
	
	/**
	 * Gets the node's column index.
	 * @return the node's column index.
	 */
	public int getColumnIndex()
	{
		return (int)(bounds.x / Size);
	}
	
	/**
	 * Sets this node, and all connected edges, to passable (if true) or impassable (if false).
	 * @param val true if the node is passable, or false otherwise.
	 */
	public void setPassable(boolean val)
	{
		if (val != passable)
		{
			for (final Edge edge : edges)
			{
				edge.setPassable(val);
			}
			passable = val;
		}
	}
	
	/**
	 * Whether this node and its connected edges are passable.
	 * @return true if this node is connected, or false otherwise.
	 */
	public boolean isPassable()
	{
		return passable;
	}
	
	/**
	 * Gets a random point within the node's bounds.
	 * @return a random point within the node's bounds.
	 */
	public Vector2 getRandomPoint()
	{
		int x = MathUtils.random((int)bounds.x, (int)(bounds.x + Size));
		int y = MathUtils.random((int)bounds.y, (int)(bounds.y + Size));
		return new Vector2(x, y);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Node))
		{
			return false;
		}
		Node otherNode = (Node)other;
		return (bounds.x == otherNode.bounds.x && bounds.y == otherNode.bounds.y);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(bounds.x, bounds.y);
	}
	
	@Override
	public String toString()
	{
		return "(" + getColumnIndex() + "," + getRowIndex() + ")";
	}
}
