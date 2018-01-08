package com.LabTests.src.Qtr1;

public class SinglyBasedPolynomialDriver
{
    public static void main(String[] args)
    {
	double[] c = new double[]
	{ 4, 3, 0, 1 };
	
	double[] d = new double[] {-5,-2};
	
	Polynomial p1 = new SinglyBasedPolynomial(c); // 4x^3 + 3x^2 + 1
	System.out.println("p1(x) =     " + p1);

	Polynomial p2 = new SinglyBasedPolynomial(d); // - 5x – 2
	System.out.println("p2(x) =     " + p2); // p2(x) = - 5x^1 – 2

	Polynomial p3 = new SinglyBasedPolynomial(-4,1); // coeff, exp = -4x
	System.out.println("p3(x) =     " + p3);

	Polynomial p = p1.plus(p2).plus(p2); // 4x^3 + 3x^2 - 10x – 3
	System.out.println("p(x) =     " + p); // p(x) = 4x^3 + 3x^2 - 10x^1 – 3

	Polynomial p4 = p.minus(p3); // 4x^3 + 3x^2 - 6x^1 – 3 <====
	System.out.println("p4(x) =     " + p4);

	Polynomial p5 = p4.differentiate(); // 12x^2 + 6x^1 - 6 <====
	System.out.println("p5(x) =     " + p5);

	Polynomial clone = new SinglyBasedPolynomial(p5);
	System.out.println("clone(x) =     " + clone);

	Polynomial product = p1.multiply(p2);
	System.out.println("product of p1(x) and p2(x) is     " + product);

	System.out.println("p5(0) = " + p5.evaluate(0));
	System.out.println("p5(1) = " + p5.evaluate(1));
    }
}
