package com.epam.university.java.core.task046;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Dmitry Novikov 26-Oct-20.
 */
public class Task046Impl implements Task046 {
    @Override
    public List<String> assembleMatryoshka(Integer k, Integer n) {
        if (k == null || n == null) {
            throw new IllegalArgumentException();
        }

        List<String> result = null;

        if (n == 1) {
            result = new ArrayList<>();
            result.add("0");
            return result;
        }

        List<Integer> rangeOfDolls = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            rangeOfDolls.add(i);
        }

        switch (k) {
            case 2:
                result = assembleForTwo(rangeOfDolls);
                break;
            case 3:
                result = assembleForThree(rangeOfDolls);
                break;
            case 4:
                result = assembleForFour(rangeOfDolls);
                break;
            default:
                break;
        }
        return result;
    }

    private List<String> assembleForTwo(List<Integer> rangeOfDolls) {
        List<String> result = new ArrayList<>();
        int first = 0;
        int second = 1;
        for (; first < rangeOfDolls.size(); first++) {
            for (; second < rangeOfDolls.size(); second++) {
                if (rangeOfDolls.get(first) < rangeOfDolls.get(second)) {
                    result.add(rangeOfDolls.get(first) + " " + rangeOfDolls.get(second));
                }
            }
            second = 2;

        }
        return result;
    }

    private List<String> assembleForThree(List<Integer> rangeOfDolls) {
        List<String> result = new ArrayList<>();
        int first = 0;
        int second = 1;
        int third = 2;
        for (; first < rangeOfDolls.size(); first++) {
            for (; second < rangeOfDolls.size(); second++) {
                for (; third < rangeOfDolls.size(); third++) {
                    if (rangeOfDolls.get(first) < rangeOfDolls.get(second)
                            && rangeOfDolls.get(second) < rangeOfDolls.get(third)) {
                        result.add(rangeOfDolls.get(first) + " "
                                + rangeOfDolls.get(second) + " "
                                + rangeOfDolls.get(third));
                    }

                }
                third = 3;
            }
            second = 2;
        }
        return result;
    }

    private List<String> assembleForFour(List<Integer> rangeOfDolls) {
        List<String> result = new ArrayList<>();
        int first = 0;
        int second = 1;
        int third = 2;
        int fourth = 3;
        for (; first < rangeOfDolls.size(); first++) {
            for (; second < rangeOfDolls.size(); second++) {
                for (; third < rangeOfDolls.size(); third++) {
                    for (; fourth < rangeOfDolls.size(); fourth++) {
                        if (rangeOfDolls.get(first) < rangeOfDolls.get(second)
                                && rangeOfDolls.get(second) < rangeOfDolls.get(third)
                                && rangeOfDolls.get(third) < rangeOfDolls.get(fourth)) {

                            result.add(rangeOfDolls.get(first) + " "
                                    + rangeOfDolls.get(second) + " "
                                    + rangeOfDolls.get(third) + " "
                                    + rangeOfDolls.get(fourth));
                        }
                    }
                    fourth = 4;
                }
                third = 3;
            }
            second = 2;
        }
        return result;
    }
}
