package com.company;

import java.util.Objects;

public class Point {

    private String name;
    private int x;
    private int y;

    public Point (Point p) {
        this.name = p.name;
        this.x = p.x;
        this.y= p.y;
    }

    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public double distance(Point p2) {
        return Math.sqrt(Math.pow((this.x-p2.x),2) +(Math.pow((this.y-p2.y),2)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return name + ' ' + x + ' ' + y ;
    }
}
