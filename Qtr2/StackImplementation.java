package com.LabTests.src.Qtr2;

import java.util.Iterator;

/**
 * 
 * @author Liam Tolbert
 *
 * @param <T>
 *            the generic data type for the stack
 *            
 * Date submitted: 12/9/17
 * What I learned: 
 * - The difference between Iterable and Iterator
 * - How to use these two to implement Iterable successfully in a custom ADT
 * 
 * How I feel about this lab: Implementing stacks in this lab was the easy part. When implementing Iterable, I couldn't tell the difference
 * between it and Iterator, since the two interfaces' methods look identical. It actually took me quite a while to figure out how things 
 * between the two worked, but a family friend (an experienced developer) helped me smoothly implement the two correctly into my code. 
 */
public class StackImplementation<T> implements Iterable<ListNode<T>>
{
    private ListNode<T> head;

    public StackImplementation()
    {
	head = new ListNode<T>(null, null);
    }

    public StackImplementation(T value)
    {
	head = new ListNode<T>(value, null);
    }

    /**
     * remove() is unsupported for this class
     */
    public void remove()
    {
	throw new UnsupportedOperationException();
    }

    /**
     * pops the stack
     * 
     * @return the element that was popped from the stack
     */
    // pre: there is something inside the stack
    public T pop()
    {
	T value = head.getValue();
	head = head.getNext();
	return value;
    }
    // post: the returned value is actually popped from the stack

    /**
     * peeks at the top of the stack
     * 
     * @return the top of the stack without removing it
     */
    // pre: there is something inside the stack
    public T peek()
    {
	return head.getValue();
    }
    // post: the returned value is from the top of the stack

    /**
     * pushes a value onto the stack
     * 
     * @param value
     *            the value to push
     */
    // pre: none
    public void push(T value)
    {
	head = new ListNode<T>(value, head);
    }
    // post: the value is pushed onto the stack

    /**
     * checks if the stack is empty or not
     * 
     * @return true if the stack is empty, false if it isn't
     */
    // pre: none
    public boolean isEmpty()
    {
	return head.getValue() == null;
    }
    // post: none (doesn't actually change anything in the stack)

    /**
     * searches the stack for the specified object
     * 
     * @param obj
     *            the object to look for
     * @return the index (starting from 1 at the top of the stack) of the found
     *         object, -1 if not found
     */
    // pre: none
    public int search(T obj)
    {
	ListNode<T> i = head;
	int index = 1;
	while (i.getNext() != null)
	{
	    if (i.getValue().equals(obj))
		return index;
	    i = i.getNext();
	    index++;
	}
	return -1;
    }
    // post: the object's index is returned, -1 if not found

    public Iterator<ListNode<T>> iterator()
    {
	return new StackImplementationIterator(this.head);
    }

    public static void main(String[] args)
    {
	StackImplementation<Integer> s = new StackImplementation<>(5);
	s.push(10);
	s.push(15);
	s.push(25);
	s.push(30);
	System.out.println("15 is at " + s.search(15) + "\n");
	System.out.println("30 is at " + s.search(30) + "\n");
	System.out.println("The stack: ");
	for (ListNode<Integer> x : s)
	    System.out.println(x.getValue());
	System.out.println();
	s.pop();
	System.out.println("The stack has been popped. New stack: ");
	for (ListNode<Integer> x : s)
	    System.out.println(x.getValue());
    }

    private class StackImplementationIterator<T> implements Iterator<ListNode<T>>
    {
	ListNode<T> head;

	public StackImplementationIterator(ListNode<T> head)
	{
	    this.head = head;
	}

	@Override
	public boolean hasNext()
	{
	    return head != null;
	}

	@Override
	public ListNode<T> next()
	{
	    ListNode<T> retVal = head;
	    head = (head == null ? null : head.getNext());// null checker
	    return retVal;
	}
    }
}