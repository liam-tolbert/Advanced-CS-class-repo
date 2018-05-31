package com.Final.src.main;

import java.util.Scanner;

public class Tolbert_Liam_Period1_Main
{
    public static void main(String[] args)
    {
	Scanner console = new Scanner(System.in);
	RBTree rbt = new RBTree(Integer.MIN_VALUE);
	System.out.println("Red Black Tree Test\n");
	char ch;
	do
	{
	    System.out.println("\nLiam and Bayan's\n"
	    	+ "Red Black Tree Operations!!\n");
	    System.out.println("1. Insert! ");
	    System.out.println("2. Search!");
	    System.out.println("3. Count nodes!");
	    System.out.println("4. Check empty!");
	    System.out.println("5. Clear tree!");

	    int choice = console.nextInt();
	    switch (choice)
	    {
	    case 1:
		System.out.println("Enter INTEGER to insert into the tree");
		rbt.insert(console.nextInt());
		break;
	    case 2:
		System.out.println("Enter INTEGER to search for");
		System.out.println("Search result : " + rbt.search(console.nextInt()));
		break;
	    case 3:
		System.out.println("Nodes = " + rbt.countNodes());
		break;
	    case 4:
		System.out.println("Empty status = " + rbt.isEmpty());
		break;
	    case 5:
		System.out.println("\nTree Cleared");
		rbt.makeEmpty();
		break;
	    default:
		System.out.println("Wrong Entry \n ");
		break;
	    }
	    /* Display tree in multiple formats*/
	    System.out.print("\nPost order : ");
	    rbt.postorder();
	    System.out.print("\nPre order : ");
	    rbt.preorder();
	    System.out.print("\nIn order : ");
	    rbt.inorder();

	    System.out.println("\nDo you want to continue (Type y or n) \n");
	    ch = console.next().charAt(0);
	} while (ch == 'Y' || ch == 'y');
    }

}
