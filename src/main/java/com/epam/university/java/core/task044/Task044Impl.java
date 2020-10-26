package com.epam.university.java.core.task044;

import java.util.List;

public class Task044Impl implements Task044 {
    @Override
    public int findCountOfTraces(Integer points, List<String> lines) {
        if (points == null || lines == null) {
            throw new IllegalArgumentException();
        }

        if (lines.isEmpty()) {
            return points;
        }

        int counter = 0;
        boolean intersects = true;

        for (int i = 0; i < lines.size(); i++) {

            if (intersects) {
                intersects = false;
            } else {
                counter++;
            }

            for (int j = 1; j < lines.size(); j++) {
                if (Integer.parseInt(String.valueOf(lines.get(i).charAt(2)))
                        == Integer.parseInt(String.valueOf(lines.get(j).charAt(0)))) {
                    intersects = true;
                }
            }
        }

        if (!intersects) {
            counter++;
        }

        if (findMaxNumber(lines) != points) {
            counter += points - findMaxNumber(lines);
        }

        return counter;
    }

    /**
     * Method returns the max number in the list of Strings.
     *
     * @param list of Strings
     * @return int max number
     */
    public static int findMaxNumber(List<String> list) {
        int maxNumber = 0;
        for (String s : list
        ) {
            int maxInSet = Math.max(Integer.parseInt(String.valueOf(s.charAt(0))),
                    Integer.parseInt(String.valueOf(s.charAt(2))));
            if (maxNumber < maxInSet) {
                maxNumber = maxInSet;
            }
        }
        return maxNumber;
    }
}
