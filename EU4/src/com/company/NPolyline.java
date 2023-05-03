package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NPolyline implements Polyline {
    private static class Node {
        public Point vertex;
        public Node nextNode;
        public Node (Point vertex){
            this.vertex = vertex;
            nextNode = null;
        }
    }
    private Node vertices;
    private String colour = "black";
    private int width = 1; // pixels
    public NPolyline () {
        this.vertices = null;
    }
    public NPolyline (Point [] vertices){
        if (vertices.length > 0){
            Node node = new Node (new Point (vertices [0]));
            this.vertices = node;
            int pos = 1;
            while (pos < vertices.length)
            {
                node.nextNode = new Node (new Point (vertices[pos ++]));
                node = node.nextNode;
            }
        }
    }


    @Override
    public Point[] getVertices() {
        Point[] verticesArray = new Point[width];
        Node startNode = vertices;

        while(vertices.nextNode!=null) {
            int i = 0;
            verticesArray[i] = vertices.vertex;
            i++;
            vertices = vertices.nextNode;
        }

        vertices = startNode;
        return verticesArray;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public double length() {
        double len = 0;
        Node temp = vertices;
        while (temp.nextNode !=null) {
            len = len + temp.vertex.distance(temp.nextNode.vertex);
            temp = temp.nextNode;
        }
        return len;
    }

    @Override
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void add(Point vertex) {
        Node startNode = vertices;
        if(vertices==null) {
            vertices = new Node(vertex);
            return;
        }

        while(vertices.nextNode != null)
            vertices = vertices.nextNode;

        vertices.nextNode = new Node(vertex);
        width++;
        vertices = startNode;
    }

    @Override
    public void insertBefore(Point vertex, String vertexName) {
        if(vertices==null)
            throw new NoSuchElementException();

        Node startNode = vertices;
        while(vertices.nextNode!=null) {
            if(Objects.equals(vertices.nextNode.vertex.getName(), vertexName)) {
                Node rememberNode = vertices.nextNode;
                vertices.nextNode = new Node(vertex);
                vertices.nextNode.nextNode = rememberNode;
                vertices = startNode;
                break;
            }

            vertices = vertices.nextNode;
        }

        vertices = startNode;
    }

    @Override
    public void remove(String vertexName) {
        Node temp = vertices;

        if(temp==null)
            throw new NoSuchElementException();

        while(temp.nextNode!=null && !Objects.equals(temp.nextNode.vertex.getName(), vertexName))
            temp = temp.nextNode;

        temp.nextNode = temp.nextNode.nextNode;
    }

    @Override
    public Iterator<Point> iterator()  {
        return new NPointIterator(vertices);
    }

    private static class NPointIterator implements Iterator<Point> {
        private Node current;

        public NPointIterator(Node head) {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Point next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Point p = current.vertex;
            current = current.nextNode;
            return p;
        }

    }

    @Override
    public String toString() {
        return "NPolyline{" +
                "vertices=" + vertices +
                ", colour='" + colour + '\'' +
                ", width=" + width +
                '}';
    }
}
