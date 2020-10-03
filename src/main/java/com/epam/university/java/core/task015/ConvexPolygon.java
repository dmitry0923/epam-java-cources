package com.epam.university.java.core.task015;

import java.util.LinkedList;
import java.util.List;

/**
 * Author Dmitry Novikov 03-Oct-20.
 */
public class ConvexPolygon {
    private static int orientation(PointImpl p, PointImpl q, PointImpl r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) {
            return 0;
        }
        return (val > 0) ? 1 : 2;
    }

    /**
     * Some doc.
     */
    public static List<PointImpl> convexHull(List<PointImpl> points, int n) {
        if (n < 3) {
            LinkedList<PointImpl> hull = new LinkedList<>();
            return hull;
        }
        LinkedList<PointImpl> hull = new LinkedList<>();

        int l = 0;
        for (int i = 1; i < n; i++) {
            if (points.get(i).getX() < points.get(l).getX()) {
                l = i;
            }
        }
        int p = l;
        int q;
        do {
            hull.add(points.get(p));
            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                if (orientation(points.get(p), points.get(i), points.get(q))
                        == 2) {
                    q = i;
                }

            }
            p = q;
        } while (p != l);

        for (int i = 0; i < hull.size() - 2; i++) {
            if (hull.get(i).getX() == hull.get(i + 1).getX()
                    && hull.get(i + 1).getX() == hull.get(i + 2).getX()) {
                hull.remove(hull.get(i + 1));
            }
            if (hull.get(i).getY() == hull.get(i + 1).getY()
                    && hull.get(i + 1).getY() == hull.get(i + 2).getY()) {
                hull.remove(hull.get(i + 1));
            }
        }

        for (int i = 0; i < hull.size() - 2; i++) {
            if (hull.get(i).getX() == hull.get(i + 1).getX()
                    && hull.get(i + 1).getX() == hull.get(i + 2).getX()) {
                hull.remove(hull.get(i + 1));
            }
            if (hull.get(i).getY() == hull.get(i + 1).getY()
                    && hull.get(i + 1).getY() == hull.get(i + 2).getY()) {
                hull.remove(hull.get(i + 1));
            }
        }
        return hull;
    }
}
