package com.company;

public class Polylines {
    public static Polyline yellowPolylines (Polyline[] polylines){
        int j = -1;
        // give the len a big positive value
        double len = Double.MAX_VALUE;
        for (int i = 0; i < polylines.length; i++) {
            if (polylines[i].getColour().equals("yellow") &&
                    polylines[i].length() < len) {
                j = i;
                len = polylines[i].length();
            }
        }
        // If there wasn't any yellow polyline
        if (j < 0)
            throw new IllegalArgumentException("no yellow polyline has been found");
        return polylines[j];
    }
}
