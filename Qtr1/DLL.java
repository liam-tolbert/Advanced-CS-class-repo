package com.LabTests.src.Qtr1;
/***********************************************************************************************************************************************
 * Name: Liam Tolbert              
 * Period: 1
 * Name of the Lab: DLL Lab
 * Purpose of the Program: To fiddle with Doubly Linked Lists
  * Due Date: 10/7/2017
 * Date Submitted: 10/6/2017
 * What I learned: 
 * 1. How to represent header and tail nodes without causing any NullPointer exceptions. 
 * 2. How to painstakingly add and delete nodes anywhere in the list 
 * How I feel about this lab: This lab was relatively simpler; however, it was still frustrating to do until I figured out how to do the add method correctly
 * What I wonder: none this time
 * Student(s) who helped me (to what extent): N/A  
 * Student(s) whom I helped (to what extent):
 *************************************************************************************************************************************************/
public class DLL<E>
{
    private class DLNode<E>
    {
	private E value;
	private DLNode<E> prev;
	private DLNode<E> next;

	public DLNode(E arg, DLNode<E> p, DLNode<E> n)
	{
	    value = arg;
	    prev = p;
	    next = n;
	}

	public DLNode()
	{
	    value = null;
	    next = null;
	    prev = null;
	}

	public E getValue()
	{
	    return value;
	}

	public void setValue(E arg)
	{
	    value = arg;
	}

	public void setNext(DLNode<E> arg)
	{
	    next = arg;
	}

	public void setPrev(DLNode<E> arg)
	{
	    prev = arg;
	}

	public DLNode<E> getNext()
	{
	    return next;
	}

	public DLNode<E> getPrev()
	{
	    return prev;
	}
    }

    private DLNode<E> head;// dummy node--very useful--simplifies the code
    private DLNode<E> tail;
    private int size = 0;

    public DLL()
    {
	head = new DLNode<E>(/* null, null, null */);
	tail = new DLNode<E>(null, head, null);
	head.setNext(tail);
    }

    /**
     * gives the size of the list (not including the head and tail nodes)
     * 
     * @return the size of the list
     */
    public int size()
    {
	return size;
    }
    
    /**
     * says of the list is empty or not
     * 
     * @return if the list of empty
     */
    public boolean empty()
    {
	return size == 0;
    }
    
    /**
     * gives the first value of the list
     * 
     * @return the first value of the list
     */
    public E getFirstValue()
    {
	if (!empty())
	    return head.getNext().getValue();
	return null;
    }
    
    /**
     * gives the last value of the list
     * 
     * @return the last value of the list
     */
    public E getLastValue()
    {
	if (!empty())
	    return tail.getPrev().getValue();
	return null;
    }
    
    /**
     * a helper method to add a node between a predecessor node and a successor node
     * size is incremented by 1
     * 
     * @param obj the value to add in
     * @param pred the predecessor node
     * @param succ the successor node
     */
    // pre: all parameters are valid representations of themselves and not null
    private void add(E obj, DLNode<E> pred, DLNode<E> succ/* xd */)
    {
	DLNode<E> x = new DLNode<>(obj, pred, succ);
	pred.setNext(x);
	succ.setPrev(x);
	size++;
    }// post: x is added to a list

    /**
     * appends obj to end of list; increases size;
     * 
     * @return true
     */
    public boolean add(E obj)
    {
	add(obj, tail.getPrev(), tail);
	return true;
    }

    /**
     * inserts obj at position index. increments size.
     * 
     * 
     */
    //pre: index and obj are valid ints and E's, respectively and not null
    public void add(int index, E obj)
    {
	int count = -1;
	for (DLNode<E> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (count == index)
	    {
		add(obj, tmp, tmp.getNext());
		break;
	    } else
	    {
		count++;
	    }
	}
    }//post: E is set to position index

    /**
     * return obj at position index, otherwise null
     * 
     * @return the value at position index
     */
    //pre: index is a valid int and not null
    public E get(int index)
    {
	int count = -1;
	for (DLNode<E> tmp = head; tmp.getNext().getValue() != null; tmp = tmp.getNext())
	{
	    if (count == index)
	    {
		return tmp.getValue();
	    } else
	    {
		count++;
	    }
	}
	return null;
    }//post: the value returned is the object at position index

    /**
     * replaces obj at position index.
     */
    //pre: index and obj are valid ints and E's, respectively, and not null
    public void set(int index, E obj)
    {
	int count = -1;
	for (DLNode<E> tmp = head; tmp.getNext().getValue() != null; tmp = tmp.getNext())
	{
	    if (count == index)
	    {
		tmp.setValue(obj);
	    } else
	    {
		count++;
	    }
	}
    }//post: obj is set to position index

    /**
     * removes the node from position index. decrements size.
     * 
     * @param index the index of the node to be removed
     * 
     * @return the object at position index.
     */
    //pre: index is a valid int and not null
    public E remove(int index)
    {
	int count = -1;
	E value = null;
	for (DLNode<E> tmp = head; tmp.getNext().getValue() != null; tmp = tmp.getNext())
	{
	    if (count == index)
	    {
		value = tmp.getValue();
		tmp.getNext()/* successor */.setPrev(tmp.getPrev()/* predecessor */);
		tmp.getPrev()/* predecessor */.setNext(tmp.getNext()/* successor */);
		size--;
		return value;
	    } else
	    {
		count++;
	    }
	}
	return null;
    }// post: the node at position index is removed

    /**
     * inserts obj at front of list; increases size;
     * 
     * @param obj the value of the element to be added
     */
    //pre: obj is a valid E and not null
    public void addFirst(E obj)
    {
	add(obj, head, head.getNext());
    }

    /**
     * appends obj to end of list; increases size;
     * 
     * @param obj the value of the element to be added
     */
    // pre: obj is a valid E and not null
    public void addLast(E obj)
    {
	add(obj, tail.getPrev(), tail);
    }

    public E getFirst()
    {
	return head.getNext().getValue();
    }

    public E getLast()
    {
	return tail.getPrev().getValue();
    }

    public E removeFirst()
    {
	E value = head.getNext().getValue();
	head.setNext(head.getNext().getNext());
	return value;

    }

    public E removeLast()
    {
	E value = tail.getPrev().getValue();
	tail.setPrev(tail.getPrev().getPrev());
	return value;
    }
    /**
     * a string representation of the list
     */
    public String toString()
    {
	String s = "";
	for (DLNode<E> tmp = head.getNext(); tmp.getValue() != null; tmp = tmp.getNext())
	{
	    s += tmp.getValue() + " ";
	}
	return s;
    }

    public static void main(String args[])
    {
	DLL<String> list = new DLL<String>();

	list.addLast("Apple");
	list.addLast("Banana");
	list.addLast("Cucumber");
	list.add("Dumpling");
	list.add("Escargot");
	System.out.println(list);
	System.out.println("Size: " + list.size());
	Object obj = list.remove(3);
	System.out.println(list);
	System.out.println("Size: " + list.size());
	System.out.println("Removed " + obj);
	System.out.print("Add at 3:   ");
	list.add(3, "Cheese");
	System.out.println(list);
	System.out.println("Get values at 1 and first: " + list.get(1) + " and " + list.getFirst());
	System.out.println("No change: " + list);
	System.out.println(list.removeFirst() + " is now removed!");
	System.out.println(list);
	System.out.print("Add first:  ");
	list.addFirst("Anchovie");
	System.out.println(list);
	System.out.println("Size: " + list.size());
	System.out.print("Set the second:  ");
	list.set(2, "Rread");
	System.out.println(list);
    }
    /*
     * Apple Banana Cucumber Dumpling Escargot 
Size: 5
Apple Banana Cucumber Escargot 
Size: 4
Removed Dumpling
Add at 3:   Apple Banana Cucumber Escargot Cheese 
Get values at 1 and first: Banana and Apple
No change: Apple Banana Cucumber Escargot Cheese 
Apple is now removed!
Banana Cucumber Escargot Cheese 
Add first:  Anchovie Banana Cucumber Escargot Cheese 
Size: 6
Set the second:  Anchovie Banana Rread Rread Cheese 

     */
}
