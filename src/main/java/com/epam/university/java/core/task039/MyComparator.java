package com.epam.university.java.core.task039;

import java.util.Comparator;

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        if (x.data == y.data) {
            return Character.compare(y.c, x.c);
        }
        return x.data - y.data;
    }
}