package com.Final.src.main;

public class RedBlackTree
{
    // TODO: Please check logic on all of these methods in class!
    private RedBlackNode header, current, parent, grand;

    public RedBlackTree()
    {
	header = new RedBlackNode(null);
	header.setLeft(new RedBlackNode(null));
	header.setRight(new RedBlackNode(null));
    }

    // HELPER METHODS-------------------------------------
    /**
     * Returns true if node is red, false if black
     * 
     * @param node
     *            the node being color tested
     * @return true if red, false if black
     */
    private boolean isRed(RedBlackNode node)
    {
	return node != null && node.getColor() == RedBlackNode.RED;
    }

    private int size(RedBlackNode node)
    {
	if (node == null)
	    return 0;
	return node.getSize();
    }

    public int size()
    {
	return size(header);
    }

    public boolean isEmpty()
    {
	return header == null;
    }
    // END OF HELPER METHODS------------------------

    /**
     * Standard BST search
     * 
     * @param node
     *            node to start at
     * @param value
     *            value being searched for
     * @return the value if found, null if not found
     */
    private Integer get(RedBlackNode node, Integer value)
    {
	while (node != null)
	{
	    int compare = value.compareTo(node.getValue());
	    if (compare < 0)// less than
		node = node.getLeft();
	    else if (compare > 0)// greater than
		node = node.getRight();
	    else// found it!!
		return node.getValue();
	}
	return null;
    }

    public boolean contains(Integer value)
    {
	return get(header, value) != null;
    }

    public RedBlackNode insert(Integer value, RedBlackNode node)
    {
	if (node == null)
	    return new RedBlackNode(value, 0 /* red */, null, null, 1/* size */);

	int compare = value.compareTo(node.getValue());

	// going to use recursion here to insert nodes
	if (compare < 0)
	    node.setLeft(insert(value, node.getLeft()));
	else if (compare > 0)
	    node.setRight(insert(value, node.getRight()));
	else
	    node.setValue(value);
	// fix up any skewed subtrees here
	// ... code here...

	return node;
    }

    private RedBlackNode rotateLeft(RedBlackNode node)// All O(1)!
    {
	RedBlackNode left = node.getLeft();
	node.setLeft(left.getRight());
	left.setRight(node);
	left.setColor(left.getRight().getColor());
	left.getRight().setColor(RedBlackNode.RED);
	left.setSize(node.getSize());
	node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
	return left;
    }

    private RedBlackNode rotateRight(RedBlackNode node)
    {
	RedBlackNode right = node.getRight();
	node.setRight(right.getLeft());
	right.setLeft(node);
	right.setColor(right.getLeft().getColor());
	right.getLeft().setColor(RedBlackNode.RED);
	right.setSize(node.getSize());
	node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
	return right;
    }

    private void swapParentAndChildrenColors(RedBlackNode parent)
    {
	// color is either 0 (RED) or 1 (BLACK)
	// by getting the absolute value of original colors minus one when swapping,
	// we can swap colors by doing
	// |0 (original color; RED) - 1| = |-1| = 1 (BLACK)
	// or...
	// |1 (original color; BLACK) - 1| = |0| = 0 (RED)
	parent.setColor(Math.abs(parent.getColor() - 1));
	parent.getLeft().setColor(Math.abs(parent.getLeft().getColor() - 1));
	parent.getLeft().setColor(Math.abs(parent.getLeft().getColor() - 1));
    }

    private RedBlackNode balance(RedBlackNode root)
    {
	if (isRed(root.getLeft()) && isRed(root.getRight()))
	    swapParentAndChildrenColors(root);
	
	if (isRed(root.getRight()) && isRed(root.getRight().getRight()))
	    root = rotateLeft(root);
	
	if (isRed(root.getLeft()) && isRed(root.getLeft().getLeft()))
	    root = rotateRight(root);
	
	root.setSize(size(root.getLeft()) + size(root.getRight()) + 1);
	return root;
    }

}
