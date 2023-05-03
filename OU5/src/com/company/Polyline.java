package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Polyline {
    private Point[] vertices;
    private String colour = "black";
    private int width = 0;
    public Polyline ()
    {
        this.vertices = new Point [0];
    }
    public Polyline (Point [] vertices)
    {
        this.vertices = new Point[vertices.length ];
        for (int i = 0; i < vertices.length; i++)
            this.vertices[i] = new Point (vertices[i]);

        this.width = vertices.length;
    }

    public Point [] getVertices () {
        //creating a new vector and copying the value into it
        Point[] verticesCopy = new Point[vertices.length];
        for(int i=0; i<verticesCopy.length; i++) {
            verticesCopy[i] = vertices[i];
        }
        return verticesCopy;
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

    public void addLast (Point vertex){
        Point[] h = new Point[this.vertices.length + 1];
        int i = 0;
        for (i = 0; i < this.vertices.length; i++)
            h[i] = this.vertices[i];
        h[i] = new Point (vertex );
        this.vertices = h;
        this.width++;
    }

    private void insert(Point vertex, int index) {
        ArrayList<Point> temp = new ArrayList<>();
        for(int i=0; i<=vertices.length; i++){
            if(i<index)
                temp.add(vertices[i]);
            else if (i==index)
                temp.add(vertex);
            else {
                if(i!=0)
                temp.add(vertices[i-1]);
                else if(i==vertices.length-1)
                    temp.add(vertices[vertices.length-1]);
                else
                    temp.add(vertices[0]);
            }
        }

        vertices= temp.toArray(new Point[0]);
    }

    public void addBefore (Point vertex , String vertexName) {
        Point vertexCopy = new Point(vertex); // intializing the copy with vertex
        this.width++;
        for(int i=0; i<vertices.length; i++){
            if(Objects.equals(vertices[i].getName(), vertexName))  {
                insert(vertexCopy, i);
                break;
            }
        }
    }

    public void remove (String vertexName) {
        ArrayList<Point> temp = new ArrayList<>();
        for(int i=0; i<vertices.length; i++)
            if(!Objects.equals(vertices[i].getName(),vertexName))
                temp.add(vertices[i]);

        vertices = temp.toArray(new Point[0]);
    }

    public boolean contains(String vertexName){
        for (Point vertex : vertices) {
            if (Objects.equals(vertex.getName(), vertexName))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Polyline{" +
                "vertices=" + Arrays.toString(vertices) +
                ", colour='" + colour + '\'' +
                ", width=" + width +
                '}';
    }

    class PolylineIterator {
        private int current = -1;
        public PolylineIterator ()
        {
            if (Polyline.this.vertices.length > 0)
                current = 0;
        }
        public boolean hasVertex ()
        {
            return current != -1;
        }
        public Point vertex () throws java.util.NoSuchElementException {
            if (!this.hasVertex ())
                throw new java.util.NoSuchElementException ("end of iteration");
            Point vertex = Polyline.this.vertices[current];
            return vertex;
        }

        public void advance () {
            if (current >= 0 &&
                    current < Polyline.this.vertices.length - 1)
                current ++;
            else
                current = -1;
        }
    }

}
