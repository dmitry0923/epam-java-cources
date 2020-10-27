package com.epam.university.java.core.task047;

public class Task047Impl implements Task047 {
    @Override
    public long calculateInversions(int n, int[] a) {
        if (n != a.length) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        int i = 0;
        int j;
        for (; i < a.length - 1; i++) {
            j = i + 1;
            for (; j < a.length; j++) {
                if (a[i] > a[j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
