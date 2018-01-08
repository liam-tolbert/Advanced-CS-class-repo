package com.LabTests.src.Qtr2;

import java.util.*;

/**
 * How I feel about this lab: It was actually extremely difficult for me (but
 * probably because I didn't attempt to create my own custom stack class
 * beforehand). I had lots of difficulty having my TextEditor object have which
 * way to "insert" the string into the stack (back of the string to the front or
 * front to back). What I found out later was that putting the front of the
 * string in first was the better idea since it is easier to handle the hyphens
 * and dollar signs accordingly when they appear. For example, all I needed to
 * do when I found a hyphen in the string was pop the top of the stack once and
 * never insert the hyphen. 
 * What I learned: 
 * - The situations when it is best to stack a string front to back or back to front
 * 
 * @author Liam Tolbert
 */
public class TextEditor
{
    private String inputString;
    private Stack<Character> stack;

    public TextEditor(String s)
    {
	inputString = s;
	stack = new Stack<Character>();
	for (char ch : inputString.toCharArray())
	    processChar(ch);
    }

    /**
     * processes an element of the stack
     * 
     * @param ch
     *            an element
     */
    // pre: ch is an element of the stack
    public void processChar(char ch)
    {
	switch (ch)
	{
	case '-':
	    if (!stack.empty())
		stack.pop();
	    break;
	case '$':
	    stack.clear();
	    break;
	default:
	    stack.push(ch);
	}
    }

    // post: hyphens and dollar signs in a stack will be handled according to the
    // rules of the lab
    /**
     * prints a stack
     * 
     * @return a String version of a stack
     */
    // pre: none
    public String printFinalString()
    {
	String str = "";
	for (char ch : stack)
	{
	    str += ch;
	}
	return str;
    }
    // post: the String that is returned is the correct representation of the stack
}
