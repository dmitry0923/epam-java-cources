package com.epam.university.java.core.task052;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Task052Impl implements Task052 {
    @Override
    public boolean validateCard(long number) {
        final String myNumber = String.valueOf(number);
        final List<String> unmodifiableList = Arrays.asList(myNumber.split("(?<=.)"));
        final List<String> split = new ArrayList<>(unmodifiableList);

        if (split.size() < 14 || split.size() > 19) {
            throw new IllegalArgumentException();
        }

        final int controlDigit = Integer.parseInt(split.get(split.size() - 1));
        split.remove(split.size() - 1);
        Collections.reverse(split);
        List<Integer> resultCollection = new ArrayList<>();

        for (int i = 0; i < split.size(); i++) {
            int position = i + 1;
            if (position % 2 != 0) {
                Integer temp = Integer.parseInt(split.get(i)) * 2;
                if (String.valueOf(temp).length() > 1) {
                    temp = Integer.parseInt(String.valueOf(String
                            .valueOf(temp).charAt(0)))
                            + Integer.parseInt(String.valueOf(String
                            .valueOf(temp).charAt(1)));
                    resultCollection.add(temp);

                } else {
                    resultCollection.add(Integer.parseInt(split.get(i)) * 2);
                }
            } else {
                resultCollection.add(Integer.parseInt(split.get(i)));
            }
        }

        int counter = 0;
        for (Integer integer : resultCollection
        ) {
            counter += integer;
        }

        String stringCounter = String.valueOf(counter);
        String lastCounterDigit = String.valueOf(stringCounter.charAt(stringCounter.length() - 1));
        int resultDigit = 10 - Integer.parseInt(lastCounterDigit);
        return resultDigit == controlDigit;
    }
}
