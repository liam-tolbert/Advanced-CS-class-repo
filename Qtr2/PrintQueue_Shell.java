package com.LabTests.src.Qtr2;

import java.util.*;

/**
 * @author Liam Tolbert
 * Date: 11/28/17
 * Where I got help: Java documentation about ArrayDeque
 * What I learned:
 * - ArrayDeque: I actually used ArrayDeque because it is possible to remove things from both ends, and that it is also more efficient than LinkedLists
 * How I felt about this lab: This lab was pretty straightforward. It took me about 30 minutes. ArrayDeque was pretty interesting to work with. 
 */
public class PrintQueue_Shell
{
    private static Scanner in;
    private static Queue<String> q;
    private static int jobNumber = 100;

    public static void main(String[] args) throws Exception
    {
	q = new LinkedList<String>();
	in = new Scanner(System.in);
	String prompt = "Add, Print, Delete, eXit:  ";
	System.out.print(prompt);
	String str = in.next();
	while (!str.equals("X"))
	{
	    if (str.equals("A"))
		add();
	    else if (str.equals("P"))
		printJob();
	    else if (str.equals("D"))
		delete();
	    else if (str.equals("E"))
		System.exit(0);
	    else
		System.out.println("   invalid command");
	    printQueue();
	    System.out.print(prompt);
	    str = in.next();
	}
	in.close();
    }

    /**
     * Adds the latest job number the end of the queue and increments jobNumber
     */
    // pre: q is a valid queue
    public static void add()
    {
	q.add(Integer.toString(jobNumber++));
    }
    // post: jobNumber is added to the end of the queue and incremented

    /**
     * prints the last job in the queue; if the queue is empty, then print a message
     */
    // pre: q is a valid queue
    public static void printJob()
    {
	if (q.peek() == null)
	    System.out.println("List is empty!");
	else
	    System.out.println(q.peek());
    }

    // post: q.peek() is printed or "List is empty!" if the queue is empty
    /**
     * deletes the specified job number prompted by the program from the queue; if
     * the element doesn't exist, print out a message
     */
    // pre: q is a valid queue
    public static void delete()
    {
	Scanner input = new Scanner(System.in);
	System.out.println("Please enter the job number: ");
	String str = input.nextLine();
	if (q.remove(str))
	    System.out.println("Deleted element " + str);
	else
	    System.out.println("Error -- element does not exist!");
    }
    // post: the specified job number is deleted from the queue
    
    /**
     * Prints the entire queue
     */
    // pre: q is a valid queue
    public static void printQueue()
    {
	System.out.print('[');
	for (String num : q)
	{
	    System.out.print(' ' + num + ' ');
	}
	System.out.print(']');
	System.out.println();
    }
    // post: q is entirely printed out
}