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
