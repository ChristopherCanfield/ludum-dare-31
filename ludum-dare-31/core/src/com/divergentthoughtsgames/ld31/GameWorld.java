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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.World;
import com.divergentthoughtsgames.ld31.nav.Edge;
import com.divergentthoughtsgames.ld31.nav.Node;

public class GameWorld
{
	public static World physicsWorld;
	
	public static List<Organism> organisms;
	
	private static BufferedImage collisionMap;
	
	private static List<Node> nodeList;
	private static Node[][] nodes;
	
	private static int columns = 1920/Node.Size;
	private static int rows = 1080/Node.Size;
	
	public static void initialize()
	{
		try {
			collisionMap = ImageIO.read(new File("collision-map.png"));
		} catch (IOException e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
		
		nodeList = new ArrayList<Node>();
		nodes = new Node[columns][rows];
		createNavGraph(nodeList, nodes, columns, rows);
	}
	
	public static boolean isPassable(float x, float y)
	{
		return isPassable((int)x, (int)y);
	}
	
	public static boolean isPassable(int x, int y)
	{
		int adjustedX = Math.round(x - Screen.Zero.x);
		int adjustedY = Math.round(collisionMap.getHeight() - (y - Screen.Zero.y));
		
		System.out.println(adjustedX + "," + adjustedY + " (" + x + "," + y + ")");
		return collisionMap.getRGB(adjustedX, adjustedY) != -16777216;
	}
	
	public static void drawNodes(ShapeRenderer shapeRenderer)
	{
		Color originalColor = shapeRenderer.getColor();
		
		shapeRenderer.begin(ShapeType.Line);
		for (Node node : nodeList)
		{
			Color color = (node.isPassable()) ? Color.CYAN : Color.RED;
			shapeRenderer.setColor(color);
			node.draw(shapeRenderer);
		}
		shapeRenderer.end();
		
		shapeRenderer.setColor(originalColor);
	}
	
	/**
	 * Creates the structure of the navigation graph.
	 * @param nodeList an instantiated list that will be populated with the navigation nodes.
	 * @param nodes a 2-dimensional array that will be populated with the navigation nodes, in 
	 * [column][row] notation. The array bounds must be equal to the number of columns and rows in 
	 * the map. 
	 * @param columns the number of columns in the map.
	 * @param rows the number of rows in the map.
	 */
	private static void createNavGraph(List<Node> nodeList, Node[][] nodes, int columns, int rows)
	{
		// Build the navigation graph. 0,0 = bottom left.
		// For now, the navigation graph has one node per map cell, but this may change in the future.
		for (int row = 0; row < rows; ++row)
		{
			for (int column = 0; column < columns; ++column)
			{
				Node node = new Node((int)(column * Node.Size + Screen.Zero.x), (int)(row * Node.Size + Screen.Zero.y));
				nodeList.add(node);
				nodes[column][row] = node;
				addEdges(nodes, row, column, columns);
			}
		}
		
		setPassability();
	}
	
	/**
	 * Adds edges to the specified nodes.
	 * @param nodes the navigation graph, in [column][row] order.
	 * @param row the node's row.
	 * @param column the node's column.
	 * @param columns the number of columns in the navigation graph.
	 */
	private static void addEdges(Node[][] nodes, int row, int column, int columns)
	{
		Node node = nodes[column][row];
		
		// Add down.
		if (row > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[column][row - 1]);
		}
		
		// Add left.
		if (column > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[column - 1][row]);
		}
		
		// Add lower-left diagonal.
		if (row > 0 && column > 0)
		{
			Edge edge = new Edge(1.4);
			edge.addNode(node).addNode(nodes[column - 1][row - 1]);
		}
		
		// Add lower-right diagonal.
		if (row > 0 && column < columns - 1)
		{
			if (nodes[column + 1][row - 1] != null)
			{
				Edge edge = new Edge(1.4);
				edge.addNode(node).addNode(nodes[column + 1][row - 1]);
			}
		}
	}
	
	private static void setPassability()
	{
		for (Node node : nodeList)
		{
			if (isPassable(node.getCenterX(), node.getCenterY()))
			{
				node.setPassable(true);
			}
		}
	}
}
