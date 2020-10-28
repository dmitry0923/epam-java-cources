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
        ListIterator<String> iterator = split.listIterator();

        while (iterator.hasNext()) {
            String stringDigit = iterator.next();
            int numericDigit = Integer.parseInt(stringDigit);
            if (numericDigit % 2 != 0) {
                numericDigit *= 2;
                if (String.valueOf(numericDigit).length() > 1) {
                    numericDigit = Integer.parseInt(String.valueOf(String
                            .valueOf(numericDigit).charAt(0)))
                            + Integer.parseInt(String.valueOf(String
                            .valueOf(numericDigit).charAt(1)));
                    iterator.remove();
                    iterator.add(String.valueOf(numericDigit));
                } else {
                    iterator.remove();
                    iterator.add(String.valueOf(numericDigit));
                }
            }
        }

        int counter = 0;
        for (String s : split
        ) {
            counter += Integer.parseInt(s);
        }

        String stringCounter = String.valueOf(counter);
        String lastCounterDigit = String.valueOf(stringCounter.charAt(stringCounter.length() - 1));
        int resultDigit = 10 - Integer.parseInt(lastCounterDigit);
        return resultDigit == controlDigit;
    }
}
