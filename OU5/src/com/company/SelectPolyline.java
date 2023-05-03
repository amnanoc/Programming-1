package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class SelectPolyline {
    public static final Random rand = new Random ();
    public static final int NOF_POLYLINES = 10;
    public static void main (String [] args) {

        // Create a random number of polylines
        Polyline [] polylines = new Polyline[NOF_POLYLINES ];
        ArrayList<Integer> yellowOnes = new ArrayList<>();
        for (int i = 0; i < NOF_POLYLINES; i++) {
            polylines[i] = randomPolyline ();
            if(Objects.equals(polylines[i].getColour(), "yellow")) {
                yellowOnes.add(i);
            }
            System.out.println(polylines[i]);  // Show the polylines
        }
        // Determine the shortest yellow polyline
        // Show the selected polyline

        int minWidth = polylines[yellowOnes.get(0)].getWidth();
        int minYellowIndex = yellowOnes.get(0);
        for(int i=1; i<yellowOnes.size(); i++) {
           if(minWidth>polylines[yellowOnes.get(i)].getWidth()) {
               minWidth = polylines[yellowOnes.get(i)].getWidth();
               minYellowIndex = yellowOnes.get(i);
           }
        }

        System.out.println("The shortest yellow path is");
        System.out.println(polylines[minYellowIndex]);
        System.out.println(" and its length is " + minWidth);


    }
        // The randomPoint method returns a new Point with a name
        // randomly chosen from the single letters A--Z. Coordinates
        // are random.
    public static Point randomPoint () {
        String n = "" + (char) (65 + rand.nextInt (26));
        int x = rand.nextInt (11);
        int y = rand.nextInt (11);
        return new Point (n, x, y);
    }
        // The method randomPolyline returns a random polyline ,
        // with a colour either blue , red , or yellow. The names
        // of the vertices are single letters from the set A--Z.
        // Two vertices can not have the same name.
    public static Polyline randomPolyline () {
        // Create an empty polyline and add vertices
        String [] colors = {"red", "yellow", "blue"};
        Polyline polyline = new Polyline();
        int nofVertices = 2 + rand.nextInt(7);
        for(int i=0; i<nofVertices; i++) {
            Point point = randomPoint();
            while(polyline.contains(point.getName())) {
                point = randomPoint(); // get a new point where the name won't be the same
            }
            polyline.addLast(randomPoint());
        }

        // Adding a random color to the line
        int rndColor = new Random().nextInt(colors.length);
        polyline.setColour(colors[rndColor]);


        return polyline;
    }
}
