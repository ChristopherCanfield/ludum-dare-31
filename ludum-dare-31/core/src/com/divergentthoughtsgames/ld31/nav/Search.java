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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Contains a method that implements the A* search algorithm.
 * @author Christopher D. Canfield
 */
public abstract class Search
{
	/**
	 * Performs an A* search between the start and end node. Returns a a queue of nodes representing 
	 * the path from start to end.
	 * @param start The initial node.
	 * @param end The final node.
	 * @return A queue of nodes representing the path from the start node to the end node.
	 */
	public static Queue<Node> aStar(Node start, Node end)
	{
		// Instantiate the frontier priority queue and searched set. The frontier 
		// is used to identify nodes that are at the edge of the explored zone. The
		// lowest cost of these nodes will then have their edges searched.
		// The searched set ensures that a previously explored node is not re-explored.
		PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();
		Set<Node> searched = new HashSet<Node>();
		
		// Wrap the start node in the SearchNode decorator.
		SearchNode startNode = 
				new SearchNode(start, null, 0, calculateCost(start, end));
		
		// Add the start node to the frontier priority queue and the searched set.
		// The searched set ensures that the start node isn't searched
		searched.add(startNode);
		frontier.add(startNode);
		
		// Loop through the frontier nodes. If all nodes are searched and
		// no path to the exit is found, then no path is possible, and null
		// will be returned.
		while (!frontier.isEmpty())
		{
			SearchNode lowestCost = frontier.remove();
			
			// Check if the lowest cost equals the end node. If it does, 
			// the algorithm has reached the end, so construct the path using
			// the linked list of parents, and return the result.
			if (lowestCost.equals(end))
			{
				return constructPath(lowestCost);
			}
			
			// Iterate through each node that is connected to the current lowest cost node.
			for (Edge edge : lowestCost.getEdges())
			{
				if (edge.isPassable())
				{
					Node currentNode = edge.getOppositeNode(lowestCost);
					if (currentNode.isPassable())
					{
						// Calculate the g (from start) cost of the lowest cost node by 
						// taking the parent's g cost and adding 1 to it.
						double g = edge.cost + lowestCost.getG();
		
						// Calculate the h (heuristic) cost of the lowest cost node
						// to the end node.
						double h = calculateCost(currentNode, end);
						
						// Wrap the edge in the SearchNode decorator, so the parent and 
						// costs can be stored with it.
						SearchNode edgeSearchNode = new SearchNode(currentNode, lowestCost, g, h);
						
						// Determine if the edge has already been searched.
						if (!searched.contains(edgeSearchNode))
						{
							frontier.add(edgeSearchNode);
							
							// Add the edge to the searched set.
							searched.add(edgeSearchNode);
						}
					}
					else
					{
						searched.add(currentNode);
					}
				}
			}
		}
		
		// Return an empty list if no path can be found from 
		// the start node to the end node.
		return new LinkedList<Node>();
	}
	
	/**
	 * Constructs a path from the final SearchNode back to the start node. This
	 * should be used with the Queue<Path> returned by the A* algorithm.
	 * @param finalNodeInPath The last node in the path returned by the A* algorithm.
	 * @return The path, starting at the start node to the end node.
	 */
	private static Queue<Node> constructPath(SearchNode finalNodeInPath)
	{
		Deque<Node> path = new ArrayDeque<Node>();
		SearchNode currentPathNode = finalNodeInPath;
		
		// Loop through the nodes until there are no more parents.
		while (currentPathNode != null)
		{
			// Add the current node to the path.
			path.addFirst(currentPathNode);
			
			// Set the current node reference to the parent of the 
			// node that was just added.
			currentPathNode = currentPathNode.getParent();
		}
		return path;
	}
	
	private static double calculateCost(Node start, Node end)
	{
//		double startRow = start.getCenterY();
//		double endRow = end.getCenterY();
//		double startColumn = start.getCenterX();
//		double endColumn = end.getCenterX();

		final double D = 1;
		
		final double startX = start.getColumnIndex();
		final double startY = start.getRowIndex();
		final double endX = end.getColumnIndex();
		final double endY = end.getRowIndex();
		
		double dx = Math.abs(startX - endX);
		double dy = Math.abs(startY - endY);
		return D * Math.sqrt(dx * dx + dy * dy);
		
//		double rowSquared = (startRow - endRow) * (startRow - endRow);
//		double columnSquared = (startColumn - endColumn) * (startColumn - endColumn);
//
//		return Math.sqrt(rowSquared + columnSquared);
	}
}
