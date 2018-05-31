package com.Final.src.main;
/**
 * 
 * @author Liam Tolbert
 * Date: 5/28/18
 * How I feel about this project: I never explicitly mentioned it in the report but I did have to take out deletion
 * as a function because I couldn't fix it in time to submit it (even before midnight tonight). I understand everything
 * conceptually but I have worked so hard on both insertion and deletion, but just couldn't finish debugging deletion in time. 
 * However, insertion works very well and it has been thoroughly tested. 
 * I believe that creating a simple working version is better than having a buggy and complicated version. 
 * I am happy that everything else works well enough to be demo'd. 
 */
class RedBlackNode
{
    private RedBlackNode left, right;
    private int value;
    private int color;
    public static final int BLACK = 1;
    public static final int RED = 0;

    public RedBlackNode(int value)
    {
	this(value, null, null);
    }

    public RedBlackNode(int value, RedBlackNode lt, RedBlackNode rt)
    {
	setLeft(lt);
	setRight(rt);
	setElement(value);
	setColor(1);
    }

    public int getValue()
    {
	return value;
    }

    public void setElement(int value)
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

    public RedBlackNode getLeft()
    {
	return left;
    }

    public RedBlackNode setLeft(RedBlackNode left)
    {
	this.left = left;
	return left;
    }

    public RedBlackNode getRight()
    {
	return right;
    }

    public RedBlackNode setRight(RedBlackNode right)
    {
	this.right = right;
	return right;
    }
}

class RBTree
{
    private RedBlackNode current, parent, grand, great, header;
    private static RedBlackNode nullNode;
    /* static initializer for nullNode; nullNode are the leaf nodes which contain no value */
    static
    {
	nullNode = new RedBlackNode(0);
	nullNode.setLeft(nullNode);
	nullNode.setRight(nullNode);
    }

    public RBTree(int rootNum)
    {
	header = new RedBlackNode(rootNum);
	header.setLeft(nullNode);
	header.setRight(nullNode);
    }

    /**
     * Function to check if tree is empty
     * 
     * @return true if tree is empty, false if not
     */
    public boolean isEmpty()
    {
	return header.getRight() == nullNode;
    }

    /**
     * Make the tree logically empty
     */
    public void makeEmpty()
    {
	header.setRight(nullNode);
    }

    /**
     * Function to insert item into the tree and balance it afterwards
     * 
     * @param item
     *            the value in which to insert in the tree
     */
    public void insert(int item)
    {
	current = parent = grand = header;
	nullNode.setElement(item);

	while (current.getValue() != item)
	{
	    great = grand;
	    grand = parent;
	    parent = current;
	    current = item < current.getValue() ? current.getLeft() : current.getRight();
	    // Check if two red children and fix if so
	    if (current.getLeft().getColor() == RedBlackNode.RED && current.getRight().getColor() == RedBlackNode.RED)
		balanceTree(item);
	}

	// Insertion fails if already present
	if (current != nullNode)
	    return;

	current = new RedBlackNode(item, nullNode, nullNode);

	// Attach to parent
	if (item < parent.getValue())
	    parent.setLeft(current);
	else
	    parent.setRight(current);

	balanceTree(item);
    }

    /**
     * Balances the tree and handles all the reorientation after deletion and
     * insertion
     * 
     * @param item
     *            the inserted value into the tree; tree is balanced around this
     */
    private void balanceTree(int item)
    {
	// Do the color flip
	current.setColor(RedBlackNode.RED);
	current.getLeft().setColor(RedBlackNode.BLACK);
	current.getRight().setColor(RedBlackNode.BLACK);

	if (parent.getColor() == RedBlackNode.RED)
	{
	    // Have to rotate
	    grand.setColor(RedBlackNode.RED);
	    if (item < grand.getValue() != item < parent.getValue())
		parent = rotate(item, grand); // Start rotate
	    current = rotate(item, great);
	    current.setColor(RedBlackNode.BLACK);
	}
	// Make root black
	header.getRight().setColor(RedBlackNode.BLACK);
    }

    /**
     * Rotates nodes in a direction according to item and parent.getValue()
     * 
     * @param item
     *            the inserted value into the tree; tree is balanced around this
     * @param parent
     *            the parent where the rotating process is
     * @return the new chlid of parent after rotation
     */
    private RedBlackNode rotate(int item, RedBlackNode parent)
    {
	if (item < parent.getValue())
	    return parent.setLeft(
		    item < parent.getLeft().getValue() ? rotateLeft(parent.getLeft()) : rotateRight(parent.getLeft()));
	else
	    return parent.setRight(item < parent.getRight().getValue() ? rotateLeft(parent.getRight())
		    : rotateRight(parent.getRight()));
    }

    /**
     * Performs left rotation with node
     * 
     * @param node
     *            the node where the left rotation is taking place
     * @return the new parent of node after rotation
     */
    private RedBlackNode rotateLeft(RedBlackNode node)
    {
	RedBlackNode k = node.getLeft();
	node.setLeft(k.getRight());
	k.setRight(node);
	return k;
    }

    /**
     * Performs right rotation with node
     * 
     * @param node
     *            the node where the right rotation is taking place
     * @return the new parent of node after rotation
     */
    private RedBlackNode rotateRight(RedBlackNode node)
    {
	RedBlackNode k = node.getRight();
	node.setRight(k.getLeft());
	k.setLeft(node);
	return k;
    }

    public int countNodes()
    {
	return countNodes(header);
    }

    /**
     * Counts the number of nodes in the tree
     * 
     * @param r
     *            the root of the tree
     * @return the number of nodes
     */
    private int countNodes(RedBlackNode r)
    {
	if (r == nullNode)
	    return 0;
	else
	{
	    int l = 1;
	    l += countNodes(r.getLeft());
	    l += countNodes(r.getRight());
	    return l;
	}
    }

    public boolean search(int val)
    {
	return search(header.getRight(), val);
    }

    /**
     * Function to search for a certain value
     * 
     * @param r
     *            root of tree
     * @param val
     *            value being searched for
     * @return true if found, false if not
     */
    private boolean search(RedBlackNode r, int val)
    {
	boolean found = false;
	while ((r != nullNode) && !found)
	{
	    int rval = r.getValue();
	    if (val < rval)
		r = r.getLeft();
	    else if (val > rval)
		r = r.getRight();
	    else
	    {
		found = true;
		break;
	    }
	    found = search(r, val);
	}
	return found;
    }

    public void inorder()
    {
	inorder(header.getRight());
    }

    /**
     * Inorder traversal
     * 
     * @param r
     *            root of the tree
     */
    private void inorder(RedBlackNode r)
    {
	if (r != nullNode)
	{
	    inorder(r.getLeft());
	    char c = 'B';
	    if (r.getColor() == 0)
		c = 'R';
	    System.out.print(r.getValue() + "" + c + " ");
	    inorder(r.getRight());
	}
    }

    public void preorder()
    {
	preorder(header.getRight());
    }

    /**
     * Preorder traversal
     * 
     * @param r
     *            root of tree
     */
    private void preorder(RedBlackNode r)
    {
	if (r != nullNode)
	{
	    char c = 'B';
	    if (r.getColor() == 0)
		c = 'R';
	    System.out.print(r.getValue() + "" + c + " ");
	    preorder(r.getLeft());
	    preorder(r.getRight());
	}
    }

    public void postorder()
    {
	postorder(header.getRight());
    }

    /**
     * Postorder traversal
     * 
     * @param r
     *            root of tree
     */
    private void postorder(RedBlackNode r)
    {
	if (r != nullNode)
	{
	    postorder(r.getLeft());
	    postorder(r.getRight());
	    char c = 'B';
	    if (r.getColor() == 0)
		c = 'R';
	    System.out.print(r.getValue() + "" + c + " ");
	}
    }
}