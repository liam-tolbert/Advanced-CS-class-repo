package com.LabTests.src.Qtr2;

import java.util.Scanner;

public class TextEditorDriver
{

    public static boolean run()
    {
	Scanner input = new Scanner(System.in);
	System.out.println("Give a line of text");
	String str = input.nextLine();
	TextEditor editor = new TextEditor(str);
	System.out.println("Here is the line you entered: " + editor.printFinalString());
	System.out.println("Run again? (y/n): ");
	str = input.nextLine();
	return str.equals("y") ? true : false;
    }

    public static void main(String[] args)
    {
	boolean runAgain = true;
	while (runAgain)
	    runAgain = run();
    }

}
