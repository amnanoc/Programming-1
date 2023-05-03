package com.company;
import java.util.Scanner;
import java.lang.Math;

public class TriangleAndItsCircles {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Please enter the sides of the triangle:");
        System.out.println("a: ");
        Scanner input = new Scanner(System.in);
        double a = input.nextDouble();
        System.out.println("b: ");
        double b = input.nextDouble();
        System.out.println("c: ");
        double c = input.nextDouble();

        System.out.println("The radius of the inscribed circle of the given triangle: "+ Triangle.getRadiusOfTheInscribedCircle(a,b,c));
        System.out.println("The radius of the circumcircle of the given triangle: "+ Triangle.getRadiusOfTheCircumcircle(a,b,c));

    }
}
