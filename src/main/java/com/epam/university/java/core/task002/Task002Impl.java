package com.epam.university.java.core.task002;

/**
 * Implementation of Task002.
 */
public class Task002Impl implements Task002 {
    @Override
    public boolean isEquals(String firstString, String secondString) {
        if (firstString == null || secondString == null) {
            throw new IllegalArgumentException();
        }

        return firstString.equals(secondString);
    }

    @Override
    public String left(String sourceString, int number) {
        if (sourceString == null || number < 0) {
            throw new IllegalArgumentException();
        } else if (number > sourceString.length()) {
            return sourceString;
        }

        return sourceString.substring(0, number);
    }

    @Override
    public String left(String sourceString, String separator) {
        if (sourceString == null || separator == null) {
            throw new IllegalArgumentException();
        }
        return sourceString.split(",")[0];
    }

    @Override
    public String right(String sourceString, int number) {
        if (sourceString == null || number < 0) {
            throw new IllegalArgumentException();
        } else if (number > sourceString.length()) {
            return sourceString;
        }

        return sourceString.substring(sourceString.length() - number);
    }

    @Override
    public String right(String sourceString, String separator) {
        if (sourceString == null || separator == null) {
            throw new IllegalArgumentException();
        }

        if (!sourceString.contains(separator)) {
            return sourceString;
        }

        return sourceString.split(",")[1].trim();
    }

    @Override
    public String[] split(String sourceString, String split) {
        if (sourceString == null || split == null) {
            throw new IllegalArgumentException();
        }

        if (!sourceString.contains(split)) {
            return new String[]{sourceString};
        }

        return sourceString.split(", ");
    }

    @Override
    public String join(String[] sourceCollection, String glue) {
        if (sourceCollection == null || glue == null
                || sourceCollection.length == 0
        ) {
            throw new IllegalArgumentException();
        }

        for (String s : sourceCollection
        ) {
            if (s == null) {
                throw new IllegalArgumentException();
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < sourceCollection.length; i++) {
            if (i == sourceCollection.length - 1) {
                stringBuilder.append(sourceCollection[i]);
            } else {
                stringBuilder.append(sourceCollection[i]);
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}