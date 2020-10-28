package com.epam.university.java.core.task051;

public class Task051Impl implements Task051 {
    @Override
    public String replace(String source) {
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException();
        }

        final String[] separate = source.split(" ");

        boolean isOnlyUpperCase = false;
        for (String s : separate
        ) {
            if (Character.isUpperCase(s.charAt(0))
                    && s.charAt((0)) == 'T') {
                isOnlyUpperCase = true;
            } else if (s.charAt((0)) == 't') {
                isOnlyUpperCase = false;
            }

            if (s.equals("the") && separate.length == 1) {
                throw new IllegalArgumentException();
            }
        }

        if (isOnlyUpperCase) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < separate.length; i++) {
            if (separate[i].charAt(0) == 't'
                    && i != separate.length - 1
                    && isVowel(separate[i + 1].charAt(0))) {
                sb.append("an ");
            } else if (separate[i].charAt(0) == 't'
                    && i != separate.length - 1
                    && !isVowel(separate[i + 1].charAt(0))) {
                sb.append("a ");
            } else if (i != separate.length - 1) {
                sb.append(separate[i]);
                sb.append(" ");
            } else {
                sb.append(separate[i]);
            }
        }
        return sb.toString();
    }

    private boolean isVowel(char letter) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
        boolean is = false;
        for (char c : vowels
        ) {
            if (letter == c) {
                is = true;
                break;
            }
        }
        return is;
    }
}


