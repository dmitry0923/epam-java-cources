package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author Dmitry Novikov 03-Oct-20.
 */
public class Task015Impl implements Task015 {
    @Override
    public double getArea(Square first, Square second) {
        List<PointImpl> firstListPoints = createSetOfPoints(first);
        List<PointImpl> secondListPoints = createSetOfPoints(second);
        List<PointImpl> intersectionPoints = new ArrayList<>();
        for (PointImpl p : firstListPoints
        ) {
            intersectionPoints.add(new PointImpl(0, 0));
        }
        Collections.copy(intersectionPoints, firstListPoints);

        intersectionPoints.retainAll(secondListPoints);
        if (intersectionPoints.isEmpty()) {
            return 0.0;
        }
        List<PointImpl> hull =
                ConvexPolygon.convexHull(intersectionPoints, intersectionPoints.size());

        return calcArea(hull);
    }

    private static double calcArea(List<PointImpl> hull) {
        if (hull.size() < 3) {
            return 0.0;
        } else if (hull.size() == 3) {
            return calcTriangleArea(hull);
        } else if ((hull.size() == 4)) {
            List<PointImpl> firstTriangle = new ArrayList<>();

            firstTriangle.add(hull.get(0));
            firstTriangle.add(hull.get(1));
            firstTriangle.add(hull.get(2));

            List<PointImpl> secondTriangle = new ArrayList<>();

            secondTriangle.add(hull.get(0));
            secondTriangle.add(hull.get(2));
            secondTriangle.add(hull.get(3));

            double firstArea = calcTriangleArea(firstTriangle);
            double secondArea = calcTriangleArea(secondTriangle);

            return firstArea + secondArea;

        } else if (hull.size() == 8) {
            List<PointImpl> first = new ArrayList<>();

            first.add(hull.get(0));
            first.add(hull.get(1));
            first.add(hull.get(2));

            List<PointImpl> second = new ArrayList<>();
            second.add(hull.get(0));
            second.add(hull.get(2));
            second.add(hull.get(3));

            List<PointImpl> third = new ArrayList<>();
            third.add(hull.get(0));
            third.add(hull.get(3));
            third.add(hull.get(4));

            List<PointImpl> fourth = new ArrayList<>();
            fourth.add(hull.get(0));
            fourth.add(hull.get(4));
            fourth.add(hull.get(5));

            List<PointImpl> fifth = new ArrayList<>();
            fifth.add(hull.get(0));
            fifth.add(hull.get(5));
            fifth.add(hull.get(6));

            List<PointImpl> sixth = new ArrayList<>();
            sixth.add(hull.get(0));
            sixth.add(hull.get(6));
            sixth.add(hull.get(7));

            double firstArea = calcTriangleArea(first);
            double secondArea = calcTriangleArea(second);
            double thirdArea = calcTriangleArea(third);
            double fourthArea = calcTriangleArea(fourth);
            double fifthArea = calcTriangleArea(fifth);
            double sixthArea = calcTriangleArea(sixth);

            return firstArea + secondArea + thirdArea
                    + fourthArea + fifthArea + sixthArea;
        }
        return 0.0;
    }

    private static double calcTriangleArea(List<PointImpl> threePointsList) {
        double pointOneX = threePointsList.get(0).getX();
        double pointOneY = threePointsList.get(0).getY();
        double pointTwoX = threePointsList.get(1).getX();
        double pointTwoY = threePointsList.get(1).getY();
        double pointThreeX = threePointsList.get(2).getX();
        double pointThreeY = threePointsList.get(2).getY();

        double res = Math.abs((pointOneX * (pointTwoY - pointThreeY)
                + pointTwoX * (pointThreeY - pointOneY)
                + pointThreeX * (pointOneY - pointTwoY)) / 2);

        return res;
    }

    private static List<PointImpl> createSetOfPoints(Square square) {

        List<PointImpl> result = new ArrayList<>();
        // если это ромб
        if (square.getFirst().getX() == square.getSecond().getX()
                || square.getFirst().getY() == square.getSecond().getY()) {
            // если равны по Y
            if (square.getFirst().getY() == square.getSecond().getY()) {
                // находим расстояние и стартовую точку
                PointImpl startPoint;
                int distance = (int) Math.abs(square.getFirst().getX() - square.getSecond().getX());
                if (square.getFirst().getX() < square.getSecond().getX()) {
                    startPoint = (PointImpl) square.getFirst();
                } else {
                    startPoint = (PointImpl) square.getSecond();
                }
                switch (distance) {
                    case 4:
                        result.add(startPoint);

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 1));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 2));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 1));

                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY()));

                        break;
                    case 6:
                        result.add(startPoint);

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 1));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 2));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 3));

                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() - 2));

                        result.add(new PointImpl(startPoint.getX() + 5, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 5, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 5, startPoint.getY() - 1));

                        result.add(new PointImpl(startPoint.getX() + 6, startPoint.getY()));

                        break;
                    default:
                }
            } else if (square.getFirst().getX() == square.getSecond().getX()) {
                // находим расстояние и стартовую точку
                PointImpl startPoint;
                int distance = (int) Math.abs(square.getFirst().getY() - square.getSecond().getY());
                if (square.getFirst().getY() < square.getSecond().getY()) {
                    startPoint = (PointImpl) square.getFirst();
                } else {
                    startPoint = (PointImpl) square.getSecond();
                }
                switch (distance) {
                    case 4:
                        result.add(startPoint);

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 1));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() - 2, startPoint.getY() + 2));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 3));

                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 4));

                        break;
                    case 6:
                        result.add(startPoint);

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 1));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() - 2, startPoint.getY() + 2));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() - 2, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() - 3, startPoint.getY() + 3));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 4));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 4));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 4));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 4));
                        result.add(new PointImpl(startPoint.getX() - 2, startPoint.getY() + 4));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 5));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 5));
                        result.add(new PointImpl(startPoint.getX() - 1, startPoint.getY() + 5));

                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 6));

                        break;
                    default:
                }
            }
        } else if (square.getFirst().getX() != square.getSecond().getX()
                && square.getFirst().getY() != square.getSecond().getY()) {
            //если диагональ слева направо
            if ((square.getFirst().getX() < square.getSecond().getX()
                    && square.getFirst().getY() < square.getSecond().getY())
                    || (square.getFirst().getX() > square.getSecond().getX()
                    && square.getFirst().getY() > square.getSecond().getY())) {
                PointImpl startPoint;
                int distance = (int) Math.abs(square.getFirst().getX() - square.getSecond().getX());
                if (square.getFirst().getX() < square.getSecond().getX()) {
                    startPoint = (PointImpl) square.getFirst();
                } else {
                    startPoint = (PointImpl) square.getSecond();
                }
                switch (distance) {
                    case 1:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 1));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));

                        break;
                    case 2:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 2));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 2));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));

                        break;
                    case 3:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 3));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 3));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 3));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 3));

                        break;
                    case 4:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() + 4));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() + 4));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() + 4));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() + 4));

                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() + 1));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() + 2));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() + 3));
                        result.add(new PointImpl(startPoint.getX() + 4, startPoint.getY() + 4));

                        break;

                    default:
                }
            } else if ((square.getFirst().getX() < square.getSecond().getX()
                    && square.getFirst().getY() > square.getSecond().getY())
                    || (square.getFirst().getX() > square.getSecond().getX()
                    && square.getFirst().getY() < square.getSecond().getY())) {
                PointImpl startPoint;
                int distance = (int) Math.abs(square.getFirst().getX() - square.getSecond().getX());
                if (square.getFirst().getX() < square.getSecond().getX()) {
                    startPoint = (PointImpl) square.getFirst();
                } else {
                    startPoint = (PointImpl) square.getSecond();
                }
                switch (distance) {
                    case 1:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 1));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 1));

                        break;
                    case 2:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 2));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 2));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 2));

                        break;
                    case 3:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 3));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 3));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 3));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 3));

                        break;
                    case 4:
                        result.add(startPoint);
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 3));
                        result.add(new PointImpl(startPoint.getX(), startPoint.getY() - 4));

                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 3));
                        result.add(new PointImpl(startPoint.getX() + 1, startPoint.getY() - 4));

                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 3));
                        result.add(new PointImpl(startPoint.getX() + 2, startPoint.getY() - 4));

                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY()));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 1));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 2));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 3));
                        result.add(new PointImpl(startPoint.getX() + 3, startPoint.getY() - 4));

                        break;

                    default:
                }
            }
        }
        return result;
    }
}
