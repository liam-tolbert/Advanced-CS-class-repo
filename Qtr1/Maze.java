package com.LabTests.src.Qtr1;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Maze extends JPanel
{
    private JButton[][] board;
    private int[][] matrix;
    private int row, col, x, y;
    private JLabel label;

    public Maze()
    {
	setLayout(new BorderLayout());
	row = 0;
	col = 0;
	x = 14;
	y = 14;
	
	JPanel north = new JPanel();
	north.setLayout(new FlowLayout());
	add(north, BorderLayout.NORTH);
	label = new JLabel("Maze will be traversed!");
	north.add(label);
	
	JPanel center = new JPanel();
	center.setLayout(new GridLayout(15, 15));
	add(center, BorderLayout.CENTER);
	board = new JButton[15][15];
	matrix = new int[15][15];
	
	for(int r = 0; r < matrix.length; r++)
	    for(int c = 0; c < matrix[0].length; c++)
	    {
		board[r][c] = new JButton();
		board[r][c].setBackground(Color.green);
		center.add(board[r][c]);
	    }
	
	traverseMaze(matrix, row, col, x, y, '*', "");
    }

    public void traverseMaze(int[][] x, int r, int c, int exitR, int exitC, int marked, String path)
    {
	if (r < 0 || c < 0)
	    return;
	if (r > 5 || c > 5)
	    return;
	if (r == exitR && c == exitC)
	{
	    System.out.println("successfully found the exit!");
	    System.out.println(path);
	    return;
	}
	if (x[r][c] == 0)
	    return;
	if (x[r][c] == marked)
	    return;
	x[r][c] = marked;
	path += "[" + r + ", " + c + "]  ";
	traverseMaze(x, r - 1, c, exitR, exitC, marked, path);
	traverseMaze(x, r + 1, c, exitR, exitC, marked, path);
	traverseMaze(x, r, c - 1, exitR, exitC, marked, path);
	traverseMaze(x, r, c + 1, exitR, exitC, marked, path);
    }

}
