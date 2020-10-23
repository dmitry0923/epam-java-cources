package com.epam.university.java.core.task044;

import java.util.List;

/**
 * Author Dmitry Novikov 23-Oct-20.
 */
public class Task044Impl implements Task044 {
    /**
     * count of traces in the snow.
     * @param points total number of points
     * @param lines  each element is a two points separated by space
     * @return number of traces
     */
    @Override
    public int findCountOfTraces(Integer points, List<String> lines) {
        if (points == null || lines == null) {
            throw new IllegalArgumentException();
        }

        if (lines.isEmpty()) {
            return points;
        }


        return 0;
    }
}
