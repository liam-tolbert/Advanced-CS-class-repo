package com.LabTests.src.Qtr2;

/*****************************************************************************************************************
NAME: Liam Tolbert     
PERIOD: 1
DUE DATE: 12/11/17 
ASSIGNMENT: Iterator Lab

PURPOSE: To use Iterators and for-each loops to traverse and process lists   

LEARNED: I didn't really learn anything from this lab...   
            
CREDITS: None

****************************************************************************************************************/

// NOTE:  use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;

public class IteratorLab_shell
{
    public static void main(String[] args)
    {
	System.out.println("Iterator Lab\n");
	int[] rawNumbers =
	{ -9, 4, 2, 5, -10, 6, -4, 24, 20, -28 };
	for (int n : rawNumbers)
	    System.out.print(n + " ");
	ArrayList<Integer> numbers = createNumbers(rawNumbers);
	System.out.println();
	System.out.println("ArrayList: " + numbers); // Implicit Iterator!
	System.out.println("Count negative numbers: " + countNeg(numbers));
	System.out.println("Average: " + average(numbers));
	System.out.println("Replace negative numbers: " + replaceNeg(numbers));
	System.out.println("Delete zeros: " + deleteZero(numbers));
	String[] rawMovies =
	{ "High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon",
		"Tron" };
	ArrayList<String> movies = createMovies(rawMovies);
	System.out.println("Movies: " + movies);
	System.out.println("Movies: " + removeDupes(movies));
    }

    // pre: an array of just int values
    // post: return an ArrayList containing all the values
    public static ArrayList<Integer> createNumbers(int[] rawNumbers)
    {
	ArrayList<Integer> retVal = new ArrayList<>();
	for (int num : rawNumbers)
	    retVal.add(num);
	return retVal;
    }

    // pre: an array of just Strings
    // post: return an ArrayList containing all the Strings
    public static ArrayList<String> createMovies(String[] rawWords)
    {
	ArrayList<String> retVal = new ArrayList<>();
	for (String str : rawWords)
	    retVal.add(str);
	return retVal;
    }

    // pre: ArrayList a is not empty and contains only Integer objects
    // post: return the number of negative values in the ArrayList a
    public static int countNeg(ArrayList<Integer> a)
    {
	int count = 0;
	for (int num : a)
	    if (num < 0)
		count++;
	return count;
    }

    // pre: ArrayList a is not empty and contains only Integer objects
    // post: return the average of all values in the ArrayList a
    public static double average(ArrayList<Integer> a)
    {
	int sum = 0, count = 0;
	for (int num : a)
	{
	    sum += num;
	    count++;
	}
	return sum / count;
    }

    // pre: ArrayList a is not empty and contains only Integer objects
    // post: replaces all negative values with 0
    public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
    {
	ArrayList<Integer> x = new ArrayList<>();
	for (int num : a)
	    x.add(num < 0 ? 0 : num);
	return x;
    }

    // pre: ArrayList a is not empty and contains only Integer objects
    // post: deletes all zeros in the ArrayList a
    public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
    {
	ArrayList<Integer> retList = new ArrayList<>();
	for (int num : a)
	{
	    if (num == 0)
		continue;
	    else
		retList.add(num);
	}
	return retList;
    }

    // pre: ArrayList a is not empty and contains only String objects
    // post: return ArrayList without duplicate movie titles
    // strategy: start with an empty array and add movies as needed
    public static ArrayList<String> removeDupes(ArrayList<String> a)
    {
	ArrayList<String> usedTitles = new ArrayList<>();
	ArrayList<String> retList = new ArrayList<>();
	boolean alreadyUsed = false;
	for (String title : a)
	{
	    for (String used : usedTitles)
	    {
		if (title.equals(used))
		{
		    alreadyUsed = true;
		    break;
		}
	    }
	    if (alreadyUsed)
	    {
		alreadyUsed = false;
		continue;
	    } else
	    {
		retList.add(title);
		usedTitles.add(title);
	    }
	}
	return retList;
    }
}
/*
 * output: 
 * Iterator Lab

-9 4 2 5 -10 6 -4 24 20 -28 
ArrayList: [-9, 4, 2, 5, -10, 6, -4, 24, 20, -28]
Count negative numbers: 4
Average: 1.0
Replace negative numbers: [0, 4, 2, 5, 0, 6, 0, 24, 20, 0]
Delete zeros: [-9, 4, 2, 5, -10, 6, -4, 24, 20, -28]
Movies: [High_Noon, High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No, Dr_No, Mary_Poppins, High_Noon, Tron]
Movies: [High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No]

 */