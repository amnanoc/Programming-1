package com.company;

public class Main {

    public static void main(String[] args) {
        try{
// write your code here
            Polyline polyline = null;
            polyline = new VPolyline (); // (1)
            polyline = new NPolyline (); // (2)

            polyline.add(new Point("O",1,1));
            for (Point vertex : polyline)
                System.out.println (vertex );
            System.out.println();

            Point pointP = new Point("P",2,1);
            polyline.add(new Point("A",2,3));
            polyline.add(new Point("B",3,4));
            polyline.add(pointP);
            for (Point vertex : polyline)
                System.out.println (vertex );
            System.out.println();

            polyline.remove("A");
            for (Point vertex : polyline)
                System.out.println (vertex );
            System.out.println();

            polyline.insertBefore(new Point("C",2,14), "B");
            for (Point vertex : polyline)
                System.out.println (vertex );
            System.out.println();

            Polyline poly1 = new NPolyline();
            Polyline poly2 = new VPolyline();

            poly1.add(new Point("A",1,2));
            poly1.add(new Point("B",2,3));
            poly1.add(new Point("C",3,4));
            poly1.add(new Point("D",5,6));
            poly1.setColour("yellow");

            poly2.add(new Point("H",2,2));
            poly2.add(new Point("E",3,3));
            poly2.add(new Point("F",4,4));
            poly2.add(new Point("G",6,6));

            Polyline[] arr1 = {poly1};
            Polyline[] arr2 = {poly2};
            Polyline[] arr3 = {poly1, poly2};

            System.out.println(Polylines.yellowPolylines(arr1));
            System.out.println(Polylines.yellowPolylines(arr2));
            System.out.println(Polylines.yellowPolylines(arr3));

        } catch(Exception e) {
            System.out.println(e);
        }


    }
}
