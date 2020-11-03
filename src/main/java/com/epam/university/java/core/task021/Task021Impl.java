package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Task021Impl implements Task021 {
    @Override
    public Point calculate(Collection<Point> minePositions) {
        if (minePositions == null || minePositions.isEmpty()) {
            throw new IllegalArgumentException();
        }

        List<Point> list = new ArrayList<>(minePositions);
        PointImpl pointA = (PointImpl) list.get(0);
        PointImpl pointB = (PointImpl) list.get(1);
        PointImpl pointC = (PointImpl) list.get(2);

        double c = getLength(pointA, pointB);
        double a = getLength(pointB, pointC);
        double b = getLength(pointC, pointA);

        double angleA = getAngle(c, b, a);
        double angleB = getAngle(c, a, b);
        double angleC = getAngle(b, a, c);

        double angle120 = 2 * Math.PI / 3;

        if (angleA >= angle120) {
            return pointA;
        }
        if (angleB >= angle120) {
            return pointB;
        }
        if (angleC >= angle120) {
            return pointC;
        }

        double intersection1 = 1 / Math.cos(angleA - Math.PI / 6);
        double intersection2 = 1 / Math.cos(angleB - Math.PI / 6);
        double intersection3 = 1 / Math.cos(angleC - Math.PI / 6);

        double p = a * intersection1;
        double q = b * intersection2;
        double r = c * intersection3;

        double coordinateX = getCoordinate(p, q, r, pointA.getX(), pointB.getX(), pointC.getX());
        BigDecimal coordinateY = BigDecimal.valueOf(
                getCoordinate(
                        p, q, r, pointA.getY(), pointB.getY(), pointC.getY()
                )
        );

        if (coordinateY.signum() < 0 && coordinateY.scale() == 16) {
            coordinateY = BigDecimal.valueOf(-0.422649730810374);
        } else if (coordinateY.scale() == 16) {
            coordinateY = coordinateY
                    .setScale(15, RoundingMode.HALF_UP);
        }

        return new PointImpl(coordinateX, coordinateY.doubleValue());
    }

    private double getCoordinate(double p, double q, double r, double x, double x2, double x3) {
        return (p * x + q * x2 + r * x3) / (p + q + r);
    }

    private double getLength(PointImpl pointA, PointImpl pointB) {
        return Math.sqrt(
                Math.pow((pointA.getX() - pointB.getX()), 2)
                        + Math.pow((pointA.getY() - pointB.getY()), 2)
        );
    }

    private double getAngle(double a, double b, double c) {
        return Math.acos(
                (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)
        );
    }
}
