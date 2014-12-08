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
	
	public double cost(Node start, Node end)
	{
		double startRow = start.getCenterY();
		double endRow = end.getCenterY();
		double startColumn = start.getCenterX();
		double endColumn = end.getCenterX();

		double rowSquared = (startRow - endRow) * (startRow - endRow);
		double columnSquared = (startColumn - endColumn) * (startColumn - endColumn);

		return Math.sqrt(rowSquared + columnSquared);
	}
}
