package com.LabTests.src.Qtr2;

import java.util.Stack;

public class Postfix
{
    /**
     * Evaluates a postfix expression
     * @param s the postfix expression
     * @return an integer that is the evaluated postfix expression
     */
    //pre: s is a valid postfix expression
    public static int eval(String s)
    {
	Stack<Character> stack = new Stack<>();
	StringBuffer string = new StringBuffer();
	for (char ch : s.toCharArray())
	{
	    switch (ch)
	    {
	    case '+':
		int sum = Integer.parseInt(String.valueOf(stack.pop())) + Integer.parseInt(String.valueOf(stack.pop()));
		stack.push(Character.forDigit(sum, 10));
		break;
	    case '-':
		int a = Integer.parseInt(String.valueOf(stack.pop()));// the reason I am making these variables is that they are pulled out of the stack in the wrong order. I have to switch them around in the expression for them to work. 
		int b = Integer.parseInt(String.valueOf(stack.pop()));
		int difference = b - a;
		stack.push(Character.forDigit(difference, 10));
		break;
	    case '*':
		int product = Integer.parseInt(String.valueOf(stack.pop())) * Integer.parseInt(String.valueOf(stack.pop()));
		stack.push(Character.forDigit(product, 10));
		break;
	    case '/':
		int x = Integer.parseInt(String.valueOf(stack.pop()));
		int y = Integer.parseInt(String.valueOf(stack.pop()));
		int quotient = y/x;
		stack.push(Character.forDigit(quotient, 10));
		break;
	    default:
		stack.push(ch);
	    }
	}
	for(char ch : stack)
	{
	    string.append(ch);
	}
	return Integer.parseInt(string.toString());
    }
    // post: the returned value is the correct value
    
    public boolean isOperand(char c)
    {
	return !(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')');
    }
}
/*
 * some outputs:
 * Enter an infix expression, single digits
such as 1+2*3 or (1+2)*3
4*2/3
4*2/3  -->  42*3/  -->  2

4+5/4
4+5/4  -->  45+4/  -->  2

3+2*2
3+2*2  -->  322*+  -->  7

5-1
1
5
5-1  -->  51-  -->  4

5-1*2
1
5
5-1*2  -->  51-2*  -->  8


 * */
