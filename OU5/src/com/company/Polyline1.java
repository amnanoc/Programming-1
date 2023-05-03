package com.company;

public class Polyline1 {
    private Point[] vertices;
    private String colour = "black";
    private int width = 1;
    public Polyline1 ()
    {
        this.vertices = new Point [0];
    }
    public Polyline1 (Point [] vertices)
    {
        this.vertices = new Point[vertices.length ];
        for (int i = 0; i < vertices.length; i++)
            this.vertices[i] = new Point (vertices[i]);
    }
    public String toString () {
        return colour+" "+width+" "+vertices;
    }
    public Point [] getVertices () {
        //returning the ref
        //this is the better strategy
        return this.vertices;
    }
    public String getColour () {
        return this.colour;
    }
    public int getWidth () {
        return this.width;
    }
    public void setColour (String colour) {
        this.colour=colour;
    }
    public void setWidth (int width) {
        this.width=width;
    }
    public double length () {
        return 0;
    }

    public void addLast (Point vertex)
    {
        Point[] h = new Point[this.vertices.length + 1];
        int i = 0;
        for (i = 0; i < this.vertices.length; i++)
            h[i] = this.vertices[i];
        h[i] = new Point (vertex );
        this.vertices = h;
    }
    public void addBefore (Point vertex , String vertexName) {


    }

    public void remove (String vertexName) {}

    class PolylineIterator {
        private int current = -1;
        public PolylineIterator () {
            if (Polyline1.this.vertices.length > 0)
                current = 0;
        }
        public boolean hasVertex ()
        {
            return current != -1;
        }

        public Point vertex () throws java.util.NoSuchElementException {
            if (!this.hasVertex ())
                throw new java.util.NoSuchElementException ("end of iteration");
            Point vertex = Polyline1.this.vertices[current ];
            return vertex;
        }

        public void advance (){
            if (current >= 0 &&
                    current < Polyline1.this.vertices.length - 1)
                current ++;
            else
                current = -1;
        }
    }

}
