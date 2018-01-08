package com.LabTests.src.Qtr2;

public class MazePoint
{
    /**
     * Date: 11/25/17
     * @author Liam Tolbert
     * What I learned:
     * - The algorithm for depth first search and breadth first search
     * - how to properly use static variables (accidentally used it on the wall variable in the constructor of MazePoint and all the points became a wall)
     * How I feel about this lab: The only issue I have with this lab right now is that the instantiation of the maze is really inefficient with the 
     * 4 by 4 grid of MazePoint objects. However, implementing the algorithm was fairly simple. I had quite a few bugs near the end that were really difficult
     * to resolve. For example, in the MazePoint class, the mark variable was, for some reason, set to static. Since static variables change all instances of the variable
     * in a program to its current set value, everything became a wall since the very last point happened to be a wall in my first maze. 
     * However, this lab still was pretty entertaining to make overall. 
     * Where I got help from:
     * - A family friend (who is an experienced Java developer)
     * - I managed to program everything and resolve all the bugs except for the static variable bug (mentioned above), and he helped me with it. 
     */
    private int x;
    private int y;
    private int mark;
    private static final int VISITED = 1, NONVISITED = 0, WALL = 2;

    public MazePoint(int x, int y, boolean wall)
    {
	this.setX(x);
	this.setY(y);
	mark = (wall ? WALL : NONVISITED);
    }

    /**
     * returns the point on the maze that is directly above the given point
     * 
     * @param grid
     *            the grid
     * @return the point on the maze that is directly above the given point
     */
    // pre: grid is a valid grid of MazePoint objects
    public MazePoint getUp(MazePoint[][] grid)
    {
	if (this.getY() > 0)
	{
	    return grid[this.getX()][this.getY() - 1];
	}
	return grid[this.getX()][this.getY()];
    }

    // post: returns the point above the give point, but if the above point is a
    // wall or is nonexistent (on a border) nothing happens
    /**
     * returns the point on the maze that is directly below the given point
     * 
     * @param grid
     *            the grid
     * @return the point on the maze that is directly below the given point
     */
    // pre: grid is a valid grid of MazePoint objects
    public MazePoint getDown(MazePoint[][] grid)
    {
	if (this.getY() < grid.length - 1/* Math is range of maze */)
	{
	    return grid[this.getX()][this.getY() + 1];
	}
	return grid[this.getX()][this.getY()];
    }

    // post: returns the point below the given point, but if the point below is a
    // wall or is nonexistent(border) nothing happens
    /**
     * returns the point on the maze that is directly to the right of the given
     * point
     * 
     * @param grid
     *            the grid
     * @return the point on the maze that is direcytly to the right of the given
     *         point
     */
    // pre: grid is a valid grid of MazePoint objects
    public MazePoint getRight(MazePoint[][] grid)
    {
	if (this.getX() < grid[0].length - 1)
	{
	    return grid[this.getX() + 1][this.getY()];
	}
	return grid[this.getX()][this.getY()];
    }

    // post: returns the point to the right of the given point, but if the point to
    // the right is a wall or is nonexistent (border) nothing happens
    /**
     * returns the point on the maze that is directly to the left of the given point
     * 
     * @param grid
     *            the grid
     * @return the point on the maze that is directly to the left of the given point
     */
    // pre: grid is a vald grid of MazePoint objects
    public MazePoint getLeft(MazePoint[][] grid)
    {
	if (this.getX() > 0)
	{
	    return grid[this.getX() - 1][this.getY()];
	}
	return grid[this.getX()][this.getY()];
    }
    // post: returns the point to the left of the given point, but if the point to
    // the right is a wall or is nonexistent (border) nothing happens

    public int getX()
    {
	return x;
    }

    private void setX(int x)
    {
	this.x = x;
    }

    public int getY()
    {
	return y;
    }

    private void setY(int y)
    {
	this.y = y;
    }

    public void markAsVisited()
    {
	System.out.println(this.toString());
	mark = VISITED;
    }

    public int getMark()
    {
	return mark;
    }
    
    public void switchTile()
    {
	mark = mark == WALL ? NONVISITED : WALL;
    }

    public String toString()
    {
	return "[" + getX() + "-" + getY() + "-" + getMark() + "]";
    }
}
