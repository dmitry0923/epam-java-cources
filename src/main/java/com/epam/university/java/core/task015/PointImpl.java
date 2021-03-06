package com.epam.university.java.core.task015;

import java.util.Objects;

/**
 * Author Dmitry Novikov 27-Sep-20.
 */
public class PointImpl implements Point {
    private double x;
    private double y;

    public PointImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.getX()
                && y == point.getY();
    }

    @Override
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
