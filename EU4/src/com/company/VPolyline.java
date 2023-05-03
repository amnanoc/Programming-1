package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class VPolyline implements Polyline{
    private Point[] vertices;
    private String color;
    private int width = 1;

    public VPolyline(Point[] vertices) {
        this.vertices = vertices;
    }

    public VPolyline() {
        this.vertices = null;
    }

    @Override
    public Point[] getVertices() {
        return vertices;
    }

    @Override
    public String getColour() {
        return color;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public double length() {
        double len = 0;
         Point[] temp = vertices;

        for (int i=0; i<temp.length; i++) {
            if(temp[i+1]!=null)
                len = len + temp[i].distance(temp[i+1]);
        }
        return len;
    }

    @Override
    public void setColour(String colour) {
        this.color=colour;
    }

    @Override
    public void setWidth(int width) {
        this.width=width;
    }

    @Override
    public void add(Point vertex) {
        Point[] arr;
        if(vertices == null)
            arr = new Point[1];
        else
            arr = new Point[vertices.length+1];

        int i = 0;
        if(vertices!=null) {
            for(i=0; i<vertices.length; i++)
                arr[i]=vertices[i];
        }

        arr[i] = vertex;
        vertices = arr;

    }

    @Override
    public void insertBefore(Point vertex, String vertexName) {
        int pos = -1;
        for (int i = 0; i < vertices.length; i++){
            if (vertices[i].getName().compareTo(vertexName) == 0)
                pos = i;
        }
        if (pos < 0)
            throw  new IllegalArgumentException("the point does not exist");

        Point[] temp  = new Point[vertices.length + 1];
        int j =0;
        for (int i = 0; i < vertices.length; i++) {
            if (i == pos) {
                temp[i] = vertex;
                j++;
            }
            temp[j] = vertices[i];
            j++;
        }
        vertices = temp;
    }

    @Override
    public void remove(String vertexName) {
        int pos = -1;
        for (int i = 0; i < vertices.length; i++){
            if (vertices[i].getName().compareTo(vertexName) == 0)
                pos = i;
        }
        if (pos<0)
            throw  new IllegalArgumentException("the point does not exist");
        Point[] temp  = new Point[vertices.length -1];
        for (int i = 0; i< pos; i++)
            temp[i] = vertices[i];
        for (int i = pos+1; i < vertices.length;i++ )
            temp[i - 1] = vertices[i];
        vertices = temp;

    }

    public Iterator<Point> iterator() {
        return new VPointIterator();}
    private class VPointIterator implements Iterator<Point> {
        int current = -1;

        public VPointIterator() {
            if (vertices.length > 0)
                current = 0;
        }

        public boolean hasNext() {
            return current != -1;
        }


        public Point next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Point p = vertices[current];
            advance();
            return p;
        }

        public void advance() {
            if (current >= 0 &&
                    current < vertices.length - 1)
                current++;
            else
                current = -1;

        }
    }

    @Override
    public String toString() {
        return "VPolyline{" +
                "vertices=" + Arrays.toString(vertices) +
                ", color='" + color + '\'' +
                ", width=" + width +
                '}';
    }
}
