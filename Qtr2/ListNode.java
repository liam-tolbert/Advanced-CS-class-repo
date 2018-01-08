package com.LabTests.src.Qtr2;

public class ListNode<E>
{
    /**
     * @author Liam Tolbert
     * 
     * A helper ListNode class for StackImplementation.java
     */
    private E value;
    private ListNode<E> nextNode;
    private int size;

    public ListNode(E value, ListNode<E> next)
    {
	this.setValue(value);
	this.setNext(next);
	for (ListNode<E> i = this; i != null; i = i.getNext())
	    size++;
    }

    public E getValue()
    {
	return value;
    }

    public ListNode<E> getNext()
    {
	return nextNode;
    }

    public void setValue(E value)
    {
	this.value = value;
    }

    public void setNext(ListNode<E> node)
    {
	this.nextNode = node;
    }

    public int getSize()
    {
	return size;
    }
}
