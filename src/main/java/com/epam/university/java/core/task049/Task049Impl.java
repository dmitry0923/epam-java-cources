package com.epam.university.java.core.task049;

/**
 * Author Dmitry Novikov 09-Oct-20.
 */
public class Task049Impl implements Task049 {
    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        if (first.isEmpty() || second.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String result = "";
        for (int length = first.length(); length > 0; length--) {
            int startIndex = 0;
            while (startIndex + length <= first.length()) {
                String current = first.substring(startIndex, startIndex + length);
                if (second.contains(current)) {
                    result = current;
                    break;
                }
                startIndex++;
            }
            if (result.length() != 0) {
                break;
            }
        }
        return result;
    }
}
