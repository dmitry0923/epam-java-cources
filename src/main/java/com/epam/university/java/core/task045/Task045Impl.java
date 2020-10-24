package com.epam.university.java.core.task045;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Task045Impl implements Task045 {

    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        if (input.isBlank()) {
            return input;
        }

        Collection<String> splitted = splitBySpace(input);
        StringBuilder sb = new StringBuilder();

        for (String s : splitted
        ) {
            sb.append(createAnagram(s));
            sb.append(" ");
        }

        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    private Collection<String> splitBySpace(String input) {
        final String[] splited = input.split(" ");
        return new ArrayList<>(Arrays.asList(splited));
    }

    private static String createAnagram(String word) {
        final char[] chars = word.toCharArray();
        StringBuilder result = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        for (char c : chars
        ) {
            if (Character.isLetter(c)) {
                letters.append(c);
            }
        }

        letters.reverse();
        int lettersLength = letters.length() - 1;
        int lettersStart = 0;

        if (lettersLength == -1) {
            return word;
        }

        for (int i = 0; i < chars.length && lettersLength != -1; i++) {
            if (Character.isLetter(chars[i])) {
                result.append(letters.charAt(lettersStart));
                lettersStart++;
                lettersLength--;
            } else {
                result.append(chars[i]);
            }
        }

        return result.toString();
    }
}
