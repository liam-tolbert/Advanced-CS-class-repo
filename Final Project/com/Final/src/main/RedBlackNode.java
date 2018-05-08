package com.Final.src.main;

public class RedBlackNode
{
    private RedBlackNode left, right;
    private int color;
    private Integer value;
    
    public RedBlackNode(Integer value)
    {
	this(value, null, null);
    }

    public RedBlackNode(Integer value, RedBlackNode leftChild, RedBlackNode rightChild)
    {
	this.setValue(value);
	setLeft(leftChild);
	setRight(rightChild);
	setColor(RedBlackTree.BLACK);
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
}
