package com.company;

public class PolylineTest {
    public static void main(String[] args) {
        Point[] vertices1 = new Point[3];
        vertices1[0] = new Point("A",1,2);
        vertices1[1] = new Point("B",2,3);
        vertices1[2] = new Point("C",3,4);

        Polyline polyline1 = new Polyline();
        polyline1.setColour("purple");
        Polyline polyline2 = new Polyline(vertices1);

        System.out.println(polyline1);
        System.out.println(polyline2);

        polyline2.addBefore(new Point("G",3,4), "A");
        polyline2.addLast(new Point("H",7,5));
        System.out.println(polyline2);

        polyline1.addLast(new Point("A",2,3));
        System.out.println(polyline1);
        polyline1.remove("A");
        polyline1.addLast(new Point("D",4,5));
        polyline1.addLast(new Point("Z",4,6));
        System.out.println(polyline1);

        Polyline.PolylineIterator it1 = polyline1.new PolylineIterator();
        Polyline.PolylineIterator it2 = polyline2.new PolylineIterator();

        System.out.println();
        System.out.println("Using iterator on the purple line");
        while(it1.hasVertex()) {
            System.out.println(it1.vertex());
            it1.advance();
        }
        System.out.println("Using iterator on the black line");
        while(it2.hasVertex()) {
            System.out.println(it2.vertex());
            it2.advance();
        }

    }
}
