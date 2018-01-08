package com.LabTests.src.Qtr1;
/**
 * @author Liam Tolbert
 * How I feel about this lab: Wasn't really a challenge for me at least. Although supporting double digit coordinates to mark the correct path
 * in the maze took a while somehow, everything else was relatively simple, especially the actual recursion part. 
 * What I learned:
 * - Recursion is actually really simple
 * - Reviewed how to manipulate strings (substring)
 */
import java.util.Scanner;

public class Maze_shell
{
    private static final int VISITED = 3;
    private static final int PATH = 7;
    private static String path;
    private static int[][] grid =
    {
	    { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 },
	    { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
	    { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
	    { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1 },
	    { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 },
	    { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
	    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

    public boolean findAnExit(int x, int y)
    {
	path = "";
	return findAnExitHelper(x, y, path);
    } // FindAnExit
    /**
     * Helper method for the find an exit method
     * @param r row
     * @param c column
     * @param path the string to add coords of the correct path
     * @return true if the exit was found, false if not
     */
    public boolean findAnExitHelper(int r, int c, String path)
    {
	//all if statements are base cases
	if (r < 0 || c < 0)
	    return false;
	if (r > grid.length - 1 || c > grid[0].length - 1)
	    return false;
	if (r == grid.length - 1 && c == grid[0].length - 1)
	{
	    System.out.println("successfully found the exit!");
	    grid[r][c] = VISITED;
	    path += "[" + r + "," + c + "]";
	    this.path = path;
	    System.out.println(this.path);

	    return true;
	}
	if (grid[r][c] == 0)
	    return false;
	if (grid[r][c] == VISITED)
	    return false;
	grid[r][c] = VISITED;
	//adding info to path string
	path += "[" + r + "," + c + "];";
	//recursive calling below
	findAnExitHelper(r - 1, c, path);
	findAnExitHelper(r + 1, c, path);
	findAnExitHelper(r, c - 1, path);
	findAnExitHelper(r, c + 1, path);
	return true;
    } // findAnExitHelper
    /**
     * string representation of the grid
     */
    public String toString()
    {
	String s = "";
	for (int i = 0; i < grid.length; i++)
	{
	    for (int j = 0; j < grid[i].length; j++)
	    {
		s += "" + grid[i][j];
	    }
	    s += "\n";
	}
	return s;
    } // toString

    public static void main(String[] args)
    {
	// Assume that the exit of the maze is at the lower right hand corner of
	// the grid
	Maze_shell m = new Maze_shell();

	// display the maze
	System.out.println(m);
	Scanner input = new Scanner(System.in);

	System.out.println("Enter current location (x and y coordinates: ");
	int startX = input.nextInt();
	int startY = input.nextInt();

	while (!m.findAnExit(startX, startY))
	{
	    System.out.println("Still trapped inside!");
	    System.out.println(m);

	    System.out.println("Re-enter current location (x and y coordinates: ");
	    startX = input.nextInt();
	    startY = input.nextInt();
	}
	
	String[] pathList = path.split(";");
	
	for (int i = 0; i < pathList.length; i++)//This for loop sets the correct path to the PATH value
	{
	    String x = pathList[i].substring(pathList[i].indexOf("[") + 1, pathList[i].indexOf(","));
	    String y = pathList[i].substring(pathList[i].indexOf(",") + 1, pathList[i].indexOf("]"));
	    grid[Integer.parseInt(x)][Integer.parseInt(y)] = PATH;
	}
	
	System.out.println("Successfully exit the maze!!!");

	// display the path (indicated by 7) that leads to the exit of the maze
	// also display locations tried

	System.out.println(m);
    } // main
} // Maze
/********************************  Sample Runs
*********************************  Run #1
 7 8
 [7,8][7,9][7,10][7,11][7,12]
 Successfully exit the maze!!!
 
 3 3 3 0 3 3 0 0 0 3 3 3 3 
 3 0 3 3 3 0 3 3 3 3 0 0 3 
 0 0 0 0 3 0 3 0 3 0 1 0 0 
 3 3 3 0 3 3 3 0 3 0 0 1 1 
 3 0 3 0 0 0 0 3 3 3 0 0 1 
 3 0 3 3 3 3 3 3 0 3 3 3 0 
 3 0 0 0 0 0 0 0 0 0 0 0 0 
 3 3 3 3 3 3 3 3 7 7 7 7 7 



********************  Run #2
0 0
 [0,0][0,1][0,2][1,2][1,3][1,4][2,4][3,4][3,5][3,6][2,6][1,6][1,7][1,8][2,8][3,8][4,8][4,7][5,7][5,6][5,5][5,4][5,3][5,2]
 [4,2][3,2][3,1][3,0][4,0][5,0][6,0][7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 Successfully exit the maze!!!
 
 7 7 7 0 1 1 0 0 0 1 1 1 1 
 3 0 7 7 7 0 7 7 7 1 0 0 1 
 0 0 0 0 7 0 7 0 7 0 1 0 0 
 7 7 7 0 7 7 7 0 7 0 0 1 1 
 7 0 7 0 0 0 0 7 7 1 0 0 1 
 7 0 7 7 7 7 7 
 
 
 ******************************  Run #3
 3 12
 no way out!
 
 1 1 1 0 1 1 0 0 0 1 1 1 1 
 1 0 1 1 1 0 1 1 1 1 0 0 1 
 0 0 0 0 1 0 1 0 1 0 1 0 0 
 1 1 1 0 1 1 1 0 1 0 0 3 3 
 1 0 1 0 0 0 0 1 1 1 0 0 3 
 1 0 1 1 1 1 1 1 0 1 1 1 0 
 1 0 0 0 0 0 0 0 0 0 0 0 0 
 1 1 1 1 1 1 1 1 1 1 1 1 1 
 

*********************************/