package com.Final.src.main;

public class RedBlackNode
{
    public static final int BLACK = 1;
    public static final int RED = 0;
    
    private RedBlackNode left, right;
    private int color;
    private Integer value;
    private int size; // subtree height
    
    public RedBlackNode(Integer value)
    {
	this(value, RED, null, null, 0);
    }

    public RedBlackNode(Integer value, int color, RedBlackNode leftChild, RedBlackNode rightChild, int size)
    {
	setValue(value);
	setLeft(leftChild);
	setRight(rightChild);
	setColor(RED);
	setSize(size);
    }

    public RedBlackNode getLeft()
    {
	return left;
    }

    public void setLeft(RedBlackNode left)
    {
	this.left = left;
    }

    public RedBlackNode getRight()
    {
	return right;
    }

    public void setRight(RedBlackNode right)
    {
	this.right = right;
    }

    public Integer getValue()
    {
	return value;
    }

    public void setValue(Integer value)
    {
	this.value = value;
    }

    public int getColor()
    {
	return color;
    }

    public void setColor(int color)
    {
	this.color = color;
    }
    
    public boolean equals(RedBlackNode other)
    {
	return other.getValue().equals(this.getValue());
    }

    public int getSize()
    {
	return size;
    }

    public void setSize(int size)
    {
	this.size = size;
    }
}
