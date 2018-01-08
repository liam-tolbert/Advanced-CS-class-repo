/*****************************************************************************************************************
NAME:      
PERIOD:
DUE DATE: 

PURPOSE:    

WHAT I LEARNED:    
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): 

****************************************************************************************************************/
package com.LabTests.src.Qtr2;

import java.util.*; //for the queue interface

public class TolbertLiamPeriod1_BT
{
    public static void main(String[] args)
    {
	String s = "XCOMPUTERSCIENCE";

	TreeNode root = new TreeNode("" + s.charAt(1), null, null);

	// The root node has index 1
	// The second level nodes' indices: 2, 3
	// The third level nodes' indices: 4,5,6,7
	// Idea: based on the index of the node, log (pos) of base 2 calculates the
	// level of the
	// node: root (index 1) on level 1. Node with index 2 or 3 is on level 2
	for (int pos = 2; pos < s.length(); pos++)
	    insert(root, "" + s.charAt(pos), pos, (int) (1 + Math.log(pos) / Math.log(2)));

	// NOTE: The following 3 lines are supposed to further show you how insert
	// works. You
	// uncomment them and see how the tree looks like with these 3 additional nodes
	// insert(root, "A", 17, 5);
	// insert(root, "B", 18, 5);
	// insert(root, "C", 37, 6); //B's right child

	// display the tree sideway; see the sample output at the end of this file
	display(root, 0);

	System.out.print("\nPreorder: ");
	preorderTraverse(root);
	System.out.print("\nInorder: ");
	inorderTraverse(root);
	System.out.print("\nPostorder: ");
	postorderTraverse(root);

	System.out.println("\n\nNodes = " + countNodes(root));
	System.out.println("Leaves = " + countLeaves(root));
	System.out.println("Grandparents = " + countGrands(root)); // count the number grandparent nodes
	display(root, 0);
	System.out.println("Only childs = " + countOnlys(root)); // count the number of nodes that has only 1 child

	// System.out.println("\nDepth = " + numOfLevels(root));
	System.out.println("Height = " + height(root));

	System.out.println("Min = " + min(root));
	System.out.println("Max = " + max(root));

	System.out.println("\nBy Level: ");
	displayLevelOrder(root); // level by level display of the nodes (starts from left to right for nodes on
				 // the same level)

    } // end of main

    // insert a new node in the tree based on the node's level
    public static void insert(TreeNode t, String s, int pos, int level)
    {
	TreeNode p = t;
	for (int k = level - 2; k > 0; k--)
	    // then 1 << 4 will insert four 0-bits at the right of 1 (binary representation
	    // of 1 is 1.
	    // 1 << 4 results in 10000 (in binary)
	    if ((pos & (1 << k)) == 0)
		p = p.getLeft(); // What does this do? Answer this question first. What does '&' do? Google
				 // it!!!!
	    else // We did not learn this in AP CS A! :
		p = p.getRight(); // What does this do? Answer this question first.

	if ((pos & 1) == 0)
	    p.setLeft(new TreeNode(s, null, null));
	else
	    p.setRight(new TreeNode(s, null, null));
    } // end of insert

    /*****************************************************************************************************
     * postcondition: display the tree sideway
     *****************************************************************************************************/
    public static void display(TreeNode t, int level)
    {
	if (t == null)
	    return;
	display(t.getRight(), level + 1); // recurse right

	for (int k = 0; k < level; k++)
	    System.out.print("\t");
	System.out.println(t.getValue());

	display(t.getLeft(), level + 1); // recurse left
    } // end of display

    public static void preorderTraverse(TreeNode t)
    {
	if (t == null)
	    return;
	System.out.println(" " + t.getValue());
	preorderTraverse(t.getLeft());
	preorderTraverse(t.getRight());
    }

    public static void inorderTraverse(TreeNode t)
    {
	if (t == null)
	    return;
	inorderTraverse(t.getLeft());
	System.out.println(" " + t.getValue());
	inorderTraverse(t.getRight());
    }

    public static void postorderTraverse(TreeNode t)
    {
	if (t == null)
	    return;
	postorderTraverse(t.getLeft());
	postorderTraverse(t.getRight());
	System.out.println(" " + t.getValue());
    }

    public static int countNodes(TreeNode t)
    {
	if (t == null)
	    return 0;
	return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }

    public static int countLeaves(TreeNode t)
    {
	if (t == null)
	    return 0;
	if (t.getLeft() == null && t.getRight() == null)
	    return 1;
	else
	    return countLeaves(t.getRight()) + countLeaves(t.getLeft());
    }

    public static int countGrands(TreeNode t)
    {
	if (t == null)
	{
	    System.out.println("null!");
	    return 0;
	}
	if (height(t) > 2)// found a grandparent
	{
	    System.out.println("found a grandparent that has great-grandchildren!");
	    return 1 + countGrands(t.getLeft()) + countGrands(t.getRight());
	}
	else if(height(t) == 2)
	{
	    System.out.println("Found a grandparent!");
	    return 1;
	}
	return 0;
    }

    public static int countOnlys(TreeNode t)
    {
	if (t.getRight() != null && t.getLeft() != null)
	    return countOnlys(t.getRight()) + countOnlys(t.getLeft());
	if (t.getRight() == null && t.getLeft() == null)
	    return 0;
	else
	    return 1 + countOnlys(t.getLeft() == null ? t.getRight() : t.getLeft());
    }

    public static int depth(TreeNode t)
    {
	if(t == null)
	    return 0;
	return 1 + Math.max(depth(t.getLeft()), depth(t.getRight()));
    }
    
    public static int height(TreeNode t)
    {
	if (t == null)
	    return -1;
	return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
    }

    public static String max(TreeNode t)
    {
	String min = t.getValue();
	if (t.getLeft() != null)
	    min = min.compareTo(t.getLeft().getValue()) < 0 ? max(t.getLeft()) : min;
	if (t.getRight() != null)
	    min = min.compareTo(t.getRight().getValue()) < 0 ? max(t.getRight()) : min;
	return min;
    }

    public static String min(TreeNode t)
    {
	String max = t.getValue();
	if (t.getLeft() != null)
	    max = max.compareTo(t.getLeft().getValue()) > 0 ? min(t.getLeft()) : max;
	if (t.getRight() != null)
	    max = max.compareTo(t.getLeft().getValue()) > 0 ? min(t.getRight()) : max;
	return max;
    }

    /*****************************************************************************************************
     * This method is not recursive. Hint: Use a local queue to store the children
     * of the current node.
     *****************************************************************************************************/
    public static void displayLevelOrder(TreeNode t)
    {
	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	queue.add(t);
	while (!queue.isEmpty())
	{
	    TreeNode temp = queue.poll();
	    System.out.println(temp.getValue() + " ");

	    if (temp.getLeft() != null)// if statements are null checkers
		queue.add(temp.getLeft());
	    if (temp.getRight() != null)
		queue.add(temp.getRight());
	}
    }
} // end of TreeLab_shell

/***************************************************
 * 
 * ----jGRASP exec: java Lab01
 * 
 * E E C M N T E C I U C O S C B P A R
 * 
 * Preorder: C O P R A S B C U C I M T E N E C E Inorder: R A P B C S O C U I C
 * E T N M C E E Postorder: A R C B S P C I U O E N T C E E M C
 * 
 * Nodes = 18 Leaves = 8 Grandparents = 5 Only childs = 3
 * 
 * Depth = 6 Height = 5 Min = A Max = U
 * 
 * By Level: COMPUTERSCIENCEABC
 * 
 *******************************************************/
/* TreeNode class for the AP Exams */

class TreeNode
{
    private String value;
    private TreeNode left, right;

    public TreeNode(String initValue)
    {
	value = initValue;
	left = null;
	right = null;
    }

    public TreeNode(String initValue, TreeNode initLeft, TreeNode initRight)
    {
	value = initValue;
	left = initLeft;
	right = initRight;
    }

    public String getValue()
    {
	return value;
    }

    public TreeNode getLeft()
    {
	return left;
    }

    public TreeNode getRight()
    {
	return right;
    }

    public void setValue(String theNewValue)
    {
	value = theNewValue;
    }

    public void setLeft(TreeNode theNewLeft)
    {
	left = theNewLeft;
    }

    public void setRight(TreeNode theNewRight)
    {
	right = theNewRight;
    }

    public String toString(TreeNode t)
    {
	return t.getValue();
    }
}
