package com.LabTests.src.Qtr2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MazeStackAndQueue extends JPanel
{
    /**
     * @author Liam Tolbert
     */

    private static JButton[][] board;
    private static JLabel label;
    private static JPanel maze, settings, input;
    private static JButton reset, info, start, loadMaze, inputStart, inputEnd;
    private static JComboBox<String> algorithms;
    private static JTextField row, col;
    private static MazePoint[][] grid;
    private static Timer progressionTimer;
    private static JSlider timeSlider;

    // Constructor for making the GUI maze
    public MazeStackAndQueue()
    {
	setLayout(new BorderLayout());

	maze = new JPanel();
	settings = new JPanel();
	input = new JPanel();

	label = new JLabel("This is Liam's GUI maze program.", SwingConstants.CENTER);

	timeSlider = new JSlider();

	reset = new JButton("Reset");
	info = new JButton("Info");
	loadMaze = new JButton("Load the maze");
	start = new JButton("Start");
	inputStart = new JButton("Starting Location");
	inputEnd = new JButton("Ending location");

	row = new JTextField("x", 10);
	col = new JTextField("y", 10);

	String[] algorithmList =
	{ "Breadth first search", "Depth first search", "Recursion" };
	algorithms = new JComboBox<String>(algorithmList);

	settings.setLayout(new FlowLayout());
	input.setLayout(new FlowLayout());
	maze.setLayout(new FlowLayout());

	add(settings, BorderLayout.SOUTH);
	add(label, BorderLayout.NORTH);
	add(maze, BorderLayout.CENTER);

	settings.add(input);
	settings.add(reset);
	reset.setEnabled(false);
	settings.add(info);
	settings.add(start);
	start.setEnabled(false);
	settings.add(loadMaze);
	settings.add(algorithms);
	settings.add(timeSlider);

	input.add(inputStart);
	inputStart.setEnabled(false);
	input.add(inputEnd);
	inputEnd.setEnabled(false);
	input.add(col);
	input.add(row);
    }

    /**
     * Prints the grid
     */
    // pre: grid is a valid MazePoint grid
    private static void dumpGrid()
    {
	for (int i = 0; i < grid.length; i++)
	{
	    for (int j = 0; j < grid[0].length; j++)
	    {
		System.out.print(grid[i][j]);
	    }
	    System.out.println();
	}
	System.out.println();
    }
    // post: the grid is printed

    public static void traverseMaze(int r, int c, int exitR, int exitC)
    {
	traverseMazeHelper(r, c, exitR, exitC, "");
    }

    /**
     * Uses recursion to traverse a maze
     * 
     * @param r
     *            the x to start in
     * @param c
     *            the y to start in
     * @param exitR
     *            the x to end in
     * @param exitC
     *            the y to end in
     * @param path
     *            the final path of the maze
     */
    // pre: starting x, y, ending x, y, are all points on the maze and are all
    // nonvisited points
    private static void traverseMazeHelper(int r, int c, int exitR, int exitC, String path)
    {
	if (r < 0 || c < 0)
	{
	    return;
	}
	if (r >= grid[0].length || c >= grid.length)
	{
	    return;
	}
	if (r == exitR && c == exitC)// found the exit
	{
	    System.out.println("successfully found the exit!");
	    System.out.println(path);
	    board[r][c].setBackground(Color.PINK);
	    return;
	}
	if (grid[r][c].getMark() == 2)// found a wall
	{
	    return;
	}
	if (grid[r][c].getMark() == 1)// found a point that was visited already
	{
	    return;
	}
	grid[r][c].markAsVisited();
	board[r][c].setBackground(Color.YELLOW);// marks in GUI
	path += "[" + r + ", " + c + "]  ";
	traverseMazeHelper(r - 1, c, exitR, exitC, path);
	traverseMazeHelper(r + 1, c, exitR, exitC, path);
	traverseMazeHelper(r, c - 1, exitR, exitC, path);
	traverseMazeHelper(r, c + 1, exitR, exitC, path);
    }
    // post: the ending point in the maze has been found by the searching algorithm

    /**
     * Uses stacks to perform depth first search on a maze
     * 
     * @param startingX
     *            the starting X value
     * @param startingY
     *            the starting Y value
     * @param endingX
     *            the ending X value
     * @param endingY
     *            the ending Y value
     */
    // pre: starting x, y, ending x, y, are all points on the maze and are all
    // nonvisited points
    public static void useStack(int startingX, int startingY, int endingX, int endingY)
    {
	int currentX = startingX, currentY = startingY;
	MazePoint start = grid[startingX][startingY];
	dumpGrid();

	Stack<MazePoint> stack = new Stack<>();

	stack.push(start);

	while ((currentX != endingX || currentY != endingY) && !stack.isEmpty())
	{
	    System.out.println(currentX + "-" + currentY);
	    MazePoint top = stack.pop();
	    top.markAsVisited();
	    maze.validate();
	    board[top.getX()][top.getY()].setBackground(Color.YELLOW);
	    currentX = top.getX();
	    currentY = top.getY();
	    if (top.getUp(grid).getMark() == 0)
	    {
		stack.push(top.getUp(grid));
	    }
	    if (top.getDown(grid).getMark() == 0)
	    {
		stack.push(top.getDown(grid));
	    }
	    if (top.getRight(grid).getMark() == 0)
	    {
		stack.push(top.getRight(grid));
	    }
	    if (top.getLeft(grid).getMark() == 0)
	    {
		stack.push(top.getLeft(grid));
	    }
	}
	board[currentX][currentY].setBackground(Color.PINK);// found the exit
	dumpGrid();
	progressionTimer.stop();
    }
    // post: the ending point in the maze has been found by the searching algorithm

    /**
     * Uses queues to perform breadth first search on a maze
     * 
     * @param startingX
     *            the starting X
     * @param startingY
     *            the starting Y
     * @param endingX
     *            the ending X
     * @param endingY
     *            the ending Y
     */
    // pre: starting x, y, ending x, y, are all points on the maze and are all
    // nonvisited points
    public static void useQueue(int startingX, int startingY, int endingX, int endingY)
    {
	int currentX = startingX, currentY = startingY;
	MazePoint start = grid[startingX][startingY];
	dumpGrid();
	ArrayDeque<MazePoint> q = new ArrayDeque<>();

	q.add(start);

	while ((currentX != endingX && currentY != endingY) && !(q.size() == 0))
	{
	    MazePoint top = q.removeFirst();
	    top.markAsVisited();
	    board[top.getX()][top.getY()].setBackground(Color.YELLOW);
	    if (top.getUp(grid).getMark() != 1)
	    {
		q.add(top.getUp(grid));
	    }
	    if (top.getDown(grid).getMark() != 1)
	    {
		q.add(top.getDown(grid));
	    }
	    if (top.getRight(grid).getMark() != 1)
	    {
		q.add(top.getRight(grid));
	    }
	    if (top.getLeft(grid).getMark() != 1)
	    {
		q.add(top.getLeft(grid));
	    }
	}
	board[currentX][currentY].setBackground(Color.PINK);// found the exit
	dumpGrid();
	progressionTimer.stop();
    }
    // post: the ending point in the maze has been found by the searching algorithm

    public static void main(String[] args)
    {
	JFrame frame = new JFrame("Liam Tolbert Adv AP CS Maze Program");

	// creating initial frame
	frame.setSize(1200, 600);
	frame.setLocation(75, 75);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setContentPane(new MazeStackAndQueue());

	// adding all ActionListeners
	loadMaze.addActionListener(new DimensionsInputHandler());
	info.addActionListener(new InfoHandler());
	start.addActionListener(new StartListener());
	reset.addActionListener(new ResetListener());
	inputStart.addActionListener(new InputStartingListener());
	inputEnd.addActionListener(new InputEndingListener());

	frame.setVisible(true);
    }

    /**
     * Makes a window to show info for the maze program to show the user what to do
     * 
     * @author Liam Tolbert
     *
     */
    private static class InfoHandler implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    // the info frame for the window
	    JFrame frame = new JFrame();
	    frame.setTitle("Info");
	    frame.setLayout(new FlowLayout());
	    frame.setSize(500, 300);
	    frame.setLocation(450, 250);
	    JTextArea info = new JTextArea(16, 40);
	    info.setText(
		    "Enter rows and columns into the text boxes where they are labeled. Use the \nstarting location and ending location buttons to input starting and ending locationClick the submit button in those windows to register the locations in the system. The drop down is for breadth first search, depth first search, and recursion. You can edit walls. Press the start button to begin. Use the reset button to get rid of \nthe path generated by the maze. Press the load maze button to give another \nmaze with the same dimensions. ");
	    // Trying to make the JTextArea look like a JLabel, since JLabels have no form of text wrapping
	    info.setOpaque(false);
	    info.setLineWrap(true);
	    info.setEditable(false);
	    info.setFocusable(false);
	    frame.add(info);
	    frame.setVisible(true);
	}
    }

    public static JTextField xText, yText;
    public static int x, y;
    public static int startingX, startingY, endingX, endingY;

    /**
     * creates the Starting position window and accepts inputs for the x and y
     * 
     * @author Liam Tolbert
     *
     */
    private static class InputStartingListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    JFrame frame = new JFrame();
	    frame.setTitle("Set the starting position");
	    frame.setLayout(new FlowLayout());
	    frame.setSize(400, 150);
	    frame.setLocation(500, 300);

	    JButton confirm = new JButton("Submit");
	    JLabel xLabel = new JLabel("Starting x:");
	    JLabel yLabel = new JLabel("Starting y:");
	    JTextField x = new JTextField(10);
	    JTextField y = new JTextField(10);

	    xText = x;
	    yText = y;

	    frame.add(xLabel);
	    frame.add(x);
	    frame.add(yLabel);
	    frame.add(y);
	    frame.add(confirm);

	    confirm.addActionListener(new SubmitCoordsListener(0));

	    frame.setVisible(true);
	}
    }

    /**
     * creates the ending position window and accepts inputs for the x and y
     * 
     * @author Liam Tolbert
     *
     */
    private static class InputEndingListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    JFrame frame = new JFrame();
	    frame.setTitle("Set the ending position");
	    frame.setLayout(new FlowLayout());
	    frame.setSize(400, 150);
	    frame.setLocation(500, 300);

	    JButton confirm = new JButton("Submit");
	    JLabel xLabel = new JLabel("Ending x:");
	    JLabel yLabel = new JLabel("Ending y:");
	    JTextField x = new JTextField(10);
	    JTextField y = new JTextField(10);

	    xText = x;
	    yText = y;

	    frame.add(xLabel);
	    frame.add(x);
	    frame.add(yLabel);
	    frame.add(y);
	    frame.add(confirm);

	    confirm.addActionListener(new SubmitCoordsListener(1));

	    frame.setVisible(true);
	}
    }

    /**
     * Handles what to do when the coordinates for starting/ending locations are
     * input
     * 
     * @author Liam Tolbert
     *
     */
    private static class SubmitCoordsListener implements ActionListener
    {
	private int startOrEnd;// 0 if start, 1 if end

	public SubmitCoordsListener(int startOrEnd)
	{
	    this.startOrEnd = startOrEnd;
	}

	public void actionPerformed(ActionEvent e)
	{
	    if (startOrEnd == 0)// start
	    {
		startingX = Integer.parseInt(xText.getText()) - 1;// Because regular people always count from 1
		startingY = Integer.parseInt(yText.getText()) - 1;

		for (int x = 0; x < board.length; x++) // these nested for loops are to clear the maze of any starting
						       // points before setting a new one
		    for (int y = 0; y < board[0].length; y++)
			if (board[x][y].getBackground().equals(Color.RED))
			    board[x][y].setBackground(Color.WHITE);

		if (!board[startingY][startingX].getBackground().equals(Color.BLACK))// checks if point isn't a wall
		    board[startingY][startingX].setBackground(Color.RED);

	    } else if (startOrEnd == 1)// end
	    {
		endingX = Integer.parseInt(xText.getText()) - 1;// Because regular people always count from 1
		endingY = Integer.parseInt(yText.getText()) - 1;

		for (int x = 0; x < board.length; x++)// clearing the maze of any ending points before setting a new one
		    for (int y = 0; y < board[0].length; y++)
			if (board[x][y].getBackground().equals(Color.BLUE))
			    board[x][y].setBackground(Color.WHITE);

		if (!board[endingY][endingX].getBackground().equals(Color.BLACK))// checks if point isn't a wall
		    board[endingY][endingX].setBackground(Color.BLUE);
	    }
	}
    }

    /**
     * Gets rid of the drawn path (with yellow and pink buttons) and replaces them
     * with white ones, while keeping the walls of the maze intact
     * 
     * @author Liam Tolbert
     *
     */
    private static class ResetListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    startingX = 0; // a null value to reset things
	    startingY = 0;
	    endingX = 0;
	    endingY = 0;
	    for (int row = 0; row < board.length; row++)
	    {
		for (int col = 0; col < board[0].length; col++)
		{
		    if (board[row][col].getBackground().equals(Color.YELLOW)// path
			    || board[row][col].getBackground().equals(Color.PINK))// ending point
		    {
			board[row][col].setBackground(Color.WHITE);// normal, non-visited point
		    }
		}
	    }
	    maze.validate();
	}
    }

    /**
     * Calls the respective algorithms when they are selected in the drop-down box
     * 
     * @author Liam Tolbert
     *
     */
    private static class StartListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    progressionTimer = new Timer(timeSlider.getValue() * 10/* slider is 0 - 100; gets value to milliseconds */,
		    new TimerDelayer());
	    progressionTimer.start();
	    if (algorithms.getSelectedItem().equals("Breadth first search"))
		useStack(startingX, startingY, endingX, endingY);
	    else if (algorithms.getSelectedItem().equals("Depth first search"))
		useQueue(startingX, startingY, endingX, endingY);
	    else if (algorithms.getSelectedItem().equals("Recursion"))
		traverseMaze(startingX, startingY, endingX, endingY);
	}
    }

    private static class TimerDelayer implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    
	}
    }

    /**
     * Makes the maze out of the coordinates that were input, while generating the
     * walls randomly
     * 
     * @author Liam Tolbert
     *
     */
    private static class DimensionsInputHandler implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    System.out.println("Loading the maze...");

	    int chanceForWall = 0;

	    if (!(row.getText() == null || col.getText() == null))
	    {
		System.out.println("Found coordinates...");
		try
		{
		    x = Integer.parseInt(row.getText());
		    y = Integer.parseInt(col.getText());
		} catch (NumberFormatException n)
		{
		    JFrame errorFrame = new JFrame();
		    errorFrame.setTitle("ERROR");
		    errorFrame.setLayout(new FlowLayout());
		    errorFrame.setSize(400, 150);
		    errorFrame.setLocation(500, 300);
		    errorFrame.add(new JLabel("Error! Please input size of maze into boxes below!"));
		    errorFrame.setVisible(true);
		    
		}catch(IllegalArgumentException i)
		{
		    JFrame errorFrame = new JFrame();
		    errorFrame.setTitle("ERROR");
		    errorFrame.setLayout(new FlowLayout());
		    errorFrame.setSize(400, 150);
		    errorFrame.setLocation(500, 300);
		    errorFrame.add(new JLabel("Error! Please input size of maze into boxes below!"));
		    errorFrame.setVisible(true);
		}
	    }
	    // making the board out of the coordinates
	    grid = new MazePoint[x][y];
	    maze.removeAll();
	    maze.setLayout(new GridLayout(x, y));
	    board = new JButton[x][y];

	    for (int row = 0; row < x; row++)
	    {
		System.out.println("Loading row " + row + ", " + y);
		for (int col = 0; col < y; col++)
		{
		    chanceForWall = (int) (Math.random() * 2) + 1;// 50% chance for a wall to occur
		    grid[row][col] = chanceForWall == 1 ? new MazePoint(row, col, false)
			    : new MazePoint(row, col, true);
		    board[row][col] = new JButton();
		    // depending on the RNG, make the wall black and the nonvisited ones white
		    board[row][col].setBackground(grid[row][col].getMark() == 0 ? Color.white : Color.black);
		    board[row][col].addActionListener(new changeTileListener(row, col, grid[row][col].getMark()));
		    maze.add(board[row][col]);
		}

	    }
	    maze.validate();// validate() method is used to refresh the maze after it has loaded, otherwise
			    // nothing would happen on the screen
	    System.out.println("finished loading maze");
	}
    }

    /**
     * Handles the user clicking any button on the maze, which would change a
     * non-visited point to a wall, and vice versa
     * 
     * @author Liam Tolbert
     *
     */
    private static class changeTileListener implements ActionListener
    {
	private int x, y, wall;

	public changeTileListener(int x, int y, int wall)
	{
	    this.x = x;
	    this.y = y;
	    this.wall = wall;
	}

	public void actionPerformed(ActionEvent e)
	{
	    System.out.println("This point has been clicked: " + x + ", " + y + " I was a " + wall);

	    // red and blue points (starting and ending locations) cannot be changed by a
	    // user clicking on it, but anything else can
	    if (!(board[x][y].getBackground().equals(Color.RED) || board[x][y].getBackground().equals(Color.BLUE)))
	    {
		board[x][y].setBackground(wall == 0 ? Color.BLACK : Color.WHITE);
		wall = wall == 0 ? 2 : 0;
		grid[x][y].switchTile();
	    }
	}
    }

}
