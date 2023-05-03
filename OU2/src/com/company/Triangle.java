package com.company;
import java.lang.Math;

public class Triangle {

    public static double triangleAreaWithHeight (double side, double perpendicularHeight) {
        return side*perpendicularHeight/2;
    }

    public static double trianglePerimeter (double a, double b, double c) {
        return a+b+c;
    }

    public static double triangleAreaWithThreeSides (double a, double b, double c) {
        double s = trianglePerimeter(a,b,c)/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    public static double bisector(double b, double c, double alpha)
    {
        double p = 2 * b * c * Math.cos (alpha / 2);
        double bis = p / (b + c);
        return bis;
    } //we just use this one for everything, it doesnt matter the names of the variables

    public static String getRadiusOfTheInscribedCircle(double a, double b, double c) {
        return String.valueOf(2*Triangle.triangleAreaWithThreeSides(a,b,c)/Triangle.trianglePerimeter(a,b,c));
    }

    public static String getRadiusOfTheCircumcircle(double a, double b, double c) {
        if(a==b && b==c) //equilateral triangle
            return String.valueOf(a/Math.sqrt(3));

        return String.valueOf(a*b*c/(4*Triangle.triangleAreaWithThreeSides(a,b,c)));
    }
}
