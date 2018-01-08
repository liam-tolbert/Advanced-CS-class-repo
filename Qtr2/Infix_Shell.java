package com.LabTests.src.Qtr2;

/**
 * @author Liam Tolbert
 * What I learned: 
 * - Evaluating postfix in Java
 * - Converting infix notation to postfix in Java
 * - the best and most simple algorithms for both of these 
 * - How to properly convert chars to ints without any weird characters appearing
 * What I thought of this lab: This lab was relatively hard for me, in the logical part of things. However, I got really stuck on making sure the compiler 
 * wanted to compile my chars that were converted from ints, so I had to look up (on StackOverflow :( ) how to properly convert an int to a char using the 
 * static method Character.forDigit().
 */
import java.util.*;

public class Infix_Shell
{
    public static void main(String[] args)
    {
	System.out.println("Enter an infix expression, single digits");
	System.out.println("such as 1+2*3 or (1+2)*3");
	Scanner keyboard = new Scanner(System.in);// (3*(4+5)-2)/5
	String s = keyboard.next();
	while (!s.equals("-1"))
	{
	    System.out.println(s + "  -->  " + trans(s) + "  -->  " + Postfix.eval(trans(s)) + "\n");
	    s = keyboard.next();
	}
    }

    /**
     * Converts infix to postfix
     * 
     * @param infix
     *            the infix notation in a String
     * @return the postfix notation
     */
    // pre: infix is a proper String and in the correct infix format
    public static String trans(String infix)
    {
	int length = infix.length();
	Stack<Character> stack = new Stack<Character>();
	StringBuilder postfix = new StringBuilder();

	for (int i = 0; i < length; i++)
	{
	    if ((infix.charAt(i) >= '0') && (infix.charAt(i) <= '9'))
	    {
		postfix.append(infix.charAt(i));
	    } else if (infix.charAt(i) == '(')
	    {
		stack.push(infix.charAt(i));
	    } else if ((infix.charAt(i) == '*') || (infix.charAt(i) == '+') || (infix.charAt(i) == '-')
		    || (infix.charAt(i) == '/'))
	    {
		while ((stack.size() > 0) && (stack.peek() != '('))
		{
		    if (ComparePrecedence(stack.peek(), infix.charAt(i)))
		    {
			postfix.append(stack.pop());
		    } else
		    {
			break;
		    }
		}
		stack.push(infix.charAt(i));
	    } else if (infix.charAt(i) == ')')
	    {
		while ((stack.size() > 0) && (stack.peek() != '('))
		{
		    postfix.append(stack.pop());
		}
		if (stack.size() > 0)
		    stack.pop(); // popping out the left brace '('
	    } else
	    {
	    }
	}
	while (stack.size() > 0)
	{
	    postfix.append(stack.pop());
	}
	return postfix.toString();
    }
    // post: the postfix string is in the proper postfix notation and is the right
    // postfix for the infix input

    /**
     * Compares precedence for operators
     * 
     * @param top
     *            the operator
     * @param p_2
     *            the second operator
     * @return boolean to whatever operator has precedence
     */
    // pre: top and p_2 are both valid operators (+, -, /, *)
    private static boolean ComparePrecedence(char top, char p_2)
    {
	if (top == '+' && p_2 == '*') // + has lower precedence than *
	    return false;
	if (top == '*' && p_2 == '-') // * has higher precedence over -
	    return true;

	if (top == '+' && p_2 == '-') // + has same precedence over +
	    return true;

	return true;
    }
    // post: the boolean comparing these two operators is correct(true if p_2 has
    // higher precedence, false otherwise)
}