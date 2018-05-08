package com.Final.src.main;

public class RedBlackTree
{
    public static final int BLACK = 1;
    public static final int RED = 0;
    
    private RedBlackNode header;
    private RedBlackNode nullNode;
    public RedBlackTree()
    {
	header = new RedBlackNode(null);
	header.setLeft(nullNode);
	header.setRight(nullNode);
	
	nullNode = new RedBlackNode(null);
	nullNode.setLeft(nullNode);
	nullNode.setRight(nullNode);
    }
}
