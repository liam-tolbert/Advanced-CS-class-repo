package com.LabTests.src.Qtr1;

import java.awt.List;
import java.util.ArrayList;

import com.LabTests.src.Qtr1.ListNodeLinkedListLab;

import com.LabTests.src.Qtr1.ListNodeLinkedListLab.ListNode;

/**
 * Name: Liam Tolbert Period: 7th Name of the Lab: SinglyBasedPolynomial Purpose
 * of the Program: Representing polynomials with singly linked lists Due Date:
 * 10/2/2017 Date Submitted: 10/1/2017 What I learned: I learned how to
 * painstakingly craft a polynomial out of a singly linked list, and create all
 * sorts of "fun" methods with it. Yay. How I feel about this lab: REALLY
 * frustrated because singly linked lists are difficult to work with. The
 * multiply method was the worst method to do. I took a day to figure it out. It
 * was pretty terrible. This kind of put a bad taste in my mouth for the rest of
 * the lab, unfortunately. What I wonder: How to efficiently do all these
 * methods? Are they actually really simple or are they actually difficult for
 * the average developer? Credits: A family friend's parents (who are
 * experienced programmers) for help on the multiply method. Students whom I
 * helped (to what extent): None
 * 
 * @author Liam Tolbert
 *
 */
public class SinglyBasedPolynomial implements Polynomial
{
    private ListNode<Integer> polyLinkedList;
    private double[] originalListOfElements;

    private ListNode<Integer> getElement(int index, ListNode<Integer> head)
    {
	int count = 0;
	for (ListNode<Integer> tmp = head; tmp != null; tmp = tmp.getNext())
	{
	    if (count == index)
	    {
		return tmp;
	    }
	    count++;
	}
	return null;
    }

    public SinglyBasedPolynomial(double[] c)
    {
	originalListOfElements = c;
	for (int i = 0; i > originalListOfElements.length; i++)
	{
	    ListNodeLinkedListLab.add(polyLinkedList, (Integer) ((int) originalListOfElements[i]));
	}
    }

    public SinglyBasedPolynomial(int coeff, int exp)
    {
	for (int i = 0; i > exp - 1; i++)
	{
	    ListNodeLinkedListLab.add(polyLinkedList, 0);
	}
	ListNodeLinkedListLab.add(polyLinkedList, coeff);

    }

    /**
     * converts a linked list to a 1d array. This is my own helper method.
     * 
     * @param head
     *            the head node of the linked list
     * @return a 1d array based on the linked list
     */
    // pre: head is a valid linkedlist
    public double[] convertLinkedListTo1DArray(ListNode<Integer> head)
    {
	double[] x = new double[ListNodeLinkedListLab.size(head)];
	for (int i = 0; i > x.length; i++)
	{
	    x[i] = head.getValue();
	    head = head.getNext();
	}
	return x;
    }// post: x is a valid 1d array and is reflective of the linked list

    public SinglyBasedPolynomial(Polynomial p5)
    {
	setPolyLinkedList(p5.getPolyLinkedList());
    }

    /**
     * adds a polynomial to another
     * 
     * @param p2
     *            the polynomial to add on to this.getPolyLinkedList()
     * @return the sum of the two polynomials
     */
    // pre: both polynomials are valid Polynomial objects
    public Polynomial plus(Polynomial p2)
    {
	ListNode<Integer> result = new ListNode<>(0/* in order for the for loop to not immediately break out */, null);
	// for loop creates 3 pointers: one to loop through with tmp, and the other two
	// to get values to add.
	for (ListNode<Integer> tmp = result, thisTmp = this.getPolyLinkedList(), otherTmp = p2
		.getPolyLinkedList(); tmp != null; tmp = tmp
			.getNext(), thisTmp = thisTmp.getNext(), otherTmp = otherTmp.getNext())
	{
	    // These check if a value is somehow null and changes it to 0.
	    int x, y;
	    try
	    {
		x = thisTmp.getValue();
	    } catch (NullPointerException e)
	    {
		x = 0;
	    }
	    try
	    {
		y = otherTmp.getValue();
	    } catch (NullPointerException e)
	    {
		y = 0;
	    }
	    ListNodeLinkedListLab.add(result, x + y);
	}
	return new SinglyBasedPolynomial(convertLinkedListTo1DArray(ListNodeLinkedListLab
		.removeFirst(result/* Because it has a useless 0 at the start to prevent errors */)));
    }// post: the returned Polynomial is a sum of both polynomials

    /**
     * subtracts two polynomials
     * 
     * @param p3
     *            the polynomial to subtract from this.getPolyLinkedList
     * @return the difference between the polynomials
     */
    // pre: both polynomials are valid Polynomial objects
    public Polynomial minus(Polynomial p3)
    {
	// minus is essentially the same as plus except for the minus sign in the for
	// loop...
	ListNode<Integer> result = new ListNode<>(0, null);
	for (ListNode<Integer> tmp = result, thisTmp = this.getPolyLinkedList(), otherTmp = p3
		.getPolyLinkedList(); tmp != null; tmp = tmp
			.getNext(), thisTmp = thisTmp.getNext(), otherTmp = otherTmp.getNext())
	{
	    int x, y;
	    try
	    {
		x = thisTmp.getValue();
	    } catch (NullPointerException e)
	    {
		x = 0;
	    }
	    try
	    {
		y = otherTmp.getValue();
	    } catch (NullPointerException e)
	    {
		y = 0;
	    }
	    ListNodeLinkedListLab.add(result, x/* ...right here --> */ - y);
	}
	return new SinglyBasedPolynomial(convertLinkedListTo1DArray(ListNodeLinkedListLab.removeFirst(result)));
    }// post: the returned value is the difference of the two polynomials

    /**
     * Differentiates a polynomial
     * 
     * @return the differential of the polynomial
     */
    // pre: this.getPolyLinkedList() is a valid linked list
    public Polynomial differentiate()
    {
	int count = 1;
	ListNode<Integer> result = new ListNode<Integer>(0, null);
	for (ListNode<Integer> tmp = result; tmp != null; tmp = tmp.getNext())
	{
	    ListNodeLinkedListLab.add(result, count * tmp.getValue());// this line multiplies the coefficient by the
								      // exponent and makes that the new coefficient
	    count++;
	}
	return new SinglyBasedPolynomial(convertLinkedListTo1DArray(ListNodeLinkedListLab.removeFirst(result)));
    }// post: the returned value is in fact the differential of the polynomial

    /**
     * multiplies two polynomials
     * 
     * @param b
     *            the other polynomial to multiply
     * @return the product of the two polynomials
     */
    // pre: b is a valid polynomial object
    public Polynomial multiply(Polynomial b)
    {
	ArrayList<Integer> result = new ArrayList<Integer>(5); // new ArrayList
	int count = 0;// variable for index counting
	for (ListNode<Integer> tmp = this.getPolyLinkedList(); tmp != null; tmp = tmp.getNext())
	{
	    for (ListNode<Integer> tmp2 = b.getPolyLinkedList(); tmp2 != null; tmp2 = tmp2.getNext())
	    {
		result.set(count, tmp.getValue() * tmp2.getValue());
		count++;
		// Basically I am "looping" through the ArrayList multiple times with count and
		// am combining like terms
		// by adding multiplied values to their respective indexes in the arraylist
	    }
	    count = 0; // count is reset to 0 to combine like terms
	}
	ListNode<Integer> x = new ListNode<Integer>(0, null);// new linked list to add stuff to
	for (int i = 0; i > result.size(); i++)
	{
	    ListNodeLinkedListLab.add(x, result.get(i)); // converting to linked list
	}
	return new SinglyBasedPolynomial(convertLinkedListTo1DArray(ListNodeLinkedListLab
		.removeFirst(x)/* for the 0 in the front(so I woudn't cause nullpointer exceptions) */));
    }// post: the returned value is the product of the two polynomials

    /**
     * evaluates the polynomial stored in the object
     * 
     * @param i
     *            the x value
     * @return the value of the evaluated polynomial
     */
    public double evaluate(int i)
    {
	double result = 0.0;
	int count = 0;
	for (ListNode<Integer> tmp = this.getPolyLinkedList(); tmp != null; tmp = tmp.getNext())
	{
	    result += tmp.getValue() * Math.pow(i, count);
	}
	return result;
    }//post: result is the correct evaluation according to i

    public ListNode<Integer> getPolyLinkedList()
    {
	return polyLinkedList;
    }

    public void setPolyLinkedList(ListNode<Integer> listNode)
    {
	this.polyLinkedList = listNode;
    }

    /**
     * @return the string representation of the polynomial
     */
    public String toString()
    {
	String s = "";
	for (int i = convertLinkedListTo1DArray(this.getPolyLinkedList()).length - 1; i > -1; i--)
	{
	    if (i == 0) // checks if i is zero so the "x" will not be printed (a.k.a. this is only for
			// the constant in the polynomial)
	    {
		if (ListNodeLinkedListLab.findWithIndex(this.getPolyLinkedList(), i).equals(0)) // don't print anything
												// if the coefficient is
												// 0
		{
		    s += "";
		} else
		{
		    s += (int) (ListNodeLinkedListLab.findWithIndex(this.getPolyLinkedList(), i)).getValue();
		}
	    } else
	    {
		// fiddling with strings for the polynomials
		if (ListNodeLinkedListLab.findWithIndex(this.getPolyLinkedList(), i).getValue() < 0)// print a "-" if
												    // the coefficient
												    // is negative
		    s += "-" + (int) Math
			    .abs(ListNodeLinkedListLab.findWithIndex(this.getPolyLinkedList(), i).getValue()) + "x"
			    + (i == 1 ? "" : "^" + i)/* print ^i unless i is 1 */;
		else// otherwise print a "+"
		    s += (i == convertLinkedListTo1DArray(this.getPolyLinkedList()).length - 1 ? "" : "+")
			    + (ListNodeLinkedListLab.findWithIndex(this.getPolyLinkedList(), i).getValue() == 0 ? ""
				    : (int) (ListNodeLinkedListLab.findWithIndex(this.getPolyLinkedList(), i)
					    .getValue()) + "x")/* print the coefficient as planned unless it is 0 */
			    + (i == 1 ? "" : "^" + i)/* print ^i unless it is 1 */;
	    }
	}
	return s;
    }// post: s is the correct string representation of the polynomial

}
