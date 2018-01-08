package com.LabTests.src.Qtr1;

import java.util.Scanner;
/**
 * @author Liam Tolbert
 * What this lab was about: Practicing small recursion methods
 * How I feel about this lab: This lab was actually pretty easy. Although I need to recognize
 * whether to use helper methods or not, recursion was mostly an easy topic for me to understand
 * Things I learned:
 * - What situations to use to use helper methods
 * - 
 */
public class RecursionLab
{
    // Pre: c is a lower case letter - Post: all lower case letters a-char c are
    // printed
    public static void letters(char c)
    {
	if (c == 'a')
	{
	    System.out.println('a');
	} else
	{
	    letters((char) (c - 1));
	    System.out.println(c);
	}
    }

    // Pre: none - Post: returns number of times two can go into x
    public static int twos(int x)
    {
	if (x == 0 || x == 1)// not divisible
	    return 0;
	return 1 + twos(x - 2);
    }

    // Pre: none - Post: returns if x is a power of 3
    public static boolean powerof3(int x)
    {
	if (x % 3 != 0)// Check if this works again after finishing this program
	    return false;
	else if(x % 3 == 0)
	    return true;
	return true && powerof3(x / 3);
    }

    // Pre: none - Post: returns String of x reversed
    public static String reverse(long x)
    {
	return reverseHelper(Long.toString(x));
    }
    
    public static String reverseHelper(String x)
    {
	if (x.length() <= 1)
	    return x;
	return reverseHelper(x.substring(1)) + x.charAt(0);
    }
    
    // Pre: x > 0 - Post: Prints x in base 5
    public static void base5(int x)
    {
	if (x < 5)
	{
	    System.out.print(x);
	} else
	{
	    base5((x - x % 5) / 5);
	    System.out.print(x % 5);
	}
    }

    // Pre: x > 0 - Post: Prints x with commas
    public static void printWithCommas(long x)
    {
	commasHelper(Long.toString(x));
    }
    
    public static void commasHelper(String x)
    {
	if (x.length() == 1)
	    System.out.print(x);
	else
	{
	    System.out.print(x.charAt(0) + ",");
	    commasHelper(x.substring(1));
	}
    }

    public static void main(String[] args)
    {
	Scanner scan = new Scanner(System.in);
	int choice;
	do
	{
	    System.out.println("\n\n1)Letters" + "\n2)Twos" + "\n3)Power Of 3" + "\n4)Reverse" + "\n5)Base 5"
		    + "\n6)Print With Commas" + "\n7)Exit");
	    choice = scan.nextInt();
	    if (choice == 1)
	    {
		System.out.println("Enter a letter");
		char charA = scan.next().charAt(0);
		if (charA < 'a' || charA > 'z')
		    System.out.println("That letter not valid");
		else
		    letters(charA);
	    } else if (choice == 2)
	    {
		System.out.println("Enter a number");
		System.out.println(twos(scan.nextInt()));
	    } else if (choice == 3)
	    {
		System.out.println("Enter a number");
		System.out.println(powerof3(scan.nextInt()));
	    } else if (choice == 4)
	    {
		System.out.println("Enter a number");
		System.out.println(reverse(scan.nextLong()));
	    } else if (choice == 5)
	    {
		System.out.println("Enter a number");
		int number = scan.nextInt();
		if (number > 0)
		    base5(number);
		else
		    System.out.println("That number is not valid");
	    } else if (choice == 6)
	    {
		System.out.println("Enter a number");
		int number = scan.nextInt();
		if (number > 0)
		    printWithCommas(number);
		else
		    System.out.println("That number is not valid");
	    }
	} while (choice != 7);
    }
}

/*
 * 
1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
4
Enter a number
1050050005
5000500501


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
1
Enter a letter
l
a
b
c
d
e
f
g
h
i
j
k
l


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
2
Enter a number
22222
11111


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
3
Enter a number
36633
true


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
3
Enter a number
36630
true


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
3
Enter a number
157210
false


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
4
Enter a number
105005
500501


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit

5
Enter a number
506670
112203140

1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
6
Enter a number
670670
6,7,0,6,7,0

1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
7

 */
