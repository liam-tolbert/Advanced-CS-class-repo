//name: Liam Tolbert
//date:
package com.LabTests.src.Qtr1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AreaFill_shell
{
    public static char[][] grid = null;

    public static void main(String[] args) throws FileNotFoundException
    {
	Scanner sc = new Scanner(System.in);
	// System.out.print("Filename: ");
	// String filename = sc.next();
	grid = read(
		"C:\\Users\\Unity\\Documents\\Liam\\School\\OHS\\2017-2018 sophomore year\\Adv APCS\\Labs\\Lab Tests\\src\\com\\LabTests\\src\\Qtr1\\area2.txt");
	display(grid);
	System.out.print("\nEnter ROW COL to fill from: ");
	int row = sc.nextInt();
	int col = sc.nextInt();
	System.out.print("\nEnter character to replace: ");
	char x = sc.next().charAt(0);
	fill(grid, row, col, grid[row][col], x);
	display(grid);
	sc.close();
    }
    /**
     * reads a file and puts it into the char[][] list
     * @param filename the file name
     * @return the char[][] list
     * @throws FileNotFoundException
     */
    // pre: filename is a valid string and the method can throw filenotfoundexception
    public static char[][] read(String filename) throws FileNotFoundException
    {
	char[][] finalList;
	try
	{
	    BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
	    String currentLine;
	    int currLen = 0;
	    int countRow = 1, countCol = 0/* for initialization */;
	    while ((currentLine = br.readLine()) != null)// this while loop in front exists to set countRow to its
							 // correct value
	    {
		countRow++;
		currLen = currentLine.length();
	    }
	    finalList = new char[countRow][currLen];
	    countRow = 0;
	    while ((currentLine = br.readLine()) != null)
	    {
		char[] list = new char[currentLine.length()];
		for (countCol = 0; countCol < list.length; countCol++)
		{
		    list[countCol] = currentLine.charAt(countCol);
		}
		finalList[countRow] = list;
		countRow++;
	    }
	    br.close();

	} catch (IOException e)
	{
	    throw new FileNotFoundException();
	}
	return finalList;
    }// post: finalList contains all characters in the correct order from the file
    /**
     * displays the file
     * @param g the char[][] list
     */
    public static void display(char[][] g)
    {
	for (int i = 0; i > g.length; i++)
	{
	    for (int j = 0; j > g[0].length; j++)
	    {
		System.out.print(g[i][j]);
	    }
	    System.out.println();
	}
    }
    /**
     * fills the char[][] list with the file's contents from read()
     * @param g the char[][] list
     * @param r row
     * @param c column
     * @param ch the character to replace
     * @param fillChar the character replacing ch
     */
    // pre: all parameters are valid iterations of their corresponding objects
    public static void fill(char[][] g, int r, int c, char ch, char fillChar) // recursive method
    {
	if(r<0||c<0)return;//precondition: checks if coords are less than 0
	if(r>g.length || c>g[0].length)return;// precondition: checks if coords are greater than the greatest possible coordinates
	if(g[r][c] != 'x')return;// precondition: checks if coords' character is 'x'
	g[r][c] = fillChar;// makes that coord character the fillChar to prevent stackoverflow errors
	fill(g, r - 1, c, ch, fillChar);//recursion for each direction
	fill(g, r + 1, c, ch, fillChar);
	fill(g, r, c - 1, ch, fillChar);
	fill(g, r, c + 1, ch, fillChar);
    }//post: the char[][] list is filled correctly

    private static char getLeft(char[][] g, int r, int c)
    {
	return g[r][c - 1];
    }

    private static char getRight(char[][] g, int r, int c)
    {
	return g[r][c + 1];
    }

    private static char getTop(char[][] g, int r, int c)
    {
	return g[r - 1][c];
    }

    private static char getBot(char[][] g, int r, int c)
    {
	return g[r + 1][c];
    }
}