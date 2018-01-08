package com.LabTests.src.Qtr1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class experiment
{

    public static void main(String[] args) throws FileNotFoundException
    {
	File file = new File("C:\\Users\\Unity\\Documents\\Liam\\School\\OHS\\2017-2018 sophomore year\\Adv APCS\\Labs\\Lab Tests\\src\\com\\LabTests\\src\\Qtr1\\area2.txt");
	BufferedReader br = new BufferedReader(new FileReader(file));
	String st;
	try
	{
	    while ((st = br.readLine()) != null)
	        System.out.println(st);
	} catch (IOException e)
	{
	    throw new FileNotFoundException();
	}
    }
}
