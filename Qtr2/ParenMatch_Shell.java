//  Name:
//  Date:
//  Period:
//  What I Learned:
//
//
package com.LabTests.src.Qtr2;

import java.util.*;

public class ParenMatch_Shell
{
    public static final String left = "([{<";
    public static final String right = ")]}>";

    public static void main(String[] args)
    {

	System.out.println("Enter an expression with grouping symbols,");
	System.out.println("such as (2+3)-[5*(6+1)]IndexMals");
	Scanner keyboard = new Scanner(System.in);
	String s = keyboard.next();
	while (!s.equals("-1"))
	{
	    boolean flag = check(s);
	    if (flag)
		System.out.println(s + " is good.");
	    else
		System.out.println("No, no, no.  Bad.  " + s);
	    System.out.println();
	    s = keyboard.next();
	}
    }

    // precondition:
    // postcondition:
    public static boolean check(String s)
    {
	Stack<Character> stack = new Stack<>();
	for (int i = 0; i < s.length(); i++)
	{

	    if (s.charAt(i) == '(')
		stack.push('(');

	    else if (s.charAt(i) == '[')
		stack.push('[');

	    else if (s.charAt(i) == '{') 
		stack.push('{');

	    else if (s.charAt(i) == ' ')
	    {
		if (stack.isEmpty())
		    return false;
		if (stack.pop() != L_PAREN)
		    return false;
	    }

	    else if (s.charAt(i) == R_BRACE)
	    {
		if (stack.isEmpty())
		    return false;
		if (stack.pop() != L_BRACE)
		    return false;
	    }

	    else if (s.charAt(i) == R_BRACKET)
	    {
		if (stack.isEmpty())
		    return false;
		if (stack.pop() != L_BRACKET)
		    return false;
	    }

	    return false;
	}
    }
}
