package com.LabTests.src.Qtr2;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUITesting extends JPanel
{
    public GUITesting()
    {
	int[][] x = new int[2][2];
	setLayout(new GridLayout());
	for(int i = 0; i < x.length; i++)
	{
	    for(int j = 0; j < x[0].length; j++)
	    {
		add(new JButton());
	    }
	}
    }
    public static void main(String[] args)
    {
	JFrame frame = new JFrame("Liam Tolbert Adv AP CS Maze Program");
	frame.setSize(800, 450);
	frame.setLocation(683, 384);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setContentPane(new GUITesting());
	frame.setVisible(true);
    }

}
