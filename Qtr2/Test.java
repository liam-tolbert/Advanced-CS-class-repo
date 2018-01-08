package com.LabTests.src.Qtr2;

public class Test
{

    public static void main(String[] args)
    {
	for(int j = 1; j * j <= 4; j++)
	{
	    for(int k = 1; k <= 4; k += k)
	    {
		System.out.println("X");
	    }
	}
	
	for(int j = 1; j <= 9; j *=2)
	{
	    for(int k = 1; k <= j; k++)
	    {
		System.out.println("*");
	    }
	}

    }

}
