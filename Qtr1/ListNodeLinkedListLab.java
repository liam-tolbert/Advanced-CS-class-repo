/**
* Name: Liam Tolbert
* Period: 7th
* Name of the Lab: ListNodeLinkedListLab
* Purpose of the Program: Writing basic raw singly linked lists
* Due Date: 9/25/2017
*  Date Submitted: 9/25/2017
* What I learned: I learned all the ins and outs of singly linked lists. I see how they are extremely useful, but I also see
* the major loopholes in the data structure such as accessing the end of the list (you can only use for loops) and the need
* to have to use sequential searches to find anything in the list. I also hate how you cannot access a previous node through 
* a node you are looping through so you have to use tmp.getNext().getNext()(which can be really confusing) in order to predict
* the position of a node. 
* How I feel about this lab: A little frustrated because singly linked lists are kind of clunky to work with, but it was still
* fun. 
* What I wonder: Why are singly linked lists still in use when doubly linked lists are better? 
* Credits: Stackoverflow for the reverse method, the CS textbook for the addLast (because I ran into some weird infinite loop 
* with my old method) method
* Students whom I helped (to what extent): None
*/
package com.LabTests.src.Qtr1;

import java.util.Scanner;

import com.LabTests.src.Qtr1.ListNodeLinkedListLab.ListNode;

public class ListNodeLinkedListLab
{

    public static class ListNode<E>
    {
	private E value;
	private ListNode<E> next;

	public ListNode(E initValue, ListNode<E> initNext)
	{
	    value = initValue;
	    next = initNext;
	}

	public E getValue()
	{
	    return value;
	}

	public ListNode<E> getNext()
	{
	    return next;
	}

	public void setValue(E theNewValue)
	{
	    value = theNewValue;
	}

	public void setNext(ListNode<E> theNewNext)
	{
	    next = theNewNext;
	}
	
	public void setLast()
	{
	    
	}
    } // end of ListNode

    public static void main(String[] args)
    {
	Scanner input = new Scanner(System.in);
	ListNode<Integer> h = new ListNode(5, null);
	h = new ListNode(4, h);
	h = new ListNode(3, h);
	h = new ListNode(2, h);
	h = new ListNode(1, h);

	char option;
	do
	{
	    option = menu();
	    if (option == 'a')
	    {
		System.out.println("list: ");
		printLinkedList(h);
	    } else if (option == 'b')
	    {
		System.out.println("The List has atleast two element?");
		System.out.println(hasTwo(h));
	    } else if (option == 'c')
	    {
		System.out.print("The size of the array is: ");
		System.out.println(size(h));
	    } else if (option == 'd')
	    {
		h = removeFirst(h);
		System.out.print("New list is: ");
		printLinkedList(h);
	    } else if (option == 'e')
	    {
		System.out.println("Enter number: ");
		int num = input.nextInt();
		System.out.println("djfskjfksjdfkjsdf");
		h = add(h, new Integer(num));
		System.out.println("New list is: ");
		printLinkedList(h);
	    } else if (option == 'f')
	    {
		h = reverseList(h);
		System.out.println("Reverse is: ");
		printLinkedList(h);
	    } else if (option == 'g')
	    {
		h = rotate(h);
		System.out.println("rotated array is: ");
		printLinkedList(h);
	    } else if (option == 'h')
	    {
		printLinkedList(h);
		System.out.println("\nmiddle node is: " + middleNode(h).getValue());
	    }

	    else if (option == 'i')
	    {
		h = removeLast(h);
		System.out.print("New list is: ");
		printLinkedList(h);
	    }

	} while (option != 'z');

    } // end of main

    /**
     * Detects if the LinkedList has two or more elements
     * 
     * @param h
     *            the head node of the LinkedList
     * @return boolean if LinkedList has two or more elements
     */
    // pre: h is declared and is a valid ListNode<Integer>
    private static boolean hasTwo(ListNode<Integer> h)
    {
	int count = 0;
	for (ListNode<Integer> tmp = h; tmp != null; tmp = tmp.getNext())
	    count++;
	return count > 2;
    }// post: count

    /**
     * merges lists that are already sorted into a larger sorted list
     * 
     * @param node1
     *            the first list
     * @param node2
     *            the second list
     * @return the merged, sorted list
     */
    // pre: node1 and node2 are valid LisNode<Integer>s
    public static ListNode<Integer> mergeSortedLists(ListNode<Integer> node1, ListNode<Integer> node2)
    {
	ListNode<Integer> newNode = new ListNode<Integer>(null, null);
	while (node1.getValue() == null || node2.getValue() == null)
	{
	    if (node1.getValue() == null)
		add(newNode, node2.getValue());
	    else if (node2.getValue() == null)
		add(newNode, node1.getValue());
	    else if (node1.getValue() > node2.getValue())
		add(newNode, node2.getValue());
	    else if (node1.getValue() < node2.getValue())
		add(newNode, node1.getValue());
	}
	return newNode;
    }// post: newNode contains node1 and node2, merged and sorted

    /**
     * Prints a linked list in order of index
     * 
     * @param head
     *            The head of the list being printed
     */
    // pre: head is a valid ListNode<Integer>
    public static void printLinkedList(ListNode<Integer> head)
    {
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    System.out.println(tmp.getValue());
	}
    }// post: a list of elements in the linked list is printed into the console

    /**
     * gets rid of the first node of the linkedlist
     * 
     * @param head
     *            the head of the linked list
     * @return the list without the first element
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> removeFirst(ListNode<Integer> head)
    {
	return head.getNext(); // does this work?
    }// post: the returned list is head without the first node

    /**
     * gets rid of the last node of the linkedlist
     * 
     * @param head
     *            the head of the linkedlist
     * @return the list without the last node
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> removeLast(ListNode<Integer> head)
    {
	ListNode<Integer> tmp;
	for (tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (tmp.getNext()
		    .getNext() == null)/* checking if the last node.getNext() is null without actually looping to it */
	    {
		tmp = remove(head, tmp.getNext().getValue());
		return tmp;
	    }
	}
	return null;
    }// post: x is head without the last node

    /**
     * finds and removes a node with a given value in a linkedlist
     * 
     * @param head
     *            the head of the linkedlist
     * @param value
     *            the value of the node scheduled for removal
     * @return a list that has the given node removed completely
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> remove(ListNode<Integer> head, Integer value)
    {
	ListNode<Integer> dummyHead = new ListNode<>(head.getValue(), head.getNext());
	if (head != null)
	{
	    if (head.getValue().equals(value))
	    {
		return head.getNext();
	    }

	    for (ListNode<Integer> next = head.getNext(); next != null; next = head.getNext())
	    {
		if (next.getValue().equals(value))
		{
		    head.setNext(next.getNext());
		    return dummyHead;
		} else
		{
		    head = next;
		}
	    }
	}
	return null;
    }// post: the list returned has the node with the given value removed

    /**
     * deep copies a linkedlist and returns it
     * 
     * @param head
     *            the linked list
     * @return a reference to the deep copy of head
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> copy(ListNode<Integer> head)
    {
	ListNode<Integer> x = new ListNode<>(null, null);
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    add(x, head.getValue());
	}
	return x;
    }// post: x is a reference to a deep copy of head

    /**
     * inserts a node into a linkedlist in order
     * 
     * @param head
     *            the linkedlist to have something inserted into
     * @param value
     *            the value that will be combined with a node and inserted into head
     * @return a linkedlist with the value inserted
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> insertInOrder(ListNode<Integer> head, Integer value)
    {
	ListNode<Integer> tmp;
	for (tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (tmp.getValue() > value)
	    {
		ListNode<Integer> x = tmp.getNext().getNext();// the node that is after the inserted node
		tmp.setNext(new ListNode<>(value, x));
	    }
	}
	return tmp;
    }// post: tmp is a linkedlist with value inserted into it

    /**
     * appends a linkedlist on the end of another one
     * 
     * @param node1
     *            the first linkedlist
     * @param node2
     *            the second linkedlist
     * @return a linkedlist with both lists in it
     */
    // pre: node1 and node2 are both valid ListNode<Integer>s
    public static ListNode<Integer> append(ListNode<Integer> node1, ListNode<Integer> node2)
    {
	ListNode<Integer> tmp;
	for (tmp = node1; tmp != null; tmp = tmp.getNext())
	{
	    if (tmp.getNext() == null)
	    {
		tmp.setNext(node2);
	    }
	}
	return tmp;
    }// post: tmp contains both lists

    /**
     * finds an element with a given value in the linked list
     * 
     * @param head
     *            the head of the linked list
     * @param value
     *            the value that the method wants to find in the linked list
     * @return
     */
    // pre: head is a valid ListNode<Integer> and value is a valid Integer
    public static ListNode<Integer> find(ListNode<Integer> head, Integer value)
    {
	ListNode<Integer> tmp;
	for (tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (tmp.getValue().equals(value))
	    {
		return tmp;
	    }
	}
	return null;
    }// post: tmp is the node returned when it is found, otherwise null

    /**
     * finds an element of a linkedlist by index
     * 
     * @param head
     *            the head of the linkedlist
     * @param index
     *            the index of the element being searched for
     * @return the node being searched for; if there is no such node, return null
     */
    // pre: head is a valid ListNode<Integer> and index is a valid integer
    public static ListNode<Integer> findWithIndex(ListNode<Integer> head, Integer index)
    {
	ListNode<Integer> tmp;
	int count = 0;
	for (tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (count == index)
	    {
		return tmp;
	    }
	    count++;
	}
	return null;
    }

    /**
     * adds a node to the end of a linkedlist
     * 
     * @param head
     *            the head node of the linkedlist
     * @param value
     *            the value of the node being added
     */
    // pre: head and value are both valid ListNode<Integer> and Integer respectively
    public static ListNode<Integer> add(ListNode<Integer> head, Integer value)
    {
	ListNode<Integer> newest = new ListNode<>(value, null);
	ListNode<Integer> tail = new ListNode<>(null, null);
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (tmp.getNext() == null)
	    {
		tail = tmp;
		break;
	    }
	}
	tail.setNext(newest);
	return head;
    }// post: a node with the correct value in it is added to the end of the list
    
    /**
     * reverses the list and returns it
     * 
     * @param head
     *            the head node of the linkedlist
     * @return a reversed version of the linkedlist
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> reverseList(ListNode<Integer> head)
    {
	ListNode<Integer> x = head;
	ListNode<Integer> currNode = x;
	ListNode<Integer> nextNode = null;
	ListNode<Integer> prevNode = null;
	while (currNode != null)
	{
	    nextNode = currNode.getNext();
	    currNode.setNext(prevNode);
	    prevNode = currNode;
	    currNode = nextNode;
	}
	x = prevNode;
	return x;
    }// post: x is a reversed version of head's list

    /**
     * rotates an element from the front to the back
     * 
     * @param head
     *            the head node of the linkedlist
     * @return the rotated list
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> rotate(ListNode<Integer> head)
    {
	ListNode<Integer> lastNode = new ListNode<>(null, null);
	ListNode<Integer> newList = new ListNode<>(null, null);
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (tmp.getNext() == null)
	    {
		lastNode.setValue(tmp.getValue());
		newList = remove(head, tmp.getValue());
	    }
	}
	lastNode.setNext(newList);
	return lastNode;
    }// post: the returned linkedlist is a rotated version of head

    /**
     * finds the middle node in a linkedlist
     * 
     * @param head
     *            the head node for the linkedlist
     * @return the middle node of head's linkedlist
     */
    // pre: head is a valid ListNode<Integer>
    public static ListNode<Integer> middleNode(ListNode<Integer> head)
    {
	int length = size(head);
	int count = 0;
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (count == length / 2)
	    {
		return tmp;
	    }
	    count++;
	}
	return head; // is this necessary
    }// post: head is the middle node of the list

    /**
     * returns the length of a linkedlist
     * 
     * @param head
     *            the head of the linkedlist
     * @return the length of the linkedlist
     */
    // pre: head is a valid ListNode<Integer>
    public static int size(ListNode<Integer> head)
    {
	int count = 0;
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    count++;
	}
	return count;
    }// post: count represents the length of the linked list

    public static char menu()
    {
	Scanner input = new Scanner(System.in);
	System.out.println("\n====> What would you like to do?");
	System.out.println("a) Print list");
	System.out.println("b) Check if list has at least two nodes");
	System.out.println("c) Get size of the list");
	System.out.println("d) Remove first node");
	System.out.println("e) Add a node");
	System.out.println("f) Reverse");
	System.out.println("g) Rotate");
	System.out.println("h) Get middle node");
	System.out.println("i) Remove last node");
	System.out.println("z) Quit?");
	String choice = input.next();
	return choice.charAt(0);
    } // end of menu
}
/*
 * a
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? a list: 1 2
 * 3 4 5
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? b list: 1 2
 * 3 4 5
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? c The size
 * of the array is: 5
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? d New list
 * is: 2 3 4 5
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? e Enter
 * number: 6 djfskjfksjdfkjsdf New list is: 2 3 4 5 6
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? f Reverse
 * is: 6 5 4 3 2
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? g rotated
 * array is: 2 6 5 4 3
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? h 2 6 5 4 3
 * 
 * middle node is: 5
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? i New list
 * is: 2 6 5 4
 * 
 * ====> What would you like to do? a) Print list b) Check if list has at least
 * two nodes c) Get size of the list d) Remove first node e) Add a node f)
 * Reverse g) Rotate h) Get middle node i) Remove last node z) Quit? z
 */